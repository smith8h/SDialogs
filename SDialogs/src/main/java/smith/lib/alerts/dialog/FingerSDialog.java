/*
 *
 *   Created by Dev. Smith (Hussein Shakir) on 9/16/23, 10:07 PM
 *   Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://github.com/smith8h
 *   Last modified: 9/16/23, 10:07 PM
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

package smith.lib.alerts.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

/**
 * The safest and most secured class of SDialog lib.
 * this class create a security SDialogs that alerts the user to input his fingerprint to access or proceed to something.
 */
@SuppressWarnings({"unused"})
public class FingerSDialog extends SDialog {

    /**
     * Pass the current context you're using this sdialog from.
     * @param context Current context (or Activity).
     */
    @SuppressLint("InflateParams")
    public FingerSDialog(Context context) {
        super.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_fingerprint, null);
        init();
    }
}
