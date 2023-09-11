# SDialog Changelog

## 4.4  
> (Sep 12, 2023)

### Fixes & Changes

- Fix adding new items to `MenuSDialog`, now you can add new list to existing items or update them with whole new list.
- Fix throwing `NullPointerException` when calling `setCancelable()` before `setView()` in `CustomSDialog`, read docs to see changes in creating new instance of `CustomSDialog`.
- Fix throwing `NullPointerException` when passing a view containing IDs same to SDialog view's IDs in `CustomSDialog`.
- Add `Deprecated` warning to all unsupported methods in `CustomSDialog`.

### New Features & Improvements

- Add new text input field to accept user feedback in `FeedbackSDialog`.
- Add new method `setInputHint(text)` to `FeedbackSDialog`.

**Full Changelog**: [4.3...4.4](https://github.com/smith8h/SDialogs/compare/4.3...4.4)

## 4.3
> (Aug 19, 2023)

- Remove buggy `PatternSDialog` (No longer supported and maintained due to some hard bugs and error).
- Fixing some bugs.

**Full Changelog**: [4.2...4.3](https://github.com/smith8h/SDialogs/compare/4.2...4.3)

## 4.2
> (Aug 12, 2023)

- Fix `ItemsSDialog` items alignment.
- Fix libraries transitive issue.
- New method `setMaxWidth` for `AlertSDialog`, `SingleSelectSDialog`, `ItemsSDialog` and `MultiSelectSDialog`.

**Full Changelog**: [4.1...4.2](https://github.com/smith8h/SDialogs/compare/4.1...4.2)

## 4.1
> (Aug 6, 2023)

- New method `setMaxHeight` for `AlertSDialog`, `ItemsSDialog`, `SingleSelectSDialog` and `MultiSelectSDialog` to set a max height for the scrollview and recyclerview inside them and ignore content goes out of screen.
- New methods `setPatternViewHeight` and `setPatternViewWidth` for `PatternSDialog` to set height and width for the pattern view.
- Fix `AlertSDialog` center aligning.
- Some bug fixes.

**Full Changelog**: [4.0...4.1](https://github.com/smith8h/SDialogs/compare/4.0...4.1)

## 4.0
> (Aug 1, 2023)

### Fixes & Changes

- Fix `neutral` button was always `null` in `AlertSDialog`.
- Fix `PatternSDialog` center alignment.
- Fix `SliderSDialog` track height `onProgressChanged`.
- Migrate adapters to Kotlin.
- Migrate Interfaces to Kotlin.

### New Features & Improvements

- New `Feedback` type of SDialog.
- Update `PatternLockView` and fix `jcenter()` issues, **THANKS TO [kimoandroid](https://github.com/kimoandroid)**.
- Improve `ProgressSDialog` progress design (SDK 29+ will be Material themed, and SDK 28- will be normal progress).
- Redesign `AlertSDialog`.
- Add support for fragments & multi threading.
- All types of SDialog now support adding title from `strings.xml` resource.
- Update calculating lighter & darker colors using SColor lib.
- New `PatternSDialog` callback method `onDrawingProgress` will trigger whenever the pattern being drawn, and return the current pattern progress as string.
- New `dismiss(duration)` method, allows you to dismiss the SDialogs automatically after a duration of time in milliseconds.
- All dialogs now supports RTL (Right to Left) layout direction.
- Add a new negative (Cancel button) to `ProgressSDialog` to cancel the current running process.

### New Contributor

<a href="https://github.com/smith8h/sdialogs/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=kimoandroid/glide-slider" title="kimoandroid"/>
</a>
<br/>

**Full Changelog**: [3.0...4.0](https://github.com/smith8h/SDialogs/compare/3.0...4.0)

## 3.0
> (May 2, 2023)

- New SDialog type `PatternSDialog`.
- `ItemsSDialog` has a new method `setNegativeButtonText(String)` to set a negative (like Cancel) button when needed.

**Full Changelog**: [2.0...3.0](https://github.com/smith8h/SDialogs/compare/2.0...3.0)

## 2.0
> (Apr 27, 2023)

- Add new method 'setInputFieldText()' for `InputSDialog`.
- Support adding icon to all dialogs (except for `CustomSDialog` and `LoadingSDialog`).
- `ProgressSDialog`, `LoadingSDialog`, `InputSDialog` and `AlertSDialog` now has a method of adding text from resource `R.string.text`.
- `AlertSDialog` now has the ability to accept `Spannable` text too!.
- New SDialog type `SliderSDialog`.

**Full Changelog**: [1.0.1...2.0](https://github.com/smith8h/SDialogs/compare/1.0.1...2.0)

## 1.0.1
>(Dec 10, 2022)

- Bug fixes.
- Code improvements.
- New Type of SDialogs.

**Full Changelog**: [1.0...1.0.1](https://github.com/smith8h/SDialogs/compare/1.0...1.0.1)

## 1.0
> (Dec 7, 2022)

- First release.