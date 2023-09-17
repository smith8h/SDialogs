/*
 *
 *   Created by Dev. Smith (Hussein Shakir) on 9/16/23, 11:57 PM
 *   Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://github.com/smith8h
 *   Last modified: 9/16/23, 11:57 PM
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *                     You may obtain a copy of the License at
 *                  http://www.apache.org/licenses/LICENSE-2.0
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * /
 */

package smith.lib.alerts.dialog.utils;

import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.biometrics.BiometricManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import androidx.annotation.RestrictTo;

@SuppressWarnings({"unused", "deprecation"})
@RestrictTo(RestrictTo.Scope.LIBRARY)
public class FingerSDialogAuth {

    /**
     * Check if device does have biometrics hardware or enrolled biometrics <i><b>at least</b></i>.
     *
     * @param context paas the current context.
     * @return {@code true} if device has fingerprint sensor and one fingerprint registered.
     */

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
