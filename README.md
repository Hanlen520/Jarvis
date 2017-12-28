[![](https://travis-ci.org/logan62334/Jarvis.svg?branch=master)](https://travis-ci.org/logan62334/Jarvis)

## Jarvis

Android automation support services

### 1、Automatic installation

Automatic installation and application

Once installed on a device, automatically open the auxiliary function page of the phone, register to the system service, manually open the corresponding intelligent installation service

The current adaptation model:

* 华为

* 小米

### 2、Unlock

Wake up and unlock an Android device or emulator

Once installed on a device, turn the device's screen off. If you have a security lock on your device, turning the screen off will also enable the security lock.

```shell
$ adb shell am start -n com.android.jarvis/.Unlock
```

Your device or emulator is awake and unlocked!

### 3、Automatic detection of network status

Automatically detects network status, automatically opens when wifi is closed

```shell
$ adb shell am start -n com.android.jarvis/.Wifi
```

### 4、Settings

Toggle settings in Android device or emulator.

Once installed on a device, you can change the `wifi`, `data` and `animation` settings through the following commands:

To turn on `wifi`:

```shell
$ adb shell am broadcast -a com.android.jarvis.wifi --es setstatus enable
```

To turn off `wifi`:

```shell
$ adb shell am broadcast -a com.android.jarvis.wifi --es setstatus disable
```

To turn on `data`:

```shell
$ adb shell am broadcast -a com.android.jarvis.data_connection --es setstatus enable
```

To turn off `data`:

```shell
$ adb shell am broadcast -a com.android.jarvis.data_connection --es setstatus disable
```

To turn on `animation`:

```shell
$ adb shell am broadcast -a com.android.jarvis.animation --es setstatus enable
```

To turn off `animation`:

```shell
$ adb shell am broadcast -a com.android.jarvis.animation --es setstatus disable
```

##### Notes:

* You have to specify the receiver class if app never executed before:

```shell
$ adb shell am broadcast -a com.android.jarvis.wifi -n com.android.jarvis/.receivers.WiFiConnectionSettingReceiver --es setstatus disable
```

* To change animation setting, app should be granted `SET_ANIMATION_SCALE` permission:

```shell
$ adb shell pm grant com.android.jarvis android.permission.SET_ANIMATION_SCALE
```

* To change locale setting, app should be granted `CHANGE_CONFIGURATION` permission:

```shell
$ adb shell pm grant com.android.jarvis android.permission.CHANGE_CONFIGURATION
```

* On Android 6.0+ you must enable the corresponding permissions for the app first. This can be
done in application settings, Permissions entry.

* Switching mobile data on/off requires the phone to be rooted on Android 5.0+.
Read [this](http://stackoverflow.com/questions/26539445/the-setmobiledataenabled-method-is-no-longer-callable-as-of-android-l-and-later)
StackOveflow thread for more details.

## License

Apache License 2.0
