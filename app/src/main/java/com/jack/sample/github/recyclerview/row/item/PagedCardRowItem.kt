package com.jack.sample.github.recyclerview.row.item

import androidx.paging.PagedList
import com.jack.sample.github.recyclerview.card.item.CardItem
import com.jack.sample.github.recyclerview.row.enums.CardRowType

class PagedCardRowItem (
    val pagedList: PagedList<CardItem>
) : CardRowItem(CardRowType.PAGED_CARDS)