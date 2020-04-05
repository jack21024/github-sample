package com.jack.sample.github.detail.ui.viewholder

import android.view.View
import android.view.ViewGroup
import com.jack.sample.github.base.recyclerview.item.BaseCardItem
import com.jack.sample.github.base.recyclerview.viewholder.BaseViewHolder
import com.jack.sample.github.recyclerview.row.item.CardRowItem
import com.jack.sample.github.R

class DetailViewHolder(
    parent: ViewGroup,
    private val onCardClicked: ((item: BaseCardItem, view: View) -> Unit)?
) : BaseViewHolder<CardRowItem>(R.layout.list_item_detail, parent) {

    private var data: CardRowItem? = null

    override fun bind(data: CardRowItem) {
        this.data = data
    }

    override fun onItemClicked(view: View) {
        data?.let {
            onCardClicked?.invoke(it, view)
        }
    }
}