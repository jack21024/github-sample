package com.jack.sample.github.recyclerview.row.viewholder

import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jack.sample.github.base.recyclerview.viewholder.BaseViewHolder
import com.jack.sample.github.recyclerview.row.item.PagedCardRowItem
import com.jack.sample.github.R
import com.jack.sample.github.base.recyclerview.item.BaseCardItem
import com.jack.sample.github.recyclerview.card.PagedCardAdapter
import com.jack.sample.github.recyclerview.row.item.CardRowItem
import kotlinx.android.synthetic.main.list_item_user_avatar.view.*
import timber.log.Timber


class PagedCardViewHolder(
    parent: ViewGroup,
    onCardClicked: ((item: BaseCardItem, view: View) -> Unit)?
) : BaseViewHolder<CardRowItem>(R.layout.list_item_user_avatar, parent) {

    private var avatarLayoutManager: LinearLayoutManager? = null
        set(value) {
            restoreAvatarState(value)
            field = value
        }

    private val avatarAdapter = PagedCardAdapter(onCardClicked)

    private val avatarPool = RecyclerView.RecycledViewPool()

    private var avatarState: Parcelable? = null


    private var data: PagedCardRowItem? = null

    override fun bind(data: CardRowItem) {
        Timber.d("bind avatars.")

        this.data = data as PagedCardRowItem

        itemView.apply {
            avatarLayoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            item_user_avatar_list.apply {
                adapter = avatarAdapter.apply {
                    submitList(data.pagedList)
                }
                layoutManager = avatarLayoutManager
                setRecycledViewPool(avatarPool)
            }
        }
    }

    override fun onItemClicked(view: View) = Unit

    override fun onRecycled() {
        saveAvatarState()
    }

    private fun saveAvatarState() {
        avatarLayoutManager?.let {
            avatarState = it.onSaveInstanceState()
        }
    }

    private fun restoreAvatarState(layoutManager: LinearLayoutManager?) {
        avatarState?.let {
            layoutManager?.onRestoreInstanceState(it)
        }
    }


}