# SDialogs
Beautiful custom android dialogs ( alert, multiselect checkbox, singleselect radiobutton, string list items, loading, progress, input and custom sdialog ).

# Menu Of Contents
- [How to setup](#setup)
- [How to implement (Documentation)](#documentation)
  - [AlertSDialog](#alertsdialog)
  - [CustomSDialog](#customsdialog)
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
This library composed of 7 types of **SDialogs** and an additional one type that represent the customizable **SDialog** (use a custom view/layout of your own design and logic) for fast and small codes of initializing new dialog.
</br>
All these SDialogs using an algorithm to calculate and extract colors lighter/darker from the accent color you set in method `setAccentColor()`. 

## AlertSDialog
Create new instance of AlertSDialog:
```java
    AlertSDialog sdialog = new AlertSDialog(this);
```
Table of methods:
- `setTitle("Title");`
- `setText("Text...");`
- `setPositiveButton("Button Text", clickcallback);` (Optional) the code here in java8, to use java7 or below syntax see in [CallBacks](#callbacks).
- `setNegativeButton("Button Text", clickcallback);` (Optional) onClick callback used.
- `setNeutralButton("Button Text", clickcallback);` (Optional) .
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.DEFAULT_COLOR`.
- `setTheme(SDialog.SYSTEM_THEME);` or `LIGHT_THEME / DARK_THEME`.
- `setCancelable(false);` (*Optional).
- `setOnDismissCallBack(dismisscallback);` (Optional).
- `show(); / show(1200);` to show always or for a duration of time (auto hide).
- `dismiss();` to hide it.

## CustomSDialog
Create new instance of CustomSDialog:
```java
    AlertSDialog sdialog = new AlertSDialog(this);
```
Table of methods:
- `setView(view, bindviewcallback);` set a view and liatener onBindCustomView.
- `setView(R.layout.your_layout_name, bindviewcallback);` set a int layout from resources and liatener onBindCustomView.
- `setCancelable(false);` (*Optional).
- `setOnDismissCallBack(dismisscallback);` (Optional).
- `show(); / show(1200);` to show always or for a duration of time (auto hide).
- `dismiss();` to hide it.

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
    OnBindCustomViewCallBack bindviewcallback = OnBindCustomViewCallBack() {
        @Override
        public void onBindCustomView(View customView) {
            
        }
    };
```