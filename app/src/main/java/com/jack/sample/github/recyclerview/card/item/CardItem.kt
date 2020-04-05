package com.jack.sample.github.recyclerview.card.item

import android.os.Bundle
import com.jack.sample.github.base.recyclerview.item.BaseCardItem
import com.jack.sample.github.home.data.entity.User
import com.jack.sample.github.recyclerview.card.enums.CardType

open class CardItem (
    val id: Int,
    val type: CardType,
    val user: User? = null,
    var bundle: Bundle? = null
) : BaseCardItem() {

    constructor(user: User): this(
        id = user.id.toInt(),
        type = CardType.AVATAR,
        user = user,
        bundle = Bundle().apply {
            putSerializable(BUNDLE_OBJ_USER, user)
        }
    )

    companion object {
        const val BUNDLE_OBJ_USER: String = "BUNDLE_OBJ_USER"
    }
}