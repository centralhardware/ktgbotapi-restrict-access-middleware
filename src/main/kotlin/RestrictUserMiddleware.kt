package me.centralhardware.telegram

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.bot.ktor.middlewares.TelegramBotMiddlewaresPipelinesHandler
import dev.inmo.tgbotapi.types.update.abstracts.Update

class UserAccessDenied: Throwable()

@OptIn(Warning::class)
fun TelegramBotMiddlewaresPipelinesHandler.Builder.restrictAccess(accessChecker: UserAccessChecker) {

    addMiddleware {
        doOnAfterCallFactoryMakeCall { result, request, _ ->
            if (result != null && result !is ArrayList<*>) return@doOnAfterCallFactoryMakeCall result

            return@doOnAfterCallFactoryMakeCall (result as ArrayList<Update>).filter {
                return@filter accessChecker.checkAccess(it.chatId())
            }
        }
    }

}