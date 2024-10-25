add dependency:
```gradle

repositories {
    maven("https://jitpack.io")
}

dependencies {
    //https://jitpack.io/#centralhardware/ktgbotapi-restrict-access-middleware
    implementation("com.github.centralhardware:ktgbotapi-restrict-access-middleware:<latest-version>")
}
```
install:
```kotlin
telegramBotWithBehaviourAndLongPolling(
        "<TOKEN>",
        CoroutineScope(Dispatchers.IO),
        builder = {
            includeMiddlewares {
                restrictAccess(EnvironmentVariableUserAccessChecker())
            }
        }) {}
```