package com.jack.sample.github.recyclerview.card.item

import com.jack.sample.github.base.recyclerview.item.BaseCardItem
import com.jack.sample.github.home.data.entity.User
import com.jack.sample.github.home.data.entity.UserRepo
import com.jack.sample.github.recyclerview.card.enums.CardType

open class CardItem (
    val id: Int,
    val type: CardType,
    val user: User? = null
) : BaseCardItem()