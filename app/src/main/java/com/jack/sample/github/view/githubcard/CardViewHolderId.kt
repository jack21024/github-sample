package com.jack.sample.github.view.githubcard

import android.content.Context


class CardViewHolderId {
    companion object {
        const val VIEW_TYPE_USER_AVATAR = 1
        const val VIEW_TYPE_USER_REPO = 2

        fun getViewHolderById(context: Context, id: Int): BaseViewHolder {
            when (id) {
                VIEW_TYPE_USER_AVATAR -> return AvatarCard(
                    null
                ).createViewHolder(context)
                else -> return RepositoryCard(null).createViewHolder(context)
            }
        }
    }
}

