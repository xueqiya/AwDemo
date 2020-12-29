package org.chromium.android_webview;

import J.N;
import android.os.CancellationSignal;
import java.lang.Override;
import javax.annotation.Generated;
import org.chromium.base.JniStaticTestMocker;
import org.chromium.base.NativeLibraryLoadedStatus;
import org.chromium.base.annotations.CheckDiscard;

@Generated("org.chromium.jni_generator.JniProcessor")
@CheckDiscard("crbug.com/993421")
final class AwPdfExporterJni implements AwPdfExporter.Natives {
  private static AwPdfExporter.Natives testInstance;

  public static final JniStaticTestMocker<AwPdfExporter.Natives> TEST_HOOKS = new org.chromium.base.JniStaticTestMocker<org.chromium.android_webview.AwPdfExporter.Natives>() {
    @java.lang.Override
    public void setInstanceForTesting(org.chromium.android_webview.AwPdfExporter.Natives instance) {
      if (!J.N.TESTING_ENABLED) {
        throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
      }
      testInstance = instance;
    }
  };

  @Override
  public void exportToPdf(long nativeAwPdfExporter, AwPdfExporter caller, int fd, int[] pages,
      CancellationSignal cancellationSignal) {
    N.Mp2j15Oh(nativeAwPdfExporter, caller, fd, pages, cancellationSignal);
  }

  public static AwPdfExporter.Natives get() {
    if (N.TESTING_ENABLED) {
      if (testInstance != null) {
        return testInstance;
      }
      if (N.REQUIRE_MOCK) {
        throw new UnsupportedOperationException("No mock found for the native implementation for org.chromium.android_webview.AwPdfExporter.Natives. The current configuration requires all native implementations to have a mock instance.");
      }
    }
    NativeLibraryLoadedStatus.checkLoaded(false);
    return new AwPdfExporterJni();
  }
}
