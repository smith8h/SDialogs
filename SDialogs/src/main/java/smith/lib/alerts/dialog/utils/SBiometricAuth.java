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

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Log;
import android.os.CancellationSignal;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import smith.lib.alerts.dialog.callbacks.OnBiometricAuthCallBack;

@RestrictTo(RestrictTo.Scope.LIBRARY)
@SuppressWarnings({"unused", "deprecation"})
public class SBiometricAuth {

    private OnBiometricAuthCallBack callBack;
    private CancellationSignal cancellationSignal;
    private int failedCount = 0, maxFailedCount = 3;

    public SBiometricAuth(Context context) {
        try {
            init(context);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCallBack(OnBiometricAuthCallBack callBack) {
        this.callBack = callBack;
    }

    public void setMaxFailureCount(int maxFailedCount) {
        this.maxFailedCount = maxFailedCount;
    }

    @SuppressLint("RestrictedApi")
    private void init(Context context) throws NoSuchAlgorithmException, InvalidKeyException {
        failedCount = 0;
        cancellationSignal = new CancellationSignal();

        Cipher cipher;
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            keyGenerator.init(new KeyGenParameterSpec.Builder("SDialogs", KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .build());
            cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, keyGenerator.generateKey());
        } catch (InvalidAlgorithmParameterException | NoSuchProviderException |
                 NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            BiometricPrompt.AuthenticationCallback promptCallback = new BiometricPrompt.AuthenticationCallback() {
                @Override public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    Log.i("Fingerprint", "Authentication failed");
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

                @Override public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Log.i("Fingerprint", "Authentication succeeded");
                    Log.i("Fingerprint Object String", result.getCryptoObject().g.toString());

                    if (callBack != null) {
                        cancelSignal();
                        callBack.onSuccess();
                    }
                }

                @Override public void onAuthenticationError(int errorCode, CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    Log.i("Fingerprint", "Authentication error: " + errorCode + " - " + errString);
                    if (callBack != null) {
                        cancelSignal();
                        callBack.onError();
                    }
                }

                @Override public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                    super.onAuthenticationHelp(helpCode, helpString);
                    Log.i("Fingerprint Help", helpString.toString());
                }
            };

            BiometricPrompt prompt = new BiometricPrompt.Builder(context)
                    .setTitle("Biometric Authentication")
                    .setNegativeButton("Cancel", context.getMainExecutor(), (dialogInterface, which) -> {})
                    .build();

            prompt.authenticate(
                    new BiometricPrompt.CryptoObject(cipher),
                    cancellationSignal,
                    context.getMainExecutor(),
                    promptCallback
            );
        } else {
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
                        public void onAuthenticationSucceeded(@NonNull FingerprintManagerCompat.AuthenticationResult result) {
                            Log.i("Fingerprint Object String", String.valueOf(result.getCryptoObject()));

                            if (callBack != null) {
                                cancelSignal();
                                callBack.onSuccess();
                            }
                        }
                    };
            FingerprintManagerCompat fingerprintManagerCompat = FingerprintManagerCompat.from(context);
            cancellationSignal = new CancellationSignal();

            fingerprintManagerCompat.authenticate(
                    new FingerprintManagerCompat.CryptoObject(cipher),
                    0,
                    cancellationSignal,
                    authenticationCallback,
                    null);
        }
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
