package me.centralhardware.telegram

import dev.inmo.kslog.common.KSLog
import dev.inmo.kslog.common.info
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
                val permitted = accessChecker.checkAccess(it.chatId())
                if (!permitted) {
                    KSLog.info("filter out update from unauthorized user ${it.chatId()}")
                }
                return@filter permitted
            }
        }
    }

}