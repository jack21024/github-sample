package com.jack.sample.github.ui.githubpage.cards.entity

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.jack.sample.github.R
import com.jack.sample.github.model.UserRepo
import com.jack.sample.github.ui.githubpage.cards.viewholder.GithubCardViewId
import kotlinx.android.synthetic.main.list_item_user_repo.view.*

class RepositoryCard(private val data: UserRepo?) : BaseCard() {

    override val viewTypeId: Int =
        GithubCardViewId.VIEW_TYPE_USER_REPO
    override val layoutId: Int = R.layout.list_item_user_repo


    override fun getViewHolder(view: View): BaseViewHolder {
        return ViewHolder(view)
    }

    override fun bindViewHolder(holder: BaseViewHolder) {
        if (holder is ViewHolder) {
            data?.apply {
                holder.full_name.text = this.full_name
                holder.desc.text = this.description
                holder.update_at.text = formatDate(this.updated_at)
                holder.starCount.text = this.stargazers_count
                Glide.with(holder.avatar)
                    .load(this.owner.avatar_url)
                    .placeholder(R.drawable.placeholder_circle_gray_24)
                    .circleCrop()
                    .into(holder.avatar)
            }
        }
    }

    private fun formatDate(dateStr: String) : String {
        return dateStr.split("T")[0]
    }

    class ViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val full_name: TextView = itemView.item_userrepo_tv_fullname
        val desc: TextView = itemView.item_userrepo_tv_desc
        val update_at: TextView = itemView.item_userrepo_tv_update_at
        val starCount: TextView = itemView.item_userrepo_tv_star_count
        val avatar: ImageView = itemView.item_userrepo_iv_avatar
    }
}