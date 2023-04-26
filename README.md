# SDialogs
Beautiful custom android dialogs ( alert, multiselect checkbox, singleselect radiobutton, string list items, loading, progress, input and custom sdialog ).

# Menu Of Contents
- [How to setup](#setup)
- [How to implement (Documentation)](#documentation)
  - [AlertSDialog](#alertsdialog)
  - [CustomSDialog](#customsdialog)
  - [InputSDialog](#inputsdialog)
  - [ItemsSDialog](#itemssdialog)
  - [LoadingSDialog](#loadingsdialog)
  - [ProgressSDialog](#progresssdialog)
  - [SingleSelectSDialog](#singleselectsdialog)
  - [MultiSelectSDialog](#multiselectsdialog)
  - [SliderSDialog](#slidersdialog)
- [Support library improvements (Donations)](#donations)

# Setup
> **Step 1.** Add the JitPack repository to your build file.</br>
Add it in your root build.gradle at the end of repositories:
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
    implementation 'com.github.smith8h:SDialogs:1.0.1'
}
```

# Documentation
This library composed of 8 types of **SDialogs** and an additional one type that represent the customizable **SDialog** (use a custom view/layout of your own design and logic) for fast and small codes of initializing new dialog.
</br>
All these SDialogs using an algorithm to calculate and extract colors lighter/darker from the accent color you set in method `setAccentColor()`. 

## AlertSDialog
![progress](https://te.legra.ph/file/29a07cd23e39721c24002.jpg)

Create new instance of AlertSDialog:
```java
    AlertSDialog sdialog = new AlertSDialog(this);
```
Table of methods:

**Setters**
- `setIconResource(icon)` add icon from res.
- `setIconDrawable(icon)` add icon from a Drawable.
- `setIconBitmap(icon)` add icon from bitmap.
- `setTitle("Title");`
- `setText(text);` accepts span text | int string res | string text...
- `setPositiveButton("Button Text", clickcallback);` (Optional) the code here in java8, to use java7 or below syntax see in [CallBacks](#callbacks).
- `setNegativeButton("Button Text", clickcallback);` (Optional) onClick callback used.
- `setNeutralButton("Button Text", clickcallback);` (Optional) .
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.COLOR_DEFAULT`.
- `setTheme(SDialog.THEME_SYSTEM);` or `THEME_LIGHT / THEME_DARK`.
- `setCancelable(false);` (*Optional).
- `setOnDismissCallBack(dismisscallback);` (Optional).
- `show(); / show(1200);` to show always or for a duration of time (auto hide).
- `dismiss();` to hide it.

**Getters**
- `getAccentColor()` get accent color.
- `getTitleColor()` get accent color.
- `getBackgroundColor()` get accent color.
- `getTextColor()` get accent color.

## CustomSDialog
Create new instance of CustomSDialog:
```java
    CustomSDialog sdialog = new CustomSDialog(this);
```
Table of methods:

- `setView(view, bindviewcallback);` set a view and liatener onBindCustomView.
- `setView(R.layout.your_layout_name, bindviewcallback);` set a int layout from resources and liatener onBindCustomView.
- `setCancelable(false);` (*Optional).
- `setOnDismissCallBack(dismisscallback);` (Optional).
- `show(); / show(1200);` to show always or for a duration of time (auto hide).
- `dismiss();` to hide it.

## InputSDialog
![progress](https://te.legra.ph/file/f398e834ab82ae6367776.jpg)

Create new instance of InputSDialog:
```java
    InputSDialog sdialog = new InputSDialog(this);
```
Table of methods:

**Setters**
- `setIconResource(icon)` add icon from res.
- `setIconDrawable(icon)` add icon from a Drawable.
- `setIconBitmap(icon)` add icon from bitmap.
- `setTitle("Title");`
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
- `dismiss();` to hide it.

**Getters**
- `getAccentColor()` get accent color.
- `getTitleColor()` get accent color.
- `getBackgroundColor()` get accent color.
- `getTextColor()` get accent color.
- `getHintColor()` get hint color.

## ItemsSDialog
![progress](https://te.legra.ph/file/11810e2ec43ec98511739.jpg)

Create new instance of ItemsSDialog:
```java
    ItemsSDialog sdialog = new ItemsSDialog(this);
```
Table of methods:

**Setters**
- `setIconResource(icon)` add icon from res.
- `setIconDrawable(icon)` add icon from a Drawable.
- `setIconBitmap(icon)` add icon from bitmap.
- `setTitle("Title");`
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.COLOR_DEFAULT`.
- `setTheme(SDialog.THEME_SYSTEM);` or `THEME_LIGHT / THEME_DARK`.
- `setItemsList(ArrayList<String>);` set items from existing arraylist of strings `ArrayList<String>`.
- `setOnItemClickCallBack(itemclickcallback);`
- `addItem("item text")` add new item.
- `removeItem(2)` remove an item by its **index** from the list.
- `removeItem("item text")` remove an item by its **text** from the list.
- `setCancelable(false);` (Optional).
- `setOnDismissCallBack(dismisscallback);` (Optional).
- `show();` to show it.
- `dismiss();` to hide it.

**Getters**
- `getAccentColor()` get accent color.
- `getTitleColor()` get accent color.
- `getBackgroundColor()` get accent color.

## LoadingSDialog
![progress](https://te.legra.ph/file/646416c965f2227515195.jpg)

Create new instance of LoadingSDialog:
```java
    LoadingSDialog sdialog = new LoadingSDialog(this);
```
Table of methods:

**Setters**
- `setTitle("Loading");`
- `setText(text);` string | int string res.
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.COLOR_DEFAULT`.
- `setTheme(SDialog.THEME_SYSTEM);` or `THEME_LIGHT / THEME_DARK`.
- `setOnDismissCallBack(dismisscallback);` (Optional).
- `show(); / show(1200);` to show it always or for a duration of time.
- `dismiss();` to hide it.

**Getters**
- `getAccentColor()` get accent color.
- `getTitleColor()` get accent color.
- `getBackgroundColor()` get accent color.
- `getTextColor()` get text color.

## ProgressSDialog
![progress](https://te.legra.ph/file/d025c806be8d64eb9ae07.jpg)
Create new instance of ProgressSDialog:
```java
    ProgressSDialog sdialog = new ProgressSDialog(this);
```
Table of methods:

**Setters**
- `setIconResource(icon)` add icon from res.
- `setIconDrawable(icon)` add icon from a Drawable.
- `setIconBitmap(icon)` add icon from bitmap.
- `setTitle("Downloading Files");`
- `setText(text);` string | int res, set text under progress bar (can be changed onProgress)
- `setMin(9);` default is 0.
- `setMax(200);` default is 100.
- `setProgress(120)` set sdialog progress.
- `setOnProgressCallBack(progresscallback)` set on progress changed callback.
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.COLOR_DEFAULT`.
- `setTheme(SDialog.THEME_SYSTEM);` or `THEME_LIGHT / THEME_DARK`.
- `setOnDismissCallBack(dismisscallback);` (Optional).
- `show();` to show it.
- `dismiss();` to hide it.

**Getters**
- `getProgress()`
- `getMax()`
- `getMin()`
- `getAccentColor()`
- `getTitleColor()`
- `getBackgroundColor()`
- `getTextColor()`

## SingleSelectSDialog
![progress](https://te.legra.ph/file/e5f52e32d27c0b0d7ab66.jpg)

Create new instance of SingleSelectSDialog:
```java
    SingleSelectSDialog sdialog = new SingleSelectSDialog(this);
```
Table of methods:

**Setters**
- `setIconResource(icon)` add icon from res.
- `setIconDrawable(icon)` add icon from a Drawable.
- `setIconBitmap(icon)` add icon from bitmap.
- `setTitle("Title");`
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.COLOR_DEFAULT`.
- `setTheme(SDialog.THEME_SYSTEM);` or `THEME_LIGHT / THEME_DARK`.
- `setOnSingleSelectCallBack(singleselectcallback);` get checked radio button id & text.
- `addItem(intId, "item text")` add new item(1, "Holow").
- `setCheckedItem(intId)` set the checked item by default by its id.
- `removeItem(2)` remove an item by its **index** from the list.
- `removeItem("item text")` remove an item by its **text** from the list.
- `setCancelable(false);` (Optional).
- `setOnDismissCallBack(dismisscallback);` (Optional).
- `show();` to show it.
- `dismiss();` to hide it.

**Getters**
- `getItemsList()` returns a list of Map<String, Object> containing all the items, to access their id, text, isChecked use:
> SDialog.KEY_ITEM_ID | KEY_ITEM_TEXT | KEY_ITEM_CHECKED
- `getAccentColor()` get accent color.
- `getTitleColor()` get accent color.
- `getBackgroundColor()` get accent color.
- `getTextColor()` get text color.

## MultiSelectSDialog
![progress](https://te.legra.ph/file/329172778299d06c611af.jpg)

Create new instance of MultiSelectSDialog:
```java
    MultiSelectSDialog sdialog = new MultiSelectSDialog(this);
```
Table of methods:

**Setters**
- `setIconResource(icon)` add icon from res.
- `setIconDrawable(icon)` add icon from a Drawable.
- `setIconBitmap(icon)` add icon from bitmap.
- `setTitle("Title");`
- `setPositiveButton("button text", multiselectcallback)`
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.COLOR_DEFAULT`.
- `setTheme(SDialog.THEME_SYSTEM);` or `THEME_LIGHT / THEME_DARK`.
- `addItem(intId, "item text", isChecked)` add new item(1, "Holow", true).
- `setCheckedItem(intId)` set the checked item by default by its id.
- `removeItem(2)` remove an item by its **index** from the list.
- `removeItem("item text")` remove an item by its **text** from the list.
- `setCancelable(false);` (Optional).
- `setOnDismissCallBack(dismisscallback);` (Optional).
- `show();` to show it.
- `dismiss();` to hide it.

**Getters**
- `getItemsList()` returns a list of Map<String, Object> containing all the items, to access their id, text, isChecked use:
> SDialog.KEY_ITEM_ID | KEY_ITEM_TEXT | KEY_ITEM_CHECKED
- `getAccentColor()` get accent color.
- `getTitleColor()` get accent color.
- `getBackgroundColor()` get accent color.
- `getTextColor()` get text color.

## SliderSDialog
![progress](https://te.legra.ph/file/2a4f52ad59c300bbff95c.jpg)

Create new instance of MultiSelectSDialog:
```java
    SliderSDialog sdialog = new SliderSDialog(this);
```
Table of methods:

**Setters**
- `setIconResource(icon)` add icon from resources.
- `setIconDrawable(icon)` add icon from a Drawable.
- `setIconBitmap(icon)` add icon from bitmap.
- `setTitle("Title");`
- `setText("text hint" || int res)` (Optional) set a hint below title.
- `setPositiveButtonAction("button text", slidercallback)`
- `setNegativeButtonText("Cancel")` negative (cancel) button text.
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.COLOR_DEFAULT`.
- `setTheme(SDialog.THEME_SYSTEM);` or `THEME_LIGHT / THEME_DARK`.
- `setMin(float)` set min value (from).
- `setMax(float)` set max value (to).
- `setValue(float)` set current slider value (from min to max).
- `setStepBy(int)` how likely you want to walk through values in slider.

**Getters:**
- `getMinValue()` get min.
- `getValue()` get value.
- `getMaxValue()` get max.
- `getAccentColor()` get accent color.
- `getTitleColor()` get title color.
- `getBackgroundColor()` get accent color.
- `getTextColor()` get text color.

## CallBacks
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
        public void onItemClick(String itemValue, int itemIndex) {
            // use itemValue to get item text, itemIndex to get item index.
        }
    }
    
    // onProgressCallBack used in progress sdialog
    OnProgressCallBack progresscallback = new OnProgressCallBack() {
        @Override
        public void onProgress(int progress, int percent) {
            // use progress to get the progress 120 of max 200 eg..
            // use percent to get progress percentage 20 of 100% eg..
        }
    }
    
    // onSingleSelect used in single select sdialog
    OnSingleSelectCallBack multiselectcallback = new OnSingleSelectCallBack() {
        @Override
        public void onSelect(int itemId, String itemText) {
            // use itemId to get selected item id
            // use itemText to get selected itemText
        }
    }
    
    // onMultiSelect used in multi select sdialog in positive button
    OnMultiSelectCallBack multiselectcallback = new OnMultiSelectCallBack() {
        @Override
        public void onMultiSelect(List<Map<String, Object>> itemsList) {
            // use itemsList to get all items selected
            // to access their id, text, isChecked use:
            // SDialog.KEY_ITEM_ID | KEY_ITEM_TEXT | KEY_ITEM_CHECKED
        }
    }
    
    OnSlideCallBack slidercallback = new OnSlideCallBack() {
        @Override
        public void onValueSelected(float value) {
            // use value
        }
    }
```

# Donations
> If you would like to support this project's further development, the creator of this projects or the continuous maintenance of the project **feel free to donate**.
Your donation is highly appreciated. Thank you!
<br/>

You can **choose what you want to donate**, all donations are awesome!</br>
<br/>

[![Buy me a coffee](https://img.shields.io/badge/Buy_Me_A_Coffee-FFDD00?style=for-the-badge&logo=buy-me-a-coffee&logoColor=black)](https://www.buymeacoffee.com/HusseinShakir)

<br/>

<p align="center">
  <img src="https://raw.githubusercontent.com/smith8h/smith8h/main/20221103_150053.png" style="width: 38%;"/>
  <br><b>With :heart:</b>
</p>
