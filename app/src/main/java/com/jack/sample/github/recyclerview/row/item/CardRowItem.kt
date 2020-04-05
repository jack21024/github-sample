package com.jack.sample.github.recyclerview.row.item

import com.jack.sample.github.base.recyclerview.item.BaseCardItem
import com.jack.sample.github.recyclerview.row.enums.CardRowType

abstract class CardRowItem (
    val type: CardRowType
) : BaseCardItem()