# Android Sensing Kit

**A**ndroid **S**ensing **K**it (**ASK**) is a development framework
written in Java, designed for large-scale sensing experiments with
Android devices.
It supports multiple sensors such as Location, Motion, and Proximity
(using both Bluetooth and Wi-Fi Direct).
ASK provides also some functionalities that can be used to characterize
the users' behaviour and interests, such as the apps usage statistics,
activity recognition, calls and messaging logs.

Due to its modular design, a developer can easily extend ASK
implementing his/her own functionalities or reusing part of the
built-in features offered by the framework.
In addition, ASK provides a useful set of features to automatically
store and uploads the sensed information to remote servers, simplifying
the data collection during the experiments.

This framework is freely inspired by similar works (e.g.,
[Funf Open Sensing Framework](http://funf.org), or
[SensingKit](https://sensingkit.org)) but, differently from
them, ASK supports both the features and building process of the most
recent versions of the Android operating system.

## Built-in probes

ASK provides a set of built-in probes to gather rich data about the
users' activities, behaviour, and social relationships. The currently
implemented built-in probes are able to acquire the following
data:

   * **Audio**
   * **Battery**
   * **General hardware info**
   * **Display**
   * **Telephony** (cells, calls, and sms/mms)
   * **Calendar** events
   * **Bluetooth** connections and devices in proximity
   * **Wi-Fi** Access Points and **Wi-Fi Direct** devices in proximity
   * **Activity** Recognition
   * **Raw Sensors** (e.g., temperature, light, accelerometer, etc...)
   * **Location**
   * **Applications** (installed and running statistics)



## Installing the library

TODO

## Using the library

### Configuration

TODO

### Start ASK

## License

`Copyright (c) 2017. Mattia Campana, m.campana@iit.cnr.it,
campana.mattia@gmail.com

This file is part of Android Sensing Kit (ASK).

Android Sensing Kit (ASK) is free software: you can redistribute it
and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

Android Sensing Kit (ASK) is distributed in the hope that it will be
useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Android Sensing Kit (ASK).  If not, see
<http://www.gnu.org/licenses/>.`