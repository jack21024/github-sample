package com.jack.sample.github.recyclerview.card

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.jack.sample.github.base.recyclerview.item.BaseCardItem
import com.jack.sample.github.base.recyclerview.viewholder.BaseViewHolder
import com.jack.sample.github.home.ui.viewholder.AvatarViewHolder
import com.jack.sample.github.recyclerview.card.enums.CardType
import com.jack.sample.github.recyclerview.card.item.CardItem

class PagedCardAdapter(
    private val onCardClicked: ((item: BaseCardItem, view: View) -> Unit)?
) : PagedListAdapter<CardItem, BaseViewHolder<CardItem>>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CardItem> {
        return when(CardType.fromId(viewType)) {
            CardType.AVATAR -> {
                AvatarViewHolder(parent, onCardClicked)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder<CardItem>, position: Int) {
        getItem(position)?.let {
            viewHolder.bind(it)
        }
    }

    override fun getItemViewType(position: Int): Int = getItem(position)?.type?.id ?: CardType.AVATAR.id
}

private object Diff: DiffUtil.ItemCallback<CardItem> () {
    override fun areItemsTheSame(oldItem: CardItem, newItem: CardItem): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: CardItem, newItem: CardItem): Boolean = oldItem.id == newItem.id
}