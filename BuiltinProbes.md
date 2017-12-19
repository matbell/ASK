# Built-in Probes #


* **General Information**
    * [Audio](#SKAudioProbe)
    * [Battery](#SKBatteryProbe)
    * [Hardware](#SKHardwareInfoProbe)
    * [Display](#SKDisplayProbe)
* **Telephony**
    * [Cells Information](#SKCellInfoProbe)
    * [Phone calls](#SKCallsProbe)
* **Calendar**
    * [SKCalendarProbe](#SKCalendarProbe)
    * [SKCurrentEventsProbe](#SKCurrentEventsProbe)
* **Bluetooth**
    * [Bluetooth connections](#SKBluetoothConnProbe)
    * [Bluetooth scans](#SKBluetoothScanProbe)
* **Wi-Fi**
    * [Wi-Fi Access Points](#SKWiFiProbe)
    * [Wi-Fi P2P scans](#SKWiFiP2PProbe)
* **Sensors**
    * [Activity Recognition](#SKActivityRecognitionProbe)
    * [Environment](#SKEnvironmentSensorProbe)
    * [Motion](#SKMotionSensorsProbe)
    * [Position](#SKPositionSensorProbe)
* **Applications**
    * [Installed](#SKInstalledAppsProbe)
    * [Running](#SKRunningAppsProbe)


[//]: <> (=================== SK Audio Probe ==========================)


## <a name="SKAudioProbe"></a>SKAudioProbe

Monitors different events related to the audio system.

#### Returns

Returns the following list of values:

NAME                | Value and Description
--------------------|--------------------------------------------
RING_MODE           | _0_ (Silent), _1_ (Vibrate), _2_ (Normal)
ALARM_VOLUME        | _\[0, 1\]_ Current volume level of the alarm.
MUSIC_VOLUME        | _\[0, 1\]_ Current volume level of the music.
NOTIFICATION_VOLUME | _\[0, 1\]_ Current volume level of the notifications.
BT_SCO              | _TRUE/FALSE_ if a Bluetooth SCO device is connected.
MIC_MUTE            | _TRUE/FALSE_ if the microphone is mute.
MUSIC_ON            | _TRUE/FALSE_ if the music is on.
SPEAKER_ON          | _TRUE/FALSE_ if the speaker is on.
HEADSET             | _TRUE/FALSE_ if the headset (bot wired and Bluetooth) are connected


[//]: <> (================== SK Battery Probe =========================)


## <a name="SKBatteryProbe"></a>SKBatteryProbe

Monitors the battery status.

#### Returns

Returns the following list of values:

NAME                | Value and Description
--------------------|--------------------------------------------
BATTERY_PCT         | _\[0, 1\]_ Current battery level in percentage.
CHARGING            | _TRUE/FALSE_ if the device is charging.
USB_CHARGE          | _TRUE/FALSE_ if the device is charging through an USB cable.
AC_CHARGE           | _TRUE/FALSE_ if the device is charging through an AC charger.
WIRELESS_CHARGE     | _TRUE/FALSE_ if the device is charging through a Wireless charger.


[//]: <> (================= SK Hardware Probe =========================)


## <a name="SKHardwareInfoProbe"></a>SKHardwareInfoProbe

Fetches different information related to the device's hardware.
Some information can be used to uniquely identify the user/device.

#### Required Permissions

```
android.permission.ACCESS_WIFI_STATE
android.permissionCHANGE_WIFI_STATE
android.permission.BLUETOOTH
android.permission.READ_PHONE_STATE
android.permission.INTERNET
```

#### Returns

Returns the following information:

NAME                | Value and Description
--------------------|--------------------------------------------
ANDROID_ID          | A 64-bit number, unique to each combination of app-signing key, user, and device. This is only available on Android API >= 26.
WI_FI_MAC           | The MAC address associated to the Wi-Fi interface
WI_FI_P2P_MAC       | The MAC address associated to the Wi-Fi P2P interface
BT_MAC              | The MAC address associated to the Bluetooth interface
BRAND               | The device's brand
MODEL               | The device's model
MANUFACTURER        | The device's manufacturer
DEVICE_ID           | The unique device ID (i.e., the IMEI for GSM and the MEID or ESN for CDMA phones)


[//]: <> (=================== SK Display Probe ========================)


## <a name="SKDisplayProbe"></a>SKDisplayProbe

Monitors the display status.

#### Returns

Returns the following information:

NAME                | Value and Description
--------------------|--------------------------------------------
STATE               | _0_ (UNKNOWN), _1_ (OFF), _2_ (ON), _3_ (DOZE), _4_ (DOZE_SUSPEND). See the Android's [Display](https://developer.android.com/reference/android/view/Display.html) class.
ROTATION            | _0_ (NO_ROTATION), _1_ (ROTATION_90), _2_ (ROTATION_180), _3_ (ROTATION_270). See the Android's [getRotation()](https://developer.android.com/reference/android/view/Display.html#getRotation()) method.


[//]: <> (================= SK Cell Info Probe ========================)


## <a name="SKCellInfoProbe"></a>SKCellInfoProbe

Monitors all observed cell information from all radios on the device
including the primary and neighboring cells.

#### Required Permissions

```
android.permission.ACCESS_COARSE_LOCATION
android.permission.READ_PHONE_STATE
```

#### Returns

For each device in proximity, returns the following information:

NAME                | Value and Description
--------------------|--------------------------------------------
CELL_TYPE           | _0_ (GSM), _1_ (LTE), _2_ (CDMA), _3_ (WCDMA)
BS_ID               | The Base Station ID. Refer to the [android.telephony.CellInfo](https://developer.android.com/reference/android/telephony/CellInfo.html) subclasses for the exact values.
SIGNAL_LEVEL        | The signal strength as dBm.


[//]: <> (==================== SK Calls Probe =========================)


## <a name="SKCallsProbe"></a>SKCallsProbe

Monitors both incoming and outgoing phone calls.

#### Required Permissions

```
android.permission.PROCESS_OUTGOING_CALLS
android.permission.READ_PHONE_STATE
```

#### Returns

For each phone call, returns the following information:

NAME                | Value and Description
--------------------|--------------------------------------------
PHONE_NUMBER        | Phone number
INCOMING            | _TRUE/FALSE_ if it is an incoming call


[//]: <> (================== SK Calendar Probe ========================)


## <a name="SKCalendarProbe"></a>SKCalendarProbe

Monitors the calendar events.

#### Required Permissions

```
android.permission.READ_CALENDAR
```

#### Returns

For each event, returns the following information:

NAME                | Value and Description
--------------------|--------------------------------------------
CALENDAR_NAME       | Name of the calendar
START               | When the event starts
END                 | When the event ends
TITLE               | The event's title
LOCATION            | Location of the event (if present)
ALL_DAY             | _TRUE/FALSE_ if the event lasts all the day


[//]: <> (================== SK Calendar Probe ========================)


## <a name="SKCurrentEventsProbe"></a>SKCurrentEventsProbe

Monitors only the calendar events that are active at that time.

#### Required Permissions

```
android.permission.READ_CALENDAR
```

#### Returns

For each event, returns the following information:

NAME                | Value and Description
--------------------|--------------------------------------------
START               | When the event starts
END                 | When the event ends
TITLE               | The event's title
LOCATION            | Location of the event (if present)
ALL_DAY             | _TRUE/FALSE_ if the event lasts all the day


[//]: <> (============= SK Bluetoot Connections Probe =================)


## <a name="SKBluetoothConnProbe"></a>SKBluetoothConnProbe

Monitors the connections to bluetooth devices.

#### Required Permissions

```
android.permission.BLUETOOTH
```

#### Returns

For each connected device, returns the following information:

NAME                | Value and Description
--------------------|--------------------------------------------
ADDRESS             | The device's Bluetooth MAC address.
BT_CLASS            | The device's [Bluetooth class](https://developer.android.com/reference/android/bluetooth/BluetoothClass.Device.html).


[//]: <> (================ SK Bluetoot Scans Probe ====================)


## <a name="SKBluetoothScanProbe"></a>SKBluetoothScanProbe

Continuously performs Bluetooth scans to discover other devices in
proximity.

#### Required Permissions

```
android.permission.BLUETOOTH
android.permission.BLUETOOTH_ADMIN
```

#### Returns

For each device in proximity, returns the following information:

NAME                | Value and Description
--------------------|--------------------------------------------
ADDRESS             | The device's Bluetooth MAC address.
BT_CLASS            | The device's [Bluetooth class](https://developer.android.com/reference/android/bluetooth/BluetoothClass.Device.html).


[//]: <> (===================== SK Wi-Fi Probe ========================)


## <a name="SKWiFiP2PProbe"></a>SKWiFiP2PProbe

Continuously performs Wi-Fi P2P scans to discover other devices in
proximity.

```
android.permission.CCESS_COARSE_LOCATION
android.permission.ACCESS_WIFI_STATE
android.permission.CHANGE_WIFI_STATE
android.permission.INTERNET
```

#### Returns

For device in proximity, returns the following information:

NAME                | Value and Description
--------------------|--------------------------------------------
ADDRESS             | The Wi-Fi P2P Mac address


[//]: <> (================== SK Wi-Fi P2P Probe =======================)


## <a name="SKWiFiProbe"></a>SKWiFiProbe

Continuously performs Wi-Fi scans to discover Access Points in proximity.

```
android.permission.CCESS_COARSE_LOCATION
android.permission.ACCESS_WIFI_STATE
android.permission.CHANGE_WIFI_STATE
android.permission.ACCESS_NETWORK_STATE
```

#### Returns

For Access Point (AP) in proximity, returns the following information:

NAME                | Value and Description
--------------------|--------------------------------------------
SSID                | The AP's SSID
BSSID               | The AP's BSSID
RSSI                | The RSSI level in the \[0,4\] range
SIG_LEVEL           | The raw RSSI level
CAPABILITIES        | Authentication, key management, and encryption schemes supported by the AP
FREQUENCY           | The primary 20 MHz frequency (in MHz) of the channel over which the client is communicating with the access point
CONNECTED           | _TRUE/FALSE_ if the device is currently connected to the Access Point


[//]: <> (============= SK Activity Recognition Probe =================)


## <a name="SKActivityRecognitionProbe"></a>SKActivityRecognitionProbe

Monitors the current user activity using the Android's [Activity
Recognition API](https://developers.google.com/location-context/activity-recognition/).

#### Parameters

```java
long updateInterval
```
Controls the activity detection update interval (in <b>seconds</b>).
Larger values will result in fewer activity detections while improving
battery life.
Smaller values will result in more frequent activity detections but
will consume more power since the device must be woken up more
frequently.

**Default value**: 300 seconds (i.e., 5 minutes).

#### Required Permissions

```
com.google.android.gms.permission.ACTIVITY_RECOGNITION
```

#### Returns

Returns the following list of activities, where each of them is
associated to its confidence value (i.e., an int value in \[0, 100\])

Activity      | Description
--------------|--------------------------------------------
IN_VEHICLE    | The device is in a vehicle, such as a car.
ON_BICYCLE    | The device is on a bicycle.
ON_FOOT       | The device is on a user who is walking or running.
RUNNING       | The device is on a user who is running.
STILL         | The device is still (not moving).
TILTING       | The device angle relative to gravity changed significantly.
WALKING       | The device is on a user who is walking.
UNKNOWN       | Unable to detect the current activity.


[//]: <> (============== SK Environment Sensors Probe =================)


## <a name="SKEnvironmentSensorProbe"></a>SKEnvironmentSensorProbe

Monitors sensors that measure various environmental parameters, such as
ambient air temperature and pressure, illumination, and humidity.
Specifically, this probe monitors the following sensors:

* **TEMPERATURE** : Ambient air temperature in °C.
* **LIGHT** : Illuminance in lx.
* **PRESSURE** : Ambient air pressure in hPa or mbar, it depends on the hardware.
* **HUMIDITY** : The percentage of the ambient relative humidity.

For a full description of these sensors, please refer to the [Android's
official documentation](https://developer.android.com/guide/topics/sensors/sensors_environment.html).

#### Parameters

```java
int maxSamples
```
Maximum number of samples for each sensor to use for the statistics.

#### Returns

For each sensor, returns the following statistics calculated by the
[Apache Commons Math](http://commons.apache.org/proper/commons-math/javadocs/api-3.3/org/apache/commons/math3/stat/descriptive/DescriptiveStatistics.html) library:

NAME                | Value and Description
--------------------|--------------------------------------------
MIN                 | Minimum
MAX                 | Maximum
MEAN                | Arithmetic mean
UAD_MEAN            | Quadratic mean
25_PERC             | 25<sup>th</sup> Percentile
50_PERC             | 50<sup>th</sup> Percentile
75_PERC             | 75<sup>th</sup> Percentile
100_PERC            | 100<sup>th</sup> Percentile
VAR                 | Variance
POP_VAR             | Population variance
SKEW                | Skewness
SUM_SQ              | Sum of the squares
STD                 | Standard deviation
KURT                | Kurtosis

Therefore, for each reading, this probe returns an array of
4 (Sensors' dimensions) * 14 (Statistics) = **56 elements**.


[//]: <> (================ SK Motion Sensors Probe ====================)


## <a name="SKMotionSensorsProbe"></a>SKMotionSensorsProbe

Monitors sensors that measure acceleration forces and rotational forces
along three axes. This category includes accelerometers, gravity
sensors, gyroscopes, and rotational vector sensors.
Specifically, this probe monitors the following sensors:

* **ACCELEROMETER** : Acceleration force along the _x,y,z_ axes in m/s<sup>2</sup>
* **GRAVITY** : Force of gravity along the _x,y,z_ axes in m/s<sup>2</sup>
* **GYROSCOPE** : Rate of rotation along the _x,y,z_ axes in rad/s
* **ACCELERATION** : Acceleration force along the _x,y,z_ axes in m/s<sup>2</sup>
* **ROTATION** : Rotation vector component along the _x,y,z_ axes

For a full description of these sensors, please refer to the [Android's
official documentation](https://developer.android.com/guide/topics/sensors/sensors_motion.html).

#### Parameters

```java
int maxSamples
```
Maximum number of samples for each sensor to use for the statistics.

#### Returns

For each sensor, returns the following statistics calculated by the
[Apache Commons Math](http://commons.apache.org/proper/commons-math/javadocs/api-3.3/org/apache/commons/math3/stat/descriptive/DescriptiveStatistics.html) library:

NAME                | Value and Description
--------------------|--------------------------------------------
MIN                 | Minimum
MAX                 | Maximum
MEAN                | Arithmetic mean
UAD_MEAN            | Quadratic mean
25_PERC             | 25<sup>th</sup> Percentile
50_PERC             | 50<sup>th</sup> Percentile
75_PERC             | 75<sup>th</sup> Percentile
100_PERC            | 100<sup>th</sup> Percentile
VAR                 | Variance
POP_VAR             | Population variance
SKEW                | Skewness
SUM_SQ              | Sum of the squares
STD                 | Standard deviation
KURT                | Kurtosis

Therefore, for each reading, this probe returns an array of
15 (Sensors' dimensions) * 14 (Statistics) = **210 elements**.


[//]: <> (============== SK Position Sensors Probe ====================)


## <a name="SKPositionSensorProbe"></a>SKPositionSensorProbe

Monitors sensors that measure the physical position of a device. This
category includes orientation sensors and magnetometers.
Specifically, this probe monitors the following sensors:

* **GAME ROTATION** : Rotation vector component along the _x,y,z_ axes
* **GEOMAGNETIC ROTATION** : Rotation vector component along the _x,y,z_ axes
* **MAGNETIC FIELD** : Geomagnetic field strength along the _x,y,z_ axes in μT
* **PROXIMITY** : Distance from object in cm

For a full description of these sensors, please refer to the [Android's
official documentation](https://developer.android.com/guide/topics/sensors/sensors_position.html).

#### Parameters

```java
int maxSamples
```
Maximum number of samples for each sensor to use for the statistics.

#### Returns

For each sensor, returns the following statistics calculated by the
[Apache Commons Math](http://commons.apache.org/proper/commons-math/javadocs/api-3.3/org/apache/commons/math3/stat/descriptive/DescriptiveStatistics.html) library:

NAME                | Value and Description
--------------------|--------------------------------------------
MIN                 | Minimum
MAX                 | Maximum
MEAN                | Arithmetic mean
UAD_MEAN            | Quadratic mean
25_PERC             | 25<sup>th</sup> Percentile
50_PERC             | 50<sup>th</sup> Percentile
75_PERC             | 75<sup>th</sup> Percentile
100_PERC            | 100<sup>th</sup> Percentile
VAR                 | Variance
POP_VAR             | Population variance
SKEW                | Skewness
SUM_SQ              | Sum of the squares
STD                 | Standard deviation
KURT                | Kurtosis

Therefore, for each reading, this probe returns an array of
10 (Sensors' dimensions) * 14 (Statistics) = **140 elements**.


[//]: <> (================ SK Installed Apps Probe ====================)


## <a name="SKInstalledAppsProbe"></a>SKInstalledAppsProbe

Monitors the installed applications.

#### Returns

For each application, returns the following information:

NAME                | Value and Description
--------------------|--------------------------------------------
PKG_NAME            | Name of the application's package


[//]: <> (================= SK Running Apps Probe =====================)


## <a name="SKRunningAppsProbe"></a>SKRunningAppsProbe

Monitors the running applications.

#### Returns

For each application, returns the following information:

NAME                | Value and Description
--------------------|--------------------------------------------
NAME                | Process name
IMPORTANCE          | Level of importance as defined in [RunningAppProcessInfo](https://developer.android.com/reference/android/app/ActivityManager.RunningAppProcessInfo.html#importance), e.g., if the app is in foreground or background mode.

