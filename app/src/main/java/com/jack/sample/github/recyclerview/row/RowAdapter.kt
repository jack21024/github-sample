package com.jack.sample.github.recyclerview.row

import android.view.View
import android.view.ViewGroup
import com.jack.sample.github.base.recyclerview.adapter.BaseViewAdapter
import com.jack.sample.github.base.recyclerview.item.BaseCardItem
import com.jack.sample.github.recyclerview.row.enums.CardRowType
import com.jack.sample.github.recyclerview.row.item.CardRowItem
import com.jack.sample.github.base.recyclerview.viewholder.BaseViewHolder
import com.jack.sample.github.home.ui.viewholder.UserRepoViewHolder
import com.jack.sample.github.recyclerview.row.viewholder.PagedCardViewHolder

class RowAdapter(
    private val onCardClicked: ((item: BaseCardItem, view: View) -> Unit)?
) : BaseViewAdapter<CardRowItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CardRowItem> {
        return when(CardRowType.fromId(viewType)) {
            CardRowType.USER_REPO -> {
                UserRepoViewHolder(parent, onCardClicked)
            }
            CardRowType.PAGED_CARDS -> {
                PagedCardViewHolder(parent, onCardClicked)
            }
        }
    }

    override fun onBind(viewHolder: BaseViewHolder<CardRowItem>, position: Int) {
        getItem(position)?.let {
            viewHolder.bind(it)
        }
    }

    override fun getItemViewType(position: Int): Int = getItem(position)?.type?.id ?: CardRowType.USER_REPO.id
}
