package com.jack.sample.github.home.ui.viewholder

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jack.sample.github.base.recyclerview.viewholder.BaseViewHolder
import com.jack.sample.github.recyclerview.card.item.CardItem
import com.jack.sample.github.R
import com.jack.sample.github.base.recyclerview.item.BaseCardItem
import kotlinx.android.synthetic.main.lsit_item_user_avatar_cell.view.*

class AvatarViewHolder(
    parent: ViewGroup,
    private val onCardClicked: ((item: BaseCardItem, view: View) -> Unit)?
) : BaseViewHolder<CardItem>(R.layout.lsit_item_user_avatar_cell, parent) {


    private var data: CardItem? = null

    override fun bind(data: CardItem) {
        this.data = data
        val user = data.user

        itemView.apply {
            item_user_avatar_cell_login.text = user?.login
            Glide.with(context)
                .load(user?.avatar_url)
                .placeholder(R.drawable.placeholder_circle_gray_72)
                .apply(RequestOptions.circleCropTransform())
                .into(item_user_avatar_cell_icon)
        }
    }

    override fun onItemClicked(view: View) {
        data?.let {
            onCardClicked?.invoke(it, view)
        }
    }

}

