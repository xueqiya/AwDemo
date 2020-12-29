package org.chromium.android_webview;

import J.N;
import java.lang.Override;
import java.lang.String;
import javax.annotation.Generated;
import org.chromium.base.JniStaticTestMocker;
import org.chromium.base.NativeLibraryLoadedStatus;
import org.chromium.base.annotations.CheckDiscard;

@Generated("org.chromium.jni_generator.JniProcessor")
@CheckDiscard("crbug.com/993421")
final class AwQuotaManagerBridgeJni implements AwQuotaManagerBridge.Natives {
  private static AwQuotaManagerBridge.Natives testInstance;

  public static final JniStaticTestMocker<AwQuotaManagerBridge.Natives> TEST_HOOKS = new org.chromium.base.JniStaticTestMocker<org.chromium.android_webview.AwQuotaManagerBridge.Natives>() {
    @java.lang.Override
    public void setInstanceForTesting(
        org.chromium.android_webview.AwQuotaManagerBridge.Natives instance) {
      if (!J.N.TESTING_ENABLED) {
        throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
      }
      testInstance = instance;
    }
  };

  @Override
  public void init(long nativeAwQuotaManagerBridge, AwQuotaManagerBridge caller) {
    N.MIrzsg_q(nativeAwQuotaManagerBridge, caller);
  }

  @Override
  public void deleteAllData(long nativeAwQuotaManagerBridge, AwQuotaManagerBridge caller) {
    N.MMBD8oze(nativeAwQuotaManagerBridge, caller);
  }

  @Override
  public void deleteOrigin(long nativeAwQuotaManagerBridge, AwQuotaManagerBridge caller,
      String origin) {
    N.MVmZoL1a(nativeAwQuotaManagerBridge, caller, origin);
  }

  @Override
  public void getOrigins(long nativeAwQuotaManagerBridge, AwQuotaManagerBridge caller,
      int callbackId) {
    N.MBqEItUo(nativeAwQuotaManagerBridge, caller, callbackId);
  }

  @Override
  public void getUsageAndQuotaForOrigin(long nativeAwQuotaManagerBridge,
      AwQuotaManagerBridge caller, String origin, int callbackId, boolean isQuota) {
    N.MJn4yzAu(nativeAwQuotaManagerBridge, caller, origin, callbackId, isQuota);
  }

  public static AwQuotaManagerBridge.Natives get() {
    if (N.TESTING_ENABLED) {
      if (testInstance != null) {
        return testInstance;
      }
      if (N.REQUIRE_MOCK) {
        throw new UnsupportedOperationException("No mock found for the native implementation for org.chromium.android_webview.AwQuotaManagerBridge.Natives. The current configuration requires all native implementations to have a mock instance.");
      }
    }
    NativeLibraryLoadedStatus.checkLoaded(false);
    return new AwQuotaManagerBridgeJni();
  }
}
