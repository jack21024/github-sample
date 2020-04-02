package com.jack.sample.github.home.ui.card

import android.view.ViewGroup

interface IGithubCard {
    val viewTypeId: Int
    fun createViewHolder(parent: ViewGroup): BaseViewHolder
    fun bindViewHolder(holder: BaseViewHolder)
    fun onViewRecycled(holder: BaseViewHolder)
}