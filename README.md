# SDialogs

![Builds and tests](https://github.com/smith8h/SDialogs/actions/workflows/build.yml/badge.svg)
[![JitPack release](https://jitpack.io/v/smith8h/SDialogs.svg)](https://jitpack.io/#smith8h/SDialogs)
![Latest release](https://img.shields.io/github/v/release/smith8h/SDialogs?include_prereleases&amp;label=latest%20release)
![Stability](https://img.shields.io/badge/stability-stable-green.svg)
![Monthly download statistics](https://jitpack.io/v/smith8h/SDialogs/month.svg)
![minSDK](https://img.shields.io/badge/minSDK-21-f39f37)
![repo size](https://img.shields.io/github/repo-size/smith8h/sdialogs)

[//]: # (![Stable Version]&#40;https://img.shields.io/badge/stable_version-4.4-blue&#41;)

</br>

Beautiful custom Android dialogs (Alert, MultiSelect, CheckBox, SingleSelect, RadioButton, Menu, Loading, Progress, Input, and Custom)

# Menu Of Contents

- [How to setup](#setup)
- [Changelog](https://github.com/smith8h/SDialogs/blob/main/CHANGELOG.md)
- [How to implement (Documentation)](#documentation)
  - [AlertSDialog](#alertsdialog)
  - [BiometricSDialog](#biometricsdialog)
  - [CustomSDialog](#customsdialog)
  - [InputSDialog](#inputsdialog)
  - [MenuSDialog](#menusdialog)
  - [LoadingSDialog](#loadingsdialog)
  - [ProgressSDialog](#progresssdialog)
  - [SingleSelectSDialog](#singleselectsdialog)
  - [MultiSelectSDialog](#multiselectsdialog)
  - [SliderSDialog](#slidersdialog)
  - [FeedbackSDialog](#feedbacksdialog)
- [CallBacks](#callbacks)
- [Used By](#used-by)
- [Contributors](#contributors)
- [Support library improvements (Donations)](#donations)

# Setup

> **Step 1.** Add the JitPack repository to your build file.</br>
> Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

> **Step 2.** Add the dependency:

```gradle
dependencies {
    ...
    implementation 'com.github.smith8h:SDialogs:4.5'
}
```

# Documentation

This library composed of 8 types of **SDialogs** and an additional one type that represent the customizable **SDialog** (use a custom view/layout of your own design and logic) for fast and small codes of initializing new dialog.


All these SDialogs using an algorithm to calculate and extract colors lighter/darker from the accent color you set in method `setAccentColor()`.

## AlertSDialog

<p align="center">
    <img src="https://te.legra.ph/file/29a07cd23e39721c24002.jpg" style="width: 50%;"  alt=""/>
</p>
Create new instance of AlertSDialog:

```java
    AlertSDialog sdialog = new AlertSDialog(this);
```

Table of methods:

**Setters**

- `setIconResource(icon)` (optional) add icon from res.
- `setIconDrawable(icon)` (optional) add icon from a Drawable.
- `setIconBitmap(icon)` (optional) add icon from bitmap.
- `setTitle("Title");` set title as string text.
- `setTitle(R.string.title);` set title as string resource.
- `setText(text);` accepts span text | int string res | string text...
- `setMaxHeight(340);` set max height to ignore screen overriding (default is 360).
- `setPositiveButton("Button Text", clickcallback);` (Optional) the code here in java8, to use java7 or below syntax see in [CallBacks](#callbacks).
- `setNegativeButton("Button Text", clickcallback);` (Optional) onClick callback used.
- `setNeutralButton("Button Text", clickcallback);` (Optional) .
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.COLOR_DEFAULT`.
- `setTheme(SDialog.THEME_SYSTEM);` or `THEME_LIGHT / THEME_DARK`.
- `setCancelable(false);` (Optional).
- `setOnDismissCallBack(dismisscallback);` (Optional).
- `show(); / show(1200);` to show always or for a duration of time (auto hide).
- `dismiss(); / dismiss(500);` to hide it immediately or after duration of time (auto dismiss).

**Getters**

- `getAccentColor()` get accent color.
- `getTitleColor()` get accent color.
- `getBackgroundColor()` get accent color.
- `getTextColor()` get accent color.

## BiometricSDialog

<p align="center">
    <img src="https://silabotov.ru/img/8eac71e0-5915-422c-a25a-7471657f8474.jpg" style="width: 50%;"  alt=""/>
</p>
Create new instance of BiometricSDialog:

```java
    BiometricSDialog sdialog = new BiometricSDialog(context);
```

Table of methods:

**Setters**

- `setNegativeButtonText("Button Text");` (Optional) cancel button text.
- `setNegativeButtonText(R.string.text);` (Optional) cancel button text.
- `setPositiveButton("Button Text", clickcallback);` (Optional) the code here in java8, to use java7 or below syntax see in [CallBacks](#callbacks).
- `setPositiveButton(R.string.text, clickcallback);` (Optional) the code here in java8, to use java7 or below syntax see in [CallBacks](#callbacks).
- `setIconResource(icon)` (optional) add icon from res.
- `setIconDrawable(icon)` (optional) add icon from a Drawable.
- `setIconBitmap(icon)` (optional) add icon from bitmap.
- `setTitle("Title");` set title as string text.
- `setTitle(R.string.title);` set title as string resource.
- `setText(text);` accepts span text | int string res | string text...
- `setErrorText(text);` accepts span text | int string res | string text...
- `setSuccessText(text);` accepts span text | int string res | string text...
- `setMaxFailureCount(3);` set max failure to ignore dialog (default is 3).
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.COLOR_DEFAULT`.
- `setTheme(SDialog.THEME_SYSTEM);` or `THEME_LIGHT / THEME_DARK`.
- `setOnBiometricAuthCallback(biometricAuthCallback);` (Optional).
- `show(); / show(1200);` to show always or for a duration of time (auto hide).
- `dismiss(); / dismiss(500);` to hide it immediately or after duration of time (auto dismiss).

**Getters**

- `getAccentColor()` get accent color.
- `getTitleColor()` get accent color.
- `getBackgroundColor()` get accent color.
- `getTextColor()` get accent color.

## CustomSDialog

Create new instance of CustomSDialog and set a view as dialog view:

```java
    CustomSDialog sdialog = new CustomSDialog(this, View);
    // or
    CustomSDialog sdialog = new CustomSDialog(this, R.layout.your_layout);
```

Table of methods:

- `setCancelable(false);` (Optional).
- `setOnDismissCallBack(dismisscallback);` (Optional).
- `setOnBindViewCallBack(onBindViewCallBack);` set a callback or logic to get in touch with your custom dialog logic.
- `show(); / show(1200);` to show always or for a duration of time (auto hide).
- `dismiss(); / dismiss(1200);` to hide it immediately or after a duration of time.

## InputSDialog

<p align="center">
    <img src="https://te.legra.ph/file/f398e834ab82ae6367776.jpg" style="width: 50%;" />
</p>

Create new instance of InputSDialog:

```java
    InputSDialog sdialog = new InputSDialog(this);
```

Table of methods:

**Setters**

- `setIconResource(icon)` (optional) add icon from res.
- `setIconDrawable(icon)` (optional) add icon from a Drawable.
- `setIconBitmap(icon)` (optional) add icon from bitmap.
- `setTitle("Title");` set title as string text.
- `setTitle(R.string.title);` set title as string resource.
- `setText(text);` string | int res, (Optional) set a docs above the input field.
- `setPositiveButtonAction("Button Text", inputclickclickcallback);` use OnInputClickCallBack.
- `setNegativeButtonText("Button Text");` dismissing sdialog by default (no need for callback).
- `setInputFieldHint("Input field hint");` set hint text in input field.
- `setInputFieldText("Input field text");` set text in input field.
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.COLOR_DEFAULT`.
- `setTheme(SDialog.THEME_SYSTEM);` or `THEME_LIGHT / THEME_DARK`.
- `setCancelable(false);` (*Optional).
- `setOnDismissCallBack(dismisscallback);` (Optional).
- `show(); / show(1200);` to show always or for a duration of time (auto hide).
- `dismiss(); / dismiss(500);` to hide it immediately or after duration of time (auto dismiss).

**Getters**

- `getAccentColor()` get accent color.
- `getTitleColor()` get accent color.
- `getBackgroundColor()` get accent color.
- `getTextColor()` get accent color.
- `getHintColor()` get hint color.

## MenuSDialog

<p align="center">
    <img src="https://te.legra.ph/file/11810e2ec43ec98511739.jpg" style="width: 50%;" />
</p>

Create new instance of ItemsSDialog:

```java
    MenuSDialog sdialog = new MenuSDialog(this);
```

Table of methods:

**Setters**

- `setIconResource(icon)` (optional) add icon from res.
- `setIconDrawable(icon)` (optional) add icon from a Drawable.
- `setIconBitmap(icon)` (optional) add icon from bitmap.
- `setTitle("Title");` set title as string text.
- `setTitle(R.string.title);` set title as string resource.
- `setMaxHeight(340);` set max height to ignore screen overriding (default is 360).
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.COLOR_DEFAULT`.
- `setTheme(SDialog.THEME_SYSTEM);` or `THEME_LIGHT / THEME_DARK`.
- `setItemsList(List<MenuSDialogItem>);` set items from existing arraylist of `MenuSDialogItem`.
- `addItemsList(List<MenuSDialogItem>);` add another items of `MenuSDialogItem` to existing items in `MenuSDialog` without resetting them.
- `addItem(DrawableIcon, "item text")` add new item (drawable, string).
- `addItem(DrawableIntRes, "item text")` add new item (int res, string).
- `removeItem(2)` remove an item by its **index** from the list.
- `setOnItemClickCallBack(itemclickcallback);` add a functionality to when item clicked.
- `setOnDismissCallBack(dismisscallback);` (Optional).
- `setCancelable(false);` (Optional).
- `show(); / show(460);` to show it permanently or for a duration of time (auto dismiss).
- `dismiss(); / dismiss(500);` to hide it immediately or after duration of time (auto dismiss).
> if you want to add new item using `MenuSDialogItem` use it this way:
</br>
```
List<MenuSDialogItem> items = new ArrayList<>();
items.add(new MenuSDialogItem(context, R.drawable.icon, "text"));
items.add(new MenuSDialogItem(drawable, "text"));
```

**Getters**

- `getAccentColor()` get accent color.
- `getTitleColor()` get accent color.
- `getBackgroundColor()` get accent color.

## LoadingSDialog

<p align="center">
    <img src="https://te.legra.ph/file/646416c965f2227515195.jpg" style="width: 50%;" />
</p>

Create new instance of LoadingSDialog:

```java
    LoadingSDialog sdialog = new LoadingSDialog(this);
```

Table of methods:

**Setters**

- `setTitle("Loading");` set title as string text.
- `setTitle(R.string.title);` set title as string resource.
- `setText(text);` string | int string res.
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.COLOR_DEFAULT`.
- `setTheme(SDialog.THEME_SYSTEM);` or `THEME_LIGHT / THEME_DARK`.
- `setOnDismissCallBack(dismisscallback);` (Optional).
- `show(); / show(1200);` to show it always or for a duration of time.
- `dismiss(); / dismiss(500);` to hide it immediately or after duration of time (auto dismiss).

**Getters**

- `getAccentColor()` get accent color.
- `getTitleColor()` get accent color.
- `getBackgroundColor()` get accent color.
- `getTextColor()` get text color.

## ProgressSDialog

<p align="center">
    <img src="https://te.legra.ph/file/cbaec92d9dd58602a7e8b.jpg" style="width: 50%;" />
</p>

Create new instance of ProgressSDialog:

```java
    ProgressSDialog sdialog = new ProgressSDialog(this);
```

Table of methods:

**Setters**

- `setIconResource(icon)` (optional) add icon from res.
- `setIconDrawable(icon)` (optional) add icon from a Drawable.
- `setIconBitmap(icon)` (optional) add icon from bitmap.
- `setTitle("Downloading Files");` set title as string text.
- `setTitle(R.string.title);` set title as string resource.
- `setText(text);` string | int res, set text under progress bar (can be changed onProgress)
- `setMin(9);` default is 0.
- `setMax(200);` default is 100.
- `setProgress(120)` set sdialog progress.
- `setOnProgressCallBack(progresscallback)` set on progress changed callback.
- `setNegativeButtonAction("button text", clickCallBack);` (optional) set a cancel process or any other usage button.
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.COLOR_DEFAULT`.
- `setTheme(SDialog.THEME_SYSTEM);` or `THEME_LIGHT / THEME_DARK`.
- `setOnDismissCallBack(dismisscallback);` (Optional).
- `show(); / show(2100);` to show it always or for duration of time (auto hide).
- `dismiss(); / dismiss(500);` to hide it immediately or after duration of time (auto dismiss).
  **Getters**

- `getProgress()`
- `getMax()`
- `getMin()`
- `getAccentColor()`
- `getTitleColor()`
- `getBackgroundColor()`
- `getTextColor()`

## SingleSelectSDialog

<p align="center">
    <img src="https://te.legra.ph/file/e5f52e32d27c0b0d7ab66.jpg" style="width: 50%;" />
</p>

Create new instance of SingleSelectSDialog:

```java
    SingleSelectSDialog sdialog = new SingleSelectSDialog(this);
```

Table of methods:

**Setters**

- `setIconResource(icon)` (optional) add icon from res.
- `setIconDrawable(icon)` (optional) add icon from a Drawable.
- `setIconBitmap(icon)` (optional) add icon from bitmap.
- `setTitle("Title");` set title as string text.
- `setTitle(R.string.title);` set title as string resource.
- `setMaxHeight(340);` set max height to ignore screen overriding (default is 360).
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.COLOR_DEFAULT`.
- `setTheme(SDialog.THEME_SYSTEM);` or `THEME_LIGHT / THEME_DARK`.
- `setOnSingleSelectCallBack(singleselectcallback);` get checked radio button id & text.
- `addItem(intId, "item text")` add new item(1, "Hollow").
- `setCheckedItem(intId)` (optional) set the checked item by default by its id.
- `removeItem(2)` remove an item by its **index** from the list.
- `removeItem("item text")` remove an item by its **text** from the list.
- `setCancelable(false);` (Optional).
- `setOnDismissCallBack(dismisscallback);` (Optional).
- `show(); / show(2100);` to show it always or for duration of time (auto hide).
- `dismiss(); / dismiss(500);` to hide it immediately or after duration of time (auto dismiss).

**Getters**

- `getItemsList()` returns a list of Map<String, Object> containing all the items, to access their id, text, isChecked use:

> SDialog.KEY_ITEM_ID | KEY_ITEM_TEXT | KEY_ITEM_CHECKED

- `getAccentColor()` get accent color.
- `getTitleColor()` get accent color.
- `getBackgroundColor()` get accent color.
- `getTextColor()` get text color.

## MultiSelectSDialog

<p align="center">
    <img src="https://te.legra.ph/file/329172778299d06c611af.jpg" style="width: 50%;" />
</p>

Create new instance of MultiSelectSDialog:

```java
    MultiSelectSDialog sdialog = new MultiSelectSDialog(this);
```

Table of methods:

**Setters**

- `setIconResource(icon)` (optional) add icon from res.
- `setIconDrawable(icon)` (optional) add icon from a Drawable.
- `setIconBitmap(icon)` (optional) add icon from bitmap.
- `setTitle("Title");` set title as string text.
- `setTitle(R.string.title);` set title as string resource.
- `setMaxHeight(340);` set max height to ignore screen overriding (default is 360).
- `setPositiveButton("button text", multiselectcallback)`
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.COLOR_DEFAULT`.
- `setTheme(SDialog.THEME_SYSTEM);` or `THEME_LIGHT / THEME_DARK`.
- `addItem(intId, "item text", isChecked)` add new item(1, "Holow", true).
- `setCheckedItem(intId)` (optional) set the checked item by default by its id.
- `removeItem(2)` remove an item by its **index** from the list.
- `removeItem("item text")` remove an item by its **text** from the list.
- `setCancelable(false);` (Optional).
- `setOnDismissCallBack(dismisscallback);` (Optional).
- `show(); / show(2100);` to show it always or for duration of time (auto hide).
- `dismiss(); / dismiss(500);` to hide it immediately or after duration of time (auto dismiss).

**Getters**

- `getItemsList()` returns a list of Map<String, Object> containing all the items, to access their id, text, isChecked use:

> SDialog.KEY_ITEM_ID | KEY_ITEM_TEXT | KEY_ITEM_CHECKED

- `getAccentColor()` get accent color.
- `getTitleColor()` get accent color.
- `getBackgroundColor()` get accent color.
- `getTextColor()` get text color.

## SliderSDialog

<p align="center">
    <img src="https://te.legra.ph/file/2a4f52ad59c300bbff95c.jpg" style="width: 50%;" />
</p>

Create new instance of SliderSDialog:

```java
    SliderSDialog sdialog = new SliderSDialog(this);
```

Table of methods:

**Setters**

- `setIconResource(icon)` (optional) add icon from resources.
- `setIconDrawable(icon)` (optional) add icon from a Drawable.
- `setIconBitmap(icon)` (optional) add icon from bitmap.
- `setTitle("Title");` set title as string text.
- `setTitle(R.string.title);` set title as string resource.
- `setText("text hint" || int res)` (Optional) set a hint below title.
- `setPositiveButtonAction("button text", slidercallback)`
- `setNegativeButtonText("Cancel")` negative (cancel) button text.
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.COLOR_DEFAULT`.
- `setTheme(SDialog.THEME_SYSTEM);` or `THEME_LIGHT / THEME_DARK`.
- `setMin(float)` set min value (from).
- `setMax(float)` set max value (to).
- `setValue(float)` set current slider value (from min to max).
- `setStepBy(int)` (optional) how likely you want to walk through values in slider.
- `show(); / show(2100);` to show it always or for duration of time (auto hide).
- `dismiss(); / dismiss(500);` to hide it immediately or after duration of time (auto dismiss).

**Getters:**

- `getMinValue()` get min.
- `getValue()` get value.
- `getMaxValue()` get max.
- `getAccentColor()` get accent color.
- `getTitleColor()` get title color.
- `getBackgroundColor()` get accent color.
- `getTextColor()` get text color.

## FeedbackSDialog

<p align="center">
    <img src="https://te.legra.ph/file/ee2c0318c74f6674f4f0a.jpg" style="width: 50%;" />
</p>

Create new instance of FeedbackSDialog:

```java
    FeedbackSDialog sdialog = new FeedbackSDialog(this);
```

Table of methods:

**Setters**

- `setIconResource(icon)` (optional) add icon from resources.
- `setIconDrawable(icon)` (optional) add icon from a Drawable.
- `setIconBitmap(icon)` (optional) add icon from bitmap.
- `setTitle("Title");` set title as string text.
- `setTitle(R.string.title);` set title as string resource.
- `setText("Text");` set text as string.
- `setText(R.string.text);` set text as string resource.
- `setInputFieldHint("Feedback...");` set text as input hint.
- `setSubmitFeedBackButton("button text", onfeedbacksubmitcallback)` set a submit button to get the feedback submitted.
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.COLOR_DEFAULT`.
- `setTheme(SDialog.THEME_SYSTEM);` or `THEME_LIGHT / THEME_DARK`.
- `show(); / show(2100);` to show it always or for duration of time (auto hide).
- `dismiss(); / dismiss(500);` to hide it immediately or after duration of time (auto dismiss).

**Getters:**

- `getAccentColor()` get accent color.
- `getTitleColor()` get title color.
- `getBackgroundColor()` get accent color.
- `getPatternColor()` get pattern in normal state color.

# CallBacks

CallBacks used in SDialogs:

```java
    // onClick button in alert sdialog
    OnClickCallBack clickcallback = new OnClickCallBack() {
        @Override
        public void onClick() {
            
        }
    };
    
    // onDismiss any sdialog
    OnDismissCallBack dismisscallback = new OnDismissCallBack() {
        @Override
        public void onDismiss() {
            
        }
    };
    
    // onBindCustomView in custom sdialog
    OnBindCustomViewCallBack bindcustomviewcallback = OnBindCustomViewCallBack() {
        @Override
        public void onBindCustomView(View customView) {
            // use customView.findView.... to find the child views.
        }
    };
    
    // onInputPositiveButtonClick in input sdialog
    OnInputClickCallBack inputclickcallback = new OnInputClickCallBack() {
        @Override
        public void onClick(String inputText) {
            // use inputText to get the text entered.
        }
    };
    
    // onItemClickCallBack used in items sdialog
    OnItemClickCallBack itemclickcallback = new OnItemClickCallBack() {
        @Override
        public void onItemClick(int index, MenuSDialogItem item) {
            // use itemValue to get item text, itemIndex to get item index.
        }
    };
    
    // onProgressCallBack used in progress sdialog
    OnProgressCallBack progresscallback = new OnProgressCallBack() {
        @Override
        public void onProgress(int progress, int percent) {
            // use progress to get the progress 120 of max 200 eg..
            // use percent to get progress percentage 20 of 100% eg..
        }
        
        @Override
        public void onFinish() {
            // add logic to when progress finish.
        }
    };
    
    // onSingleSelect used in single select sdialog
    OnSingleSelectCallBack multiselectcallback = new OnSingleSelectCallBack() {
        @Override
        public void onSelect(int itemId, String itemText) {
            // use itemId to get selected item id
            // use itemText to get selected itemText
        }
    };
    
    // onMultiSelect used in multi select sdialog in positive button
    OnMultiSelectCallBack multiselectcallback = new OnMultiSelectCallBack() {
        @Override
        public void onMultiSelect(List<Map<String, Object>> itemsList) {
            // use itemsList to get all items selected
            // to access their id, text, isChecked use:
            // SDialog.KEY_ITEM_ID | KEY_ITEM_TEXT | KEY_ITEM_CHECKED
        }
    };
    
    OnSlideCallBack slidercallback = new OnSlideCallBack() {
        @Override
        public void onValueSelected(float value) {
            // use value
        }
    };

    // onFeedback submit callback
    OnFeedbackSubmitCallBack onfeedbacksubmitcallback = new OnFeedbackSubmitCallBack() {
        @Override
        public void onSubmit(boolean isLiked, String feedbackText) {
            // use isLiked
        }
    };

    // onBiometricAuthCallback
    OnBiometricAuthCallback biometricAuthCallback = new OnBiometricAuthCallback() {
      @Override
      public void onSuccess() {
        // auth succeed
      }
      
      @Override
      public void onFailure() {
        // auth failed
      }
      
      @Override
      public void onError() {
        // auth error
      }
    };
```

# Used By
List of apps on Play Store where this library used. Contact me if you want your app listed.

| Icon                                                                                                                                                         | Application                      |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------|
| <img src="https://play-lh.googleusercontent.com/I0i7O7IBTeuRDwjogpu9W5LFZU2X4IMPSs7fCxUOT5sMrTsaJauaEJXYOJCxSPGkm_ps=w240-h480-rw" width="48" height="48" /> | Coding Oasis - Learn Programming |
| <img src="https://play-lh.googleusercontent.com/nah9O-xROZI1Av_fRpLlUf4hq2pJpDVLQynCQ9ZQn5JFBGpo_kppnkMwpfr0zSB0jGQ=s256-rw" width="48" height="48" />       | Azkary                           |

<br>

# Contributors

<a href="https://github.com/smith8h/sdialogs/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=kimoandroid/glide-slider" />
</a>

<br/>

# Donations

> If you would like to support this project's further development, the creator of this projects or the continuous maintenance of the project **feel free to donate**.
> Your donation is highly appreciated. Thank you!
<br/>

You can **choose what you want to donate**, all donations are awesome!</br>
<br/>

[![PayPal](https://img.shields.io/badge/PayPal-00457C?style=for-the-badge&logo=paypal&logoColor=white)](https://www.paypal.me/husseinshakir)

<br/>

<p align="center">
  <img src="https://raw.githubusercontent.com/smith8h/smith8h/main/20221103_150053.png" style="width: 20%;"/>
  <br><b>With :heart:</b>
</p>
