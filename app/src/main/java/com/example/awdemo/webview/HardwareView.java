package com.example.awdemo.webview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.view.SurfaceHolder;

import org.chromium.android_webview.shell.DrawFn;
import org.chromium.base.Callback;

import java.nio.ByteBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class HardwareView extends GLSurfaceView {
        private static final int MODE_DRAW = 0;
        private static final int MODE_PROCESS = 1;
        private static final int MODE_PROCESS_NO_CONTEXT = 2;
        private static final int MODE_SYNC = 3;

        // mSyncLock is used to synchronized requestRender on the UI thread
        // and drawGL on the rendering thread. The variables following
        // are protected by it.
        private final Object mSyncLock = new Object();
        private int mFunctor;
        private int mLastDrawnFunctor;
        private boolean mSyncDone;
        private boolean mPendingDestroy;
        private int mLastScrollX;
        private int mLastScrollY;
        private Callback<int[]> mQuadrantReadbackCallback;

        // Only used by drawGL on render thread to store the value of scroll offsets at most recent
        // sync for subsequent draws.
        private int mCommittedScrollX;
        private int mCommittedScrollY;

        private boolean mHaveSurface;
        private Runnable mReadyToRenderCallback;
        private Runnable mReadyToDetachCallback;

        public HardwareView(Context context) {
            super(context);
            setEGLContextClientVersion(2); // GLES2
            getHolder().setFormat(PixelFormat.OPAQUE);
            setPreserveEGLContextOnPause(true);
            setRenderer(new Renderer() {
                private int mWidth;
                private int mHeight;

                @Override
                public void onDrawFrame(GL10 gl) {
                    HardwareView.this.onDrawFrame(gl, mWidth, mHeight);
                }

                @Override
                public void onSurfaceChanged(GL10 gl, int width, int height) {
                    gl.glViewport(0, 0, width, height);
                    gl.glScissor(0, 0, width, height);
                    mWidth = width;
                    mHeight = height;
                }

                @Override
                public void onSurfaceCreated(GL10 gl, EGLConfig config) {}
            });

            setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        }

        public void readbackQuadrantColors(Callback<int[]> callback) {
            synchronized (mSyncLock) {
                assert mQuadrantReadbackCallback == null;
                mQuadrantReadbackCallback = callback;
            }
            super.requestRender();
        }

        public boolean isReadyToRender() {
            return mHaveSurface;
        }

        public void setReadyToRenderCallback(Runnable runner) {
            assert !isReadyToRender() || runner == null;
            mReadyToRenderCallback = runner;
        }

        public void setReadyToDetachCallback(Runnable runner) {
            mReadyToDetachCallback = runner;
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mHaveSurface = true;
            if (mReadyToRenderCallback != null) {
                mReadyToRenderCallback.run();
                mReadyToRenderCallback = null;
            }
            super.surfaceCreated(holder);
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            mHaveSurface = false;
            if (mReadyToDetachCallback != null) {
                mReadyToDetachCallback.run();
                mReadyToDetachCallback = null;
            }
            super.surfaceDestroyed(holder);
        }

        public void updateScroll(int x, int y) {
            synchronized (mSyncLock) {
                mLastScrollX = x;
                mLastScrollY = y;
            }
        }

        public void awContentsDetached() {
            synchronized (mSyncLock) {
                super.requestRender();
                assert !mPendingDestroy;
                mPendingDestroy = true;
                try {
                    while (!mPendingDestroy) {
                        mSyncLock.wait();
                    }
                } catch (InterruptedException e) {
                    // ...
                }
            }
        }

        public void drawWebViewFunctor(int functor) {
            synchronized (mSyncLock) {
                super.requestRender();
                assert mFunctor == 0;
                mFunctor = functor;
                mSyncDone = false;
                try {
                    while (!mSyncDone) {
                        mSyncLock.wait();
                    }
                } catch (InterruptedException e) {
                    // ...
                }
            }
        }

        public void onDrawFrame(GL10 gl, int width, int height) {
            int functor;
            int scrollX;
            int scrollY;
            synchronized (mSyncLock) {
                scrollX = mLastScrollX;
                scrollY = mLastScrollY;

                if (mFunctor != 0) {
                    assert !mSyncDone;
                    functor = mFunctor;
                    mLastDrawnFunctor = mFunctor;
                    mFunctor = 0;
                    DrawFn.sync(functor, false);
                    mSyncDone = true;
                    mSyncLock.notifyAll();
                } else {
                    functor = mLastDrawnFunctor;
                    if (mPendingDestroy) {
                        DrawFn.destroyReleased();
                        mPendingDestroy = false;
                        mLastDrawnFunctor = 0;
                        mSyncLock.notifyAll();
                        return;
                    }
                }
            }
            if (functor != 0) {
                DrawFn.drawGL(functor, width, height, scrollX, scrollY);

                Callback<int[]> quadrantReadbackCallback = null;
                synchronized (mSyncLock) {
                    if (mQuadrantReadbackCallback != null) {
                        quadrantReadbackCallback = mQuadrantReadbackCallback;
                        mQuadrantReadbackCallback = null;
                    }
                }
                if (quadrantReadbackCallback != null) {
                    int quadrantColors[] = new int[4];
                    int quarterWidth = width / 4;
                    int quarterHeight = height / 4;
                    ByteBuffer buffer = ByteBuffer.allocate(4);
                    gl.glReadPixels(quarterWidth, quarterHeight * 3, 1, 1, GL10.GL_RGBA,
                            GL10.GL_UNSIGNED_BYTE, buffer);
                    quadrantColors[0] = readbackToColor(buffer);
                    gl.glReadPixels(quarterWidth * 3, quarterHeight * 3, 1, 1, GL10.GL_RGBA,
                            GL10.GL_UNSIGNED_BYTE, buffer);
                    quadrantColors[1] = readbackToColor(buffer);
                    gl.glReadPixels(quarterWidth, quarterHeight, 1, 1, GL10.GL_RGBA,
                            GL10.GL_UNSIGNED_BYTE, buffer);
                    quadrantColors[2] = readbackToColor(buffer);
                    gl.glReadPixels(quarterWidth * 3, quarterHeight, 1, 1, GL10.GL_RGBA,
                            GL10.GL_UNSIGNED_BYTE, buffer);
                    quadrantColors[3] = readbackToColor(buffer);
                    quadrantReadbackCallback.onResult(quadrantColors);
                }
            }
        }

        private int readbackToColor(ByteBuffer buffer) {
            return Color.argb(buffer.get(3) & 0xff, buffer.get(0) & 0xff, buffer.get(1) & 0xff,
                    buffer.get(2) & 0xff);
        }
    }