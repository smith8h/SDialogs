/*
 *
 *   Created by Dev. Smith (Hussein Shakir) on 9/16/23, 10:07 PM
 *   Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://github.com/smith8h
 *   Last modified: 8/6/23, 3:23 PM
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

package smith.lib.alerts.dialog.callbacks;

import java.util.*;

/**
 * On multiple items selected callback for MultiSelectSDialog.
 */
@FunctionalInterface
public interface OnMultiSelectCallBack {
    /**
     * triggered on selecting items from the list and returns:
     *
     * @param selectedItems as a list of items that been selected.
     */
    void onMultiSelect(List<Map<String, Object>> selectedItems);
}
