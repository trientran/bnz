## Build tools & versions used

- Android Gradle Plugin: 7.4.2
- Gradle: 8.0.1
- compileSdkVersion: 33
- Kotlin: 1.7.20
- Embedded JDK: 17.0.6
- Android Studio Flamingo: 2022.2.1

## Dependencies/Copied code

- This project is based on an app I have been developing called Modular MLKit to gain experience
  with Machine Learning for mobile. Specifically, the code from the Domain layer (compose, core,
  images, logging, view, and view-model modules) in this project has been selectively copied over,
  while the schools module (School API) and the home screen (mobile module) were newly
  written.

- List of libraries used in this project:

  [AndroidX/KotlinX](https://github.com/androidx/androidx) libraries (including Corountine, Flow,
  Compose, test libs..)
  [Retrofit](https://square.github.io/retrofit/)
  [Compose coil](https://coil-kt.github.io/coil/compose/) (async image loading)
  [Dagger Hilt](https://dagger.dev/hilt/)
  [Timber (logging)](https://github.com/JakeWharton/timber)
  [Squareup Moshi](https://github.com/square/moshi)
  [Squareup OkHttp](https://square.github.io/okhttp/)
  ...
