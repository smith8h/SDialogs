/*
 * Created by Dev. Smith (Hussein Shakir) on 9/18/23, 12:00 AM
 * Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://github.com/smith8h
 * Last modified: 9/18/23, 12:00 AM
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

package smith.lib.alerts.dialog.callbacks;

/**
 * Set a functional interface to interact with biometric hardware results.
 */
public interface OnBiometricAuthCallBack {

    /**
     * Triggered when the biometric response is complete successfully.
     */
    void onSuccess();

    /**
     * Triggered when the biometric response is complete with failure.
     * this method will trigger each time the biometric auth getting error recognizing the fingerprint,
     * that means it will trigger until the max failure count reaches its maximum.
     */
    void onFailure();

    /**
     * Triggered when the biometric response is complete with some errors.
     */
    void onError();
}
