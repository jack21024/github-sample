package com.jack.sample.github.ui.githubpage.cards.viewholder

import android.view.ViewGroup
import com.jack.sample.github.ui.githubpage.cards.entity.AvatarCard
import com.jack.sample.github.ui.githubpage.cards.entity.BaseViewHolder
import com.jack.sample.github.ui.githubpage.cards.entity.RepositoryCard


class GithubCardViewHolderId {
    companion object {
        const val VIEW_TYPE_USER_AVATAR = 1
        const val VIEW_TYPE_USER_REPO = 2

        fun getViewHolderById(parent: ViewGroup,id: Int): BaseViewHolder {
            when (id) {
                VIEW_TYPE_USER_AVATAR -> return AvatarCard(
                    null
                ).createViewHolder(parent)
                else -> return RepositoryCard(null)
                    .createViewHolder(parent)
            }
        }
    }
}

