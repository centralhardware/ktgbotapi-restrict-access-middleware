package me.centralhardware.telegram

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.bot.ktor.middlewares.TelegramBotMiddlewaresPipelinesHandler

class UserAccessDenied: Throwable()

@OptIn(Warning::class)
fun TelegramBotMiddlewaresPipelinesHandler.Builder.restrictAccess(accessChecker: UserAccessChecker) {

    addMiddleware {
        doOnRequestReturnResult { result, request, _ ->
//            if (result.getOrNull() is ArrayList<Any>)
            null
        }
    }

}