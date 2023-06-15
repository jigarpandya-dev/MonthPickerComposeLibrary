# MonthPickerComposeLibrary
A simple library in Jetpack Compose to add a feature to select a month and a year in your app. You can add a dialog or a bottomsheet to pick a month and optionally year also. 

![](https://jitpack.io/v/jigarpandya-dev/MonthPickerComposeLibrary.svg)](https://jitpack.io/#jigarpandya-dev/MonthPickerComposeLibrary)


### Gradle setup

Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}
 ``` 
  
In `build.gradle` of app module, include this dependency

```gradle
dependencies {
    implementation 'com.github.jigarpandya-dev:MonthPickerComposeLibrary:1.0.1'
}
```

## 🚀 Implementation

You can check [/app](/app) directory which includes example application for demonstration.

Adding month picker in your app as a bottom sheet.

```kotlin
           var openSheet by remember { mutableStateOf(false) }
           if (openSheet) 
           {
                    MonthPickerBottomSheet(
                            showYear = true,
                            onDismiss = {
                                openSheet = false
                            },
                            onUpdateMonth = { month, year ->
                                val calendar = Calendar.getInstance()
                                calendar.set(Calendar.MONTH, month)
                                calendar.set(Calendar.YEAR, year)

                                openSheet = false

                                monthValue = " ${
                                    SimpleDateFormat("MMMM").format(calendar.time)
                                } - $year"
                            },
                        )                      
            }                                                    
```

Adding month picker in your app as a dialog.

```kotlin
              var openDialog by remember { mutableStateOf(false) }
              if (openDialog) 
              {
                        MonthPickerDialog(
                            onCancel = { openDialog = false },
                            onUpdateMonth = { month, year ->
                                val calendar = Calendar.getInstance()
                                calendar.set(Calendar.MONTH, month)
                                calendar.set(Calendar.YEAR, year)

                                openDialog = false
                                monthValue = " ${
                                    SimpleDateFormat("MMMM").format(calendar.time)
                                } - $year"
                            },
                        )
                 }
```                 

## 🙋‍♂️ Contribute

Read [contribution guidelines](CONTRIBUTING.md) for more information regarding contribution.

## 💬 Discuss?

Have any questions, doubts or want to present your opinions, views? You're always welcome. You can [start discussions](https://github.com/PatilShreyas/permission-flow-android/discussions).

## 📝 License

```
Copyright 2022 Shreyas Patil

Licensed under the Apache License, Version 2.0 (the "License");

you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


