package com.example.awdemo.webview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WebView extends FrameLayout {

    private WebViewProvider mProvider;

    public WebView(@NonNull Context context) {
        super(context);
    }

    public WebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mProvider = new WebViewChromium();
        mProvider.init(null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mProvider.onDraw(canvas);
    }
}
