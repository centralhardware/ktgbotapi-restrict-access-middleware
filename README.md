# KTGBotAPI Restrict Access Middleware

A middleware for the [KTGBotAPI](https://github.com/InsanusMokrassar/ktgbotapi) library that restricts access to your Telegram bot based on user IDs.

## Features

- Restrict bot access to specific Telegram users
- Simple integration with KTGBotAPI
- Configurable access control via environment variables
- Customizable access checking logic

## Installation

### Gradle (Kotlin DSL)

```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.centralhardware:ktgbotapi-restrict-access-middleware:<latest-version>")
}
```

### Gradle (Groovy)

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.centralhardware:ktgbotapi-restrict-access-middleware:<latest-version>'
}
```

## Usage

### Basic Usage with Environment Variables

1. Set the `ALLOWED_USERS` environment variable with a comma-separated list of Telegram user IDs:

```shell
export ALLOWED_USERS=123456789,987654321
```

2. Add the middleware to your bot:

```kotlin
telegramBotWithBehaviourAndLongPolling(
    "<YOUR_BOT_TOKEN>",
    CoroutineScope(Dispatchers.IO),
    builder = {
        includeMiddlewares {
            restrictAccess(EnvironmentVariableUserAccessChecker())
        }
    }
) {
    // Your bot behavior here
}
```

### Custom Access Checker

You can create your own implementation of the `UserAccessChecker` interface to customize access control logic:

```kotlin
class CustomUserAccessChecker : UserAccessChecker {
    // Your custom logic to determine if a user has access
    override fun checkAccess(userId: Long?): Boolean {
        // Example: Allow only specific user IDs
        return userId in setOf(123456789L, 987654321L)
    }
}

// Use your custom checker with the middleware
telegramBotWithBehaviourAndLongPolling(
    "<YOUR_BOT_TOKEN>",
    CoroutineScope(Dispatchers.IO),
    builder = {
        includeMiddlewares {
            restrictAccess(CustomUserAccessChecker())
        }
    }
) {
    // Your bot behavior here
}
```

## How It Works

The middleware intercepts all updates from Telegram and checks if the user who sent the update is allowed to access the bot. If the user is not allowed, the update is replaced with an `UnknownUpdate` containing an `AccessDeniedException`, effectively preventing the bot from processing the update.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

Alexey Fedechkin, 2025
