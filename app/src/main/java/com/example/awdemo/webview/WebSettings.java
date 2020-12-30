package com.example.awdemo.webview;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import com.p761uc.webview.export.internal.SDKFactory;
import com.p761uc.webview.export.internal.utility.ReflectionUtil;
import com.taobao.taolive.sdk.model.message.PowerMsgType;

/* renamed from: com.uc.webview.export.WebSettings */
/* compiled from: ProGuard */
public abstract class WebSettings {
    public static final int COOKIE_TYPE_HYBRID = 4;
    public static final int COOKIE_TYPE_SYSTEM = 1;
    public static final int COOKIE_TYPE_UC = 2;
    public static final int COOKIE_TYPE_UC_ENCRYPT = 3;
    public static final int LOAD_CACHE_ELSE_NETWORK = 1;
    public static final int LOAD_CACHE_ONLY = 3;
    public static final int LOAD_DEFAULT = -1;
    @Deprecated
    public static final int LOAD_NORMAL = 0;
    public static final int LOAD_NO_CACHE = 2;
    public static final int MENU_ITEM_NONE = 0;
    public static final int MENU_ITEM_PROCESS_TEXT = 4;
    public static final int MENU_ITEM_SHARE = 1;
    public static final int MENU_ITEM_WEB_SEARCH = 2;
    public static final int MIXED_CONTENT_ALWAYS_ALLOW = 0;
    public static final int MIXED_CONTENT_COMPATIBILITY_MODE = 2;
    public static final int MIXED_CONTENT_NEVER_ALLOW = 1;

    /* renamed from: a */
    private String f43686a = "";
    public android.webkit.WebSettings mSettings = null;

    /* renamed from: com.uc.webview.export.WebSettings$LayoutAlgorithm */
    /* compiled from: ProGuard */
    public enum LayoutAlgorithm {
        NORMAL,
        SINGLE_COLUMN,
        NARROW_COLUMNS,
        TEXT_AUTOSIZING
    }

    /* renamed from: com.uc.webview.export.WebSettings$PluginState */
    /* compiled from: ProGuard */
    public enum PluginState {
        ON,
        ON_DEMAND,
        OFF
    }

    /* renamed from: com.uc.webview.export.WebSettings$RenderPriority */
    /* compiled from: ProGuard */
    public enum RenderPriority {
        NORMAL,
        HIGH,
        LOW
    }

    /* renamed from: com.uc.webview.export.WebSettings$TextSize */
    /* compiled from: ProGuard */
    public enum TextSize {
        SMALLEST(50),
        SMALLER(75),
        NORMAL(100),
        LARGER(150),
        LARGEST(200);
        
        public int value;

        private TextSize(int i) {
            this.value = i;
        }
    }

    /* renamed from: com.uc.webview.export.WebSettings$ZoomDensity */
    /* compiled from: ProGuard */
    public enum ZoomDensity {
        FAR(150),
        MEDIUM(100),
        CLOSE(75);
        

        /* renamed from: a */
        int f43693a;

        private ZoomDensity(int i) {
            this.f43693a = i;
        }

        public final int getValue() {
            return this.f43693a;
        }
    }

    @Deprecated
    public void setNavDump(boolean z) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setNavDump", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
    }

    @Deprecated
    public boolean getNavDump() {
        Boolean bool = (Boolean) ReflectionUtil.invokeNoThrow(this.mSettings, "getNavDump");
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public void setSupportZoom(boolean z) {
        this.mSettings.setSupportZoom(z);
    }

    public boolean supportZoom() {
        return this.mSettings.supportZoom();
    }

    public void setMediaPlaybackRequiresUserGesture(boolean z) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setMediaPlaybackRequiresUserGesture", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
    }

    public boolean getMediaPlaybackRequiresUserGesture() {
        Boolean bool = (Boolean) ReflectionUtil.invokeNoThrow(this.mSettings, "getMediaPlaybackRequiresUserGesture");
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public void setBuiltInZoomControls(boolean z) {
        this.mSettings.setBuiltInZoomControls(z);
    }

    public boolean getBuiltInZoomControls() {
        return this.mSettings.getBuiltInZoomControls();
    }

    public void setDisplayZoomControls(boolean z) {
        if (Build.VERSION.SDK_INT >= 11) {
            this.mSettings.setDisplayZoomControls(z);
        }
    }

    public boolean getDisplayZoomControls() {
        if (Build.VERSION.SDK_INT >= 11) {
            return this.mSettings.getDisplayZoomControls();
        }
        return false;
    }

    public void setAllowFileAccess(boolean z) {
        this.mSettings.setAllowFileAccess(z);
    }

    public boolean getAllowFileAccess() {
        return this.mSettings.getAllowFileAccess();
    }

    public void setAllowContentAccess(boolean z) {
        if (Build.VERSION.SDK_INT >= 11) {
            this.mSettings.setAllowContentAccess(z);
        }
    }

    public boolean getAllowContentAccess() {
        return Build.VERSION.SDK_INT >= 11 && this.mSettings.getAllowContentAccess();
    }

    public void setLoadWithOverviewMode(boolean z) {
        this.mSettings.setLoadWithOverviewMode(z);
    }

    public boolean getLoadWithOverviewMode() {
        return this.mSettings.getLoadWithOverviewMode();
    }

    @Deprecated
    public void setEnableSmoothTransition(boolean z) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setEnableSmoothTransition", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
    }

    @Deprecated
    public boolean enableSmoothTransition() {
        Boolean bool = (Boolean) ReflectionUtil.invokeNoThrow(this.mSettings, "enableSmoothTransition");
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @Deprecated
    public void setUseWebViewBackgroundForOverscrollBackground(boolean z) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setUseWebViewBackgroundForOverscrollBackground", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
    }

    @Deprecated
    public boolean getUseWebViewBackgroundForOverscrollBackground() {
        Boolean bool = (Boolean) ReflectionUtil.invokeNoThrow(this.mSettings, "getUseWebViewBackgroundForOverscrollBackground");
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public void setSaveFormData(boolean z) {
        this.mSettings.setSaveFormData(z);
    }

    public boolean getSaveFormData() {
        return this.mSettings.getSaveFormData();
    }

    @Deprecated
    public void setSavePassword(boolean z) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setSavePassword", new Class[]{Boolean.class}, new Object[]{Boolean.valueOf(z)});
    }

    @Deprecated
    public boolean getSavePassword() {
        Boolean bool = (Boolean) ReflectionUtil.invokeNoThrow(this.mSettings, "getSavePassword");
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public synchronized void setTextZoom(int i) {
        if (Build.VERSION.SDK_INT >= 14) {
            this.mSettings.setTextZoom(i);
        }
    }

    public synchronized int getTextZoom() {
        if (Build.VERSION.SDK_INT < 14) {
            return 0;
        }
        return this.mSettings.getTextZoom();
    }

    public synchronized void setTextSize(TextSize textSize) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setTextSize", new Class[]{TextSize.class}, new Object[]{TextSize.valueOf(textSize.name())});
    }

    public synchronized TextSize getTextSize() {
        TextSize textSize = (TextSize) ReflectionUtil.invokeNoThrow(this.mSettings, "getTextSize");
        if (textSize == null) {
            return null;
        }
        return TextSize.valueOf(textSize.name());
    }

    @Deprecated
    public void setDefaultZoom(ZoomDensity zoomDensity) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setDefaultZoom", new Class[]{ZoomDensity.class}, new Object[]{ZoomDensity.valueOf(zoomDensity.name())});
    }

    public ZoomDensity getDefaultZoom() {
        ZoomDensity zoomDensity = (ZoomDensity) ReflectionUtil.invokeNoThrow(this.mSettings, "getDefaultZoom");
        if (zoomDensity == null) {
            return null;
        }
        return ZoomDensity.valueOf(zoomDensity.name());
    }

    @Deprecated
    public void setLightTouchEnabled(boolean z) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setLightTouchEnabled", new Class[]{Boolean.class}, new Object[]{Boolean.valueOf(z)});
    }

    @Deprecated
    public boolean getLightTouchEnabled() {
        Boolean bool = (Boolean) ReflectionUtil.invokeNoThrow(this.mSettings, "getLightTouchEnabled");
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @Deprecated
    public synchronized void setUseDoubleTree(boolean z) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setUseDoubleTree", new Class[]{Boolean.class}, new Object[]{Boolean.valueOf(z)});
    }

    @Deprecated
    public synchronized boolean getUseDoubleTree() {
        Boolean bool = (Boolean) ReflectionUtil.invokeNoThrow(this.mSettings, "getUseDoubleTree");
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @Deprecated
    public synchronized void setUserAgent(int i) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setUserAgent", new Class[]{Integer.class}, new Object[]{Integer.valueOf(i)});
    }

    @Deprecated
    public synchronized int getUserAgent() {
        Integer num = (Integer) ReflectionUtil.invokeNoThrow(this.mSettings, "getUserAgent");
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public synchronized void setUseWideViewPort(boolean z) {
        this.mSettings.setUseWideViewPort(z);
    }

    public synchronized boolean getUseWideViewPort() {
        return this.mSettings.getUseWideViewPort();
    }

    public synchronized void setSupportMultipleWindows(boolean z) {
        this.mSettings.setSupportMultipleWindows(z);
    }

    public synchronized boolean supportMultipleWindows() {
        return this.mSettings.supportMultipleWindows();
    }

    public synchronized void setLayoutAlgorithm(LayoutAlgorithm layoutAlgorithm) {
        this.mSettings.setLayoutAlgorithm(LayoutAlgorithm.valueOf(layoutAlgorithm.name()));
    }

    public synchronized LayoutAlgorithm getLayoutAlgorithm() {
        return LayoutAlgorithm.valueOf(this.mSettings.getLayoutAlgorithm().name());
    }

    public synchronized void setStandardFontFamily(String str) {
        this.mSettings.setStandardFontFamily(str);
    }

    public synchronized String getStandardFontFamily() {
        return this.mSettings.getStandardFontFamily();
    }

    public synchronized void setFixedFontFamily(String str) {
        this.mSettings.setFixedFontFamily(str);
    }

    public synchronized String getFixedFontFamily() {
        return this.mSettings.getFixedFontFamily();
    }

    public synchronized void setSansSerifFontFamily(String str) {
        this.mSettings.setSansSerifFontFamily(str);
    }

    public synchronized String getSansSerifFontFamily() {
        return this.mSettings.getSansSerifFontFamily();
    }

    public synchronized void setSerifFontFamily(String str) {
        this.mSettings.setSerifFontFamily(str);
    }

    public synchronized String getSerifFontFamily() {
        return this.mSettings.getSerifFontFamily();
    }

    public synchronized void setCursiveFontFamily(String str) {
        this.mSettings.setCursiveFontFamily(str);
    }

    public synchronized String getCursiveFontFamily() {
        return this.mSettings.getCursiveFontFamily();
    }

    public synchronized void setFantasyFontFamily(String str) {
        this.mSettings.setFantasyFontFamily(str);
    }

    public synchronized String getFantasyFontFamily() {
        return this.mSettings.getFantasyFontFamily();
    }

    public synchronized void setMinimumFontSize(int i) {
        this.mSettings.setMinimumFontSize(i);
    }

    public synchronized int getMinimumFontSize() {
        return this.mSettings.getMinimumFontSize();
    }

    public synchronized void setMinimumLogicalFontSize(int i) {
        this.mSettings.setMinimumLogicalFontSize(i);
    }

    public synchronized int getMinimumLogicalFontSize() {
        return this.mSettings.getMinimumLogicalFontSize();
    }

    public synchronized void setDefaultFontSize(int i) {
        this.mSettings.setDefaultFontSize(i);
    }

    public synchronized int getDefaultFontSize() {
        return this.mSettings.getDefaultFontSize();
    }

    public synchronized void setDefaultFixedFontSize(int i) {
        this.mSettings.setDefaultFixedFontSize(i);
    }

    public synchronized int getDefaultFixedFontSize() {
        return this.mSettings.getDefaultFixedFontSize();
    }

    public synchronized void setLoadsImagesAutomatically(boolean z) {
        this.mSettings.setLoadsImagesAutomatically(z);
    }

    public synchronized boolean getLoadsImagesAutomatically() {
        return this.mSettings.getLoadsImagesAutomatically();
    }

    public synchronized void setBlockNetworkImage(boolean z) {
        this.mSettings.setBlockNetworkImage(z);
    }

    public synchronized boolean getBlockNetworkImage() {
        return this.mSettings.getBlockNetworkImage();
    }

    public synchronized void setBlockNetworkLoads(boolean z) {
        this.mSettings.setBlockNetworkLoads(z);
    }

    public synchronized boolean getBlockNetworkLoads() {
        return this.mSettings.getBlockNetworkLoads();
    }

    public synchronized void setJavaScriptEnabled(boolean z) {
        this.mSettings.setJavaScriptEnabled(z);
    }

    public void setAllowUniversalAccessFromFileURLs(boolean z) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setAllowUniversalAccessFromFileURLs", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
    }

    public void setAllowFileAccessFromFileURLs(boolean z) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setAllowFileAccessFromFileURLs", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
    }

    @Deprecated
    public synchronized void setPluginsEnabled(boolean z) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setPluginsEnabled", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
    }

    @Deprecated
    public synchronized void setPluginState(PluginState pluginState) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setPluginState", new Class[]{PluginState.class}, new Object[]{PluginState.valueOf(pluginState.name())});
    }

    @Deprecated
    public synchronized void setPluginsPath(String str) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setPluginsPath", new Class[]{String.class}, new Object[]{str});
    }

    public synchronized void setDatabasePath(String str) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setDatabasePath", new Class[]{String.class}, new Object[]{str});
    }

    public synchronized void setGeolocationDatabasePath(String str) {
        this.mSettings.setGeolocationDatabasePath(str);
    }

    public synchronized void setAppCacheEnabled(boolean z) {
        this.mSettings.setAppCacheEnabled(z);
    }

    public synchronized void setAppCachePath(String str) {
        this.mSettings.setAppCachePath(str);
    }

    @Deprecated
    public synchronized void setAppCacheMaxSize(long j) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setAppCacheMaxSize", new Class[]{Long.class}, new Object[]{Long.valueOf(j)});
    }

    public synchronized void setDatabaseEnabled(boolean z) {
        this.mSettings.setDatabaseEnabled(z);
    }

    public synchronized void setDomStorageEnabled(boolean z) {
        this.mSettings.setDomStorageEnabled(z);
    }

    public synchronized boolean getDomStorageEnabled() {
        return this.mSettings.getDomStorageEnabled();
    }

    public synchronized String getDatabasePath() {
        String str = (String) ReflectionUtil.invokeNoThrow(this.mSettings, "getDatabasePath");
        if (str == null) {
            return null;
        }
        return str;
    }

    public synchronized boolean getDatabaseEnabled() {
        return this.mSettings.getDatabaseEnabled();
    }

    public synchronized void setGeolocationEnabled(boolean z) {
        this.mSettings.setGeolocationEnabled(z);
    }

    public synchronized boolean getJavaScriptEnabled() {
        return this.mSettings.getJavaScriptEnabled();
    }

    public boolean getAllowUniversalAccessFromFileURLs() {
        Boolean bool = (Boolean) ReflectionUtil.invokeNoThrow(this.mSettings, "getAllowUniversalAccessFromFileURLs");
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean getAllowFileAccessFromFileURLs() {
        Boolean bool = (Boolean) ReflectionUtil.invokeNoThrow(this.mSettings, "getAllowFileAccessFromFileURLs");
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @Deprecated
    public synchronized boolean getPluginsEnabled() {
        boolean z = false;
        if (Build.VERSION.SDK_INT <= 17) {
            Boolean bool = (Boolean) ReflectionUtil.invokeNoThrow(this.mSettings, "getPluginsEnabled");
            if (bool == null) {
                return false;
            }
            return bool.booleanValue();
        }
        if (Build.VERSION.SDK_INT == 18 && PluginState.ON == this.mSettings.getPluginState()) {
            z = true;
        }
        return z;
    }

    @Deprecated
    public synchronized PluginState getPluginState() {
        PluginState pluginState = (PluginState) ReflectionUtil.invokeNoThrow(this.mSettings, "getPluginState");
        if (pluginState == null) {
            return null;
        }
        return PluginState.valueOf(pluginState.name());
    }

    @Deprecated
    public synchronized String getPluginsPath() {
        String str = (String) ReflectionUtil.invokeNoThrow(this.mSettings, "getPluginsPath");
        if (str == null) {
            return null;
        }
        return str;
    }

    public synchronized void setJavaScriptCanOpenWindowsAutomatically(boolean z) {
        this.mSettings.setJavaScriptCanOpenWindowsAutomatically(z);
    }

    public synchronized boolean getJavaScriptCanOpenWindowsAutomatically() {
        return this.mSettings.getJavaScriptCanOpenWindowsAutomatically();
    }

    public synchronized void setDefaultTextEncodingName(String str) {
        this.mSettings.setDefaultTextEncodingName(str);
    }

    public synchronized String getDefaultTextEncodingName() {
        return this.mSettings.getDefaultTextEncodingName();
    }

    public synchronized void setUserAgentString(String str) {
        this.mSettings.setUserAgentString(str);
    }

    public synchronized String getUserAgentString() {
        return this.mSettings.getUserAgentString();
    }

    public static String getDefaultUserAgent(Context context) {
        return (String) SDKFactory.invoke(PowerMsgType.customServGoodIntro, context);
    }

    public void setNeedInitialFocus(boolean z) {
        this.mSettings.setNeedInitialFocus(z);
    }

    @Deprecated
    public synchronized void setRenderPriority(RenderPriority renderPriority) {
        ReflectionUtil.invokeNoThrow(this.mSettings, "setRenderPriority", new Class[]{RenderPriority.class}, new Object[]{RenderPriority.valueOf(renderPriority.name())});
    }

    public void setCacheMode(int i) {
        this.mSettings.setCacheMode(i);
    }

    public int getCacheMode() {
        return this.mSettings.getCacheMode();
    }

    public synchronized void setPreCacheScope(String str) {
        this.f43686a = str;
    }

    public synchronized String getPreCacheScope() {
        return this.f43686a;
    }

    public void setMixedContentMode(int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            ReflectionUtil.invokeNoThrow(this.mSettings, "setMixedContentMode", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(i)});
        }
    }

    public int getMixedContentMode() {
        Integer num;
        if (Build.VERSION.SDK_INT < 21 || (num = (Integer) ReflectionUtil.invokeNoThrow(this.mSettings, "getMixedContentMode")) == null) {
            return 0;
        }
        return num.intValue();
    }

    public void setOffscreenPreRaster(boolean z) {
        if (Build.VERSION.SDK_INT >= 23) {
            ReflectionUtil.invokeNoThrow(this.mSettings, "setOffscreenPreRaster", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
        }
    }

    public boolean getOffscreenPreRaster() {
        Boolean bool;
        if (Build.VERSION.SDK_INT < 23 || (bool = (Boolean) ReflectionUtil.invokeNoThrow(this.mSettings, "getOffscreenPreRaster")) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public void setDisabledActionModeMenuItems(int i) {
        if (Build.VERSION.SDK_INT >= 24) {
            ReflectionUtil.invokeNoThrow(this.mSettings, "setDisabledActionModeMenuItems", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(i)});
        }
    }

    public int getDisabledActionModeMenuItems() {
        Integer num;
        if (Build.VERSION.SDK_INT < 24 || (num = (Integer) ReflectionUtil.invokeNoThrow(this.mSettings, "getDisabledActionModeMenuItems")) == null) {
            return 0;
        }
        return num.intValue();
    }
}
