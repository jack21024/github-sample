package com.jack.sample.github.ui.githubpage.cards.interfaces

import android.view.ViewGroup
import com.jack.sample.github.ui.githubpage.cards.entity.BaseViewHolder

interface IGithubCard {
    val viewTypeId: Int
    fun createViewHolder(parent: ViewGroup): BaseViewHolder
    fun bindViewHolder(holder: BaseViewHolder)
    fun onViewRecycled(holder: BaseViewHolder)
}