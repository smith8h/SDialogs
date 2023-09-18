/*
 * Created by Dev. Smith (Hussein Shakir) on 9/17/23, 11:44 PM
 * Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://github.com/smith8h
 * Last modified: 9/17/23, 8:02 PM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *                  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package smith.lib.alerts.dialog.utils;

import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.biometrics.BiometricManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import androidx.annotation.RestrictTo;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.core.os.CancellationSignal;
import smith.lib.alerts.dialog.callbacks.OnBiometricAuthCallBack;

@RestrictTo(RestrictTo.Scope.LIBRARY)
@SuppressWarnings({"unused", "deprecation"})
public class SBiometricAuth {

    private OnBiometricAuthCallBack callBack;
    private CancellationSignal cancellationSignal;
    private int failedCount = 0, maxFailedCount = 3;

    public SBiometricAuth(Context context) {
        init(context);
    }

    public void setCallBack(OnBiometricAuthCallBack callBack) {
        this.callBack = callBack;
    }

    public void setMaxFailureCount(int maxFailedCount) {
        this.maxFailedCount = maxFailedCount;
    }

    private void init(Context context) {
        // fixme : replace deprecated methods.
        failedCount = 0;

        FingerprintManagerCompat.AuthenticationCallback authenticationCallback =
                new FingerprintManagerCompat.AuthenticationCallback() {
            @Override
            public void onAuthenticationFailed() {
                failedCount++;
                if (failedCount == maxFailedCount) {
                    failedCount = 0;
                    if (callBack != null) {
                        cancelSignal();
                        callBack.onError();
                    }
                } else {
                    callBack.onFailure();
                }
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                if (callBack != null) {
                    cancelSignal();
                    callBack.onSuccess();
                }
            }
        };
        FingerprintManagerCompat fingerprintManagerCompat = FingerprintManagerCompat.from(context);
        cancellationSignal = new CancellationSignal();

        fingerprintManagerCompat.authenticate(null, 0, cancellationSignal, authenticationCallback, null);
    }

    public void cancelSignal() {
        if (cancellationSignal != null) cancellationSignal.cancel();
    }

    public static boolean hasBiometrics(Context context) {
        boolean hasBiometrics;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            BiometricManager biometricManager = (BiometricManager) context.getSystemService(Context.BIOMETRIC_SERVICE);
            hasBiometrics = biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS;
        } else {
            FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
            boolean hardwareSupport = fingerprintManager.isHardwareDetected();

            boolean secureSupport = true;
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            if (keyguardManager != null) secureSupport = keyguardManager.isKeyguardSecure();

            boolean hasPwd = fingerprintManager.hasEnrolledFingerprints();
            hasBiometrics = hardwareSupport && secureSupport && hasPwd;
        }
        return hasBiometrics;
    }
}
