package me.centralhardware.telegram

import dev.inmo.tgbotapi.extensions.utils.extensions.raw.from
import dev.inmo.tgbotapi.types.update.*
import dev.inmo.tgbotapi.types.update.abstracts.UnknownUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update

fun Update.chatId(): Long? {
    return when (this) {
        is EditMessageUpdate -> data.from?.id?.chatId?.long
        is MessageUpdate -> data.from?.id?.chatId?.long
        is EditChannelPostUpdate -> data.from?.id?.chatId?.long
        is ChannelPostUpdate -> null
        is ChosenInlineResultUpdate -> data.from.id.chatId.long
        is InlineQueryUpdate -> data.from.id.chatId.long
        is CallbackQueryUpdate -> data.from.id.chatId.long
        is ShippingQueryUpdate -> data.from.id.chatId.long
        is PreCheckoutQueryUpdate -> data.from.id.chatId.long
        is PollUpdate -> null
        is PollAnswerUpdate -> null
        is MyChatMemberUpdatedUpdate -> null
        is CommonChatMemberUpdatedUpdate -> null
        is ChatJoinRequestUpdate -> null
        is ChatMessageReactionUpdatedUpdate -> null
        is ChatMessageReactionsCountUpdatedUpdate -> null
        is ChatBoostUpdatedUpdate -> null
        is ChatBoostRemovedUpdate -> null
        is BusinessConnectionUpdate -> null
        is BusinessMessageUpdate -> data.from.id.chatId.long
        is EditBusinessMessageUpdate -> null
        is DeletedBusinessMessageUpdate -> null
        is PaidMediaPurchasedUpdate -> null
        is UnknownUpdate -> null
        else -> null
    }
}
