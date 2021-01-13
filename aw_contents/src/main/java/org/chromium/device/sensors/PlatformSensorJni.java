package org.chromium.device.sensors;

import J.N;
import java.lang.Override;
import javax.annotation.Generated;
import org.chromium.base.JniStaticTestMocker;
import org.chromium.base.NativeLibraryLoadedStatus;
import org.chromium.base.annotations.CheckDiscard;

@Generated("org.chromium.jni_generator.JniProcessor")
@CheckDiscard("crbug.com/993421")
final class PlatformSensorJni implements PlatformSensor.Natives {
  private static PlatformSensor.Natives testInstance;

  public static final JniStaticTestMocker<PlatformSensor.Natives> TEST_HOOKS = new JniStaticTestMocker<PlatformSensor.Natives>() {
    @Override
    public void setInstanceForTesting(PlatformSensor.Natives instance) {
      if (!N.TESTING_ENABLED) {
        throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
      }
      testInstance = instance;
    }
  };

  @Override
  public void notifyPlatformSensorError(long nativePlatformSensorAndroid, PlatformSensor caller) {
    N.MrHXg7he(nativePlatformSensorAndroid, caller);
  }

  @Override
  public void updatePlatformSensorReading(long nativePlatformSensorAndroid, PlatformSensor caller,
      double timestamp, double value1, double value2, double value3, double value4) {
    N.Mb4JvlL7(nativePlatformSensorAndroid, caller, timestamp, value1, value2, value3, value4);
  }

  public static PlatformSensor.Natives get() {
    if (N.TESTING_ENABLED) {
      if (testInstance != null) {
        return testInstance;
      }
      if (N.REQUIRE_MOCK) {
        throw new UnsupportedOperationException("No mock found for the native implementation for org.chromium.device.sensors.PlatformSensor.Natives. The current configuration requires all native implementations to have a mock instance.");
      }
    }
    NativeLibraryLoadedStatus.checkLoaded(false);
    return new PlatformSensorJni();
  }
}
