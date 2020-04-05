package com.jack.sample.github.home.ui.viewholder

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jack.sample.github.base.recyclerview.viewholder.BaseViewHolder
import com.jack.sample.github.R
import com.jack.sample.github.base.recyclerview.item.BaseCardItem
import com.jack.sample.github.recyclerview.row.item.CardRowItem
import com.jack.sample.github.recyclerview.row.item.RepoRowItem
import kotlinx.android.synthetic.main.list_item_user_repo.view.*

class UserRepoViewHolder(
    parent: ViewGroup,
    private val onCardClicked: ((item: BaseCardItem, view: View) -> Unit)?
) : BaseViewHolder<CardRowItem>(R.layout.list_item_user_repo, parent) {

    private var data: CardRowItem? = null

    override fun bind(data: CardRowItem) {
        (data as RepoRowItem).let {
            this.data = data
            val userRepo = it.userRepo
            userRepo?.let {
                itemView.apply {
                    item_userrepo_tv_fullname.text = userRepo.full_name
                    item_userrepo_tv_desc.text = userRepo.description
                    item_userrepo_tv_update_at.text = formatDate(userRepo.updated_at)
                    item_userrepo_tv_star_count.text = userRepo.stargazers_count
                    Glide.with(item_userrepo_iv_avatar)
                        .load(userRepo.owner.avatar_url)
                        .placeholder(R.drawable.placeholder_circle_gray_24)
                        .circleCrop()
                        .into(item_userrepo_iv_avatar)
                }
            }
        }
    }

    override fun onItemClicked(view: View) {
        data ?.let {
            onCardClicked?.invoke((it), view)
        }
    }

    private fun formatDate(dateStr: String) : String {
        return dateStr.split("T")[0]
    }

}