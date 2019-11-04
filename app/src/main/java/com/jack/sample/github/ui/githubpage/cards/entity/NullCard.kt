package com.jack.sample.github.ui.githubpage.cards.entity

import android.view.View
import com.jack.sample.github.R

class NullCard : BaseCard() {

    override val layoutId: Int = R.layout.list_item_null

    override fun getViewHolder(view: View): BaseViewHolder {
        return BaseViewHolder(view)
    }

    override val viewTypeId: Int = 0

    override fun bindViewHolder(holder: BaseViewHolder) { }

}