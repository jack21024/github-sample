package com.jack.sample.github.view.githubcard

import android.view.View
import android.widget.TextView
import com.jack.sample.github.R
import com.jack.sample.github.model.UserRepo
import kotlinx.android.synthetic.main.list_item_user_repo.view.*

class RepositoryCard(private val data: UserRepo?) : BaseCard() {

    override val viewTypeId: Int =
        CardViewHolderId.VIEW_TYPE_USER_REPO
    override val layoutId: Int = R.layout.list_item_user_repo


    override fun getViewHolder(view: View): BaseViewHolder {
        return ViewHolder(view)
    }

    override fun bindViewHolder(holder: BaseViewHolder) {
        if (holder is ViewHolder) {
            holder.desc.text = data!!.description
            holder.update_at.text = data!!.updated_at
        }
    }

    class ViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val desc: TextView = itemView.item_userrepo_tv_desc
        val update_at: TextView = itemView.item_userrepo_tv_update_at
    }
}