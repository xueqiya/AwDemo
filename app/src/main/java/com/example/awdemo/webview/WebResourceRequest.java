package com.example.awdemo.webview;

import android.net.Uri;
import java.util.Map;

/* renamed from: com.uc.webview.export.WebResourceRequest */
/* compiled from: ProGuard */
public class WebResourceRequest {

    /* renamed from: a */
    String f43674a;

    /* renamed from: b */
    Map<String, String> f43675b;

    /* renamed from: c */
    Uri f43676c;

    /* renamed from: d */
    boolean f43677d;

    /* renamed from: e */
    boolean f43678e;

    /* renamed from: f */
    boolean f43679f;

    public WebResourceRequest(String str, Map<String, String> map, String str2, boolean z, boolean z2) {
        this.f43674a = str;
        this.f43675b = map;
        this.f43676c = Uri.parse(str2);
        this.f43677d = z;
        this.f43678e = z2;
    }

    public WebResourceRequest(String str, Map<String, String> map, String str2, boolean z, boolean z2, boolean z3) {
        this.f43679f = z3;
        this.f43674a = str;
        this.f43675b = map;
        this.f43676c = Uri.parse(str2);
        this.f43677d = z;
        this.f43678e = z2;
    }

    public WebResourceRequest(String str, Map<String, String> map, Uri uri, boolean z, boolean z2) {
        this.f43674a = str;
        this.f43675b = map;
        this.f43676c = uri;
        this.f43677d = z;
        this.f43678e = z2;
    }

    public WebResourceRequest(String str, String str2, Map<String, String> map) {
        this.f43674a = str;
        this.f43675b = map;
        this.f43676c = Uri.parse(str2);
    }

    public WebResourceRequest(String str, Map<String, String> map) {
        this("Get", str, map);
    }

    public String getMethod() {
        return this.f43674a;
    }

    public void setMethod(String str) {
        this.f43674a = str;
    }

    public Map<String, String> getRequestHeaders() {
        return this.f43675b;
    }

    public void setRequestHeaders(Map<String, String> map) {
        this.f43675b = map;
    }

    public Uri getUrl() {
        return this.f43676c;
    }

    public void setUrl(String str) {
        this.f43676c = Uri.parse(str);
    }

    public void setUrl(Uri uri) {
        this.f43676c = uri;
    }

    public boolean hasGesture() {
        return this.f43677d;
    }

    public boolean isForMainFrame() {
        return this.f43678e;
    }

    public boolean isRedirect() {
        return this.f43679f;
    }

    public String toString() {
        return "method=" + this.f43674a + ",header=" + this.f43675b + ",uri=" + this.f43676c + ",hasGesture=" + this.f43677d + ",isForMainFrame=" + this.f43678e;
    }
}
