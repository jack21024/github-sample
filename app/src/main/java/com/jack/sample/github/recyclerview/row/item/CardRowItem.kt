package com.jack.sample.github.recyclerview.row.item

import android.os.Bundle
import com.jack.sample.github.base.recyclerview.item.BaseCardItem
import com.jack.sample.github.recyclerview.row.enums.CardRowType

abstract class CardRowItem (
    val type: CardRowType,
    var bundle: Bundle? = null
) : BaseCardItem()