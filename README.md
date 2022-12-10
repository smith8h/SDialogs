# SDialogs
Beautiful custom android dialogs ( alert, multiselect checkbox, singleselect radiobutton, string list items, loading, progress, input and custom sdialog ).

# Menu Of Contents
- [How to setup](#setup)
- [How to implement (Documentation)](#documentation)
  - [AlertSDialog](#alertsdialog)
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
- `setPositiveButton("Button Text", () -> {...});` (Optional) the code here in java8, to use java7 or below syntax see in [CallBacks](#callbacks).
- `setNegativeButton("Button Text", () -> {...});` (Optional).
- `setNeutralButton("Button Text", () -> {...});` (Optional).
- `setAccentColor(int color/string hex color);` Default color is 0xFFA7B4C5/#FFA7B4C5 you can access it by `SDialog.DEFAULT_COLOR`.
- `setTheme(SDialog.SYSTEM_THEME);` or `LIGHT_THEME / DARK_THEME`.
- `setCancelable(false);` (*Optional).
- `setOnDismissCallBack(() -> {...});` (Optional).
- `show(); / show(1200);` to show always or for a duration of time (auto hide).
- `dismiss();` to hide it.

## CallBacks