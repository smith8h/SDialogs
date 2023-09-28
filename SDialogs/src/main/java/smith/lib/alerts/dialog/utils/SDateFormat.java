/*
 * Created by Dev. Smith (Hussein Shakir) on 9/28/23, 5:14 PM
 * Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://github.com/smith8h
 * Last modified: 9/28/23, 5:14 PM
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

import smith.lib.alerts.dialog.DatePickerSDialog;

@SuppressWarnings({"unused"})
public class SDateFormat {

    /**
     * The days enum class to set the format for days in {@link DatePickerSDialog}
     * using value of days format as {@link SDateFormat.DAYS#D},
     * {@link SDateFormat.DAYS#DD}.
     */
    public enum DAYS {

        /**
         * A day in its single numeric representation. <b>e.g.</b> {@code "1", "21", "30"...}
         */
        D("d"),
        /**
         * A day in its single numeric representation. <b>e.g.</b> {@code "01", "08", "22"...}
         */
        DD("dd");

        /**
         * The days enum class to set the format for days in {@link DatePickerSDialog}
         * @param d value of days format as {@link SDateFormat.DAYS#D},
         * {@link SDateFormat.DAYS#DD}.
         */
        DAYS(String d) {}
    }

    /**
     * The months enum class to set the format for months in {@link DatePickerSDialog}
     * using the value of months format as {@link SDateFormat.MONTHS#M},
     * {@link SDateFormat.MONTHS#MM}, {@link SDateFormat.MONTHS#MMM},
     * {@link SDateFormat.MONTHS#MMMM}.
     */
    public enum MONTHS {

        /**
         * A month in its numeric representation.
         * <b>e.g.</b> {@code "1", "10", "12"...}
         */
        M("M"),
        /**
         * A month in its numeric representation.
         * <b>e.g.</b> {@code "01", "05", "11"...}
         */
        MM("MM"),
        /**
         * A month in its three letters abbreviated representation.
         * <b>e.g.</b> {@code "Aug", "Nov", "May"...}
         */
        MMM("MMM"),
        /**
         * A month in its full name representation.
         * <b>e.g.</b> {@code "August", "November", "May"...}
         */
        MMMM("MMMM");

        /**
         * The months enum class to set the format for months in {@link DatePickerSDialog}
         * @param m the value of months format as {@link SDateFormat.MONTHS#M},
         * {@link SDateFormat.MONTHS#MM}, {@link SDateFormat.MONTHS#MMM},
         * {@link SDateFormat.MONTHS#MMMM}.
         */
        MONTHS(String m) {}
    }
}
