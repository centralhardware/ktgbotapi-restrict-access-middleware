package me.centralhardware.telegram

import dev.inmo.kslog.common.KSLog
import dev.inmo.kslog.common.info
import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.bot.ktor.middlewares.TelegramBotMiddlewaresPipelinesHandler
import dev.inmo.tgbotapi.types.update.abstracts.UnknownUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.serialization.json.JsonObject

class AccessDeniedException : Exception("User access restricted")

@OptIn(Warning::class)
fun TelegramBotMiddlewaresPipelinesHandler.Builder.restrictAccess(accessChecker: UserAccessChecker) {

    addMiddleware {
        doOnRequestResultPresented { result, _, _, _ ->
            when {
                result != null && result !is ArrayList<*> -> result
                result != null && result is ArrayList<*> -> (result as ArrayList<Update>).map {
                    val permitted = accessChecker.checkAccess(it.chatId())
                    if (permitted) {
                        it
                    } else {
                        KSLog.info("filter out ${it::class.simpleName} from unauthorized user ${it.chatId()}")
                        UnknownUpdate(it.updateId, JsonObject(mapOf()), AccessDeniedException())
                    }
                }
                else -> result
            }
        }
    }

}