# Video-Calling-App

Hey there! 👋🏼👋🏼👋🏼

This is an android application that demonstrates video calling functionality using Jetpack Compose and [Stream](https://getstream.io/) SDK.


### Tools and Libraries
- Jetpack Compose
- Clean Architecture & MVVM
- Dependency Injection using Koin
- Stream SDK


### Prerequisite
To build this project, you require:

- Android Studio Canary version and up
- Gradle 7.4.2
- Kotlin 1.9.0
- Compose 1.5.0
- Koin 3.5.3


### Steps
- The application uses Stream SDK whose documentation can be found [here](https://getstream.io/). You are required to create an account and an application, to get an API key for that app.

- After generating your API key, put it in your local.properties file, which usually gets ignored by default by the git version control system (VCS).

- Install the Google Secrets dependency which hides secrets so they don't get checked into git VCS. Do this by placing "id 'com.google.android.libraries.mapsplatform.secrets-gradle-plugin' in your build.gradle file module and project level respectively.

- Sync the project and Android studio generates a constant representing your API key in your BuildConfig class. You can then use this value in your application securely.