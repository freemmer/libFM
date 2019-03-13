![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)
[![](https://jitpack.io/v/freemmer/libFM.svg)](https://jitpack.io/#freemmer/libFM)

# libFM
Logger, BeanManager, Utils etc for Android Library (Kotlin)
- [Contains the FMCheckPermission repository](https://github.com/freemmer/FMCheckPermission) v1.2.2
- [Contains the FMNotification repository](https://github.com/freemmer/FMNotification) v1.0.1

## Demo ScreenShot
+ Basic log.
![Screenshot](https://github.com/freemmer/libFM/blob/master/Screenshots/logcat_basic.jpg)

+ When notification message is tapped.
![Screenshot](https://github.com/freemmer/libFM/blob/master/Screenshots/logcat_tapped_notification.jpg)

## Setup
★ By default, Must be FCM ready for use. (Register your app to use FCM with Firebase and apply google-services.json)

Project build.gradle
```Groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

App build.gradle
```Groovy
dependencies {
    implementation 'com.github.freemmer:libFM:1.2.6'
}
```

## How to use
Reference
- [Contains the FMCheckPermission repository](https://github.com/freemmer/FMCheckPermission)
- [Contains the FMNotification repository](https://github.com/freemmer/FMNotification)

## License 
```code
This software is licensed under the [Apache 2 license](LICENSE), quoted below.

Copyright 2019 freemmer. <http://freemmer.tistory.com>

Licensed under the Apache License, Version 2.0 (the "License"); you may not
use this project except in compliance with the License. You may obtain a copy
of the License at http://www.apache.org/licenses/LICENSE-2.0.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.
```
