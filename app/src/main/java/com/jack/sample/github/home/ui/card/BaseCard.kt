package com.jack.sample.github.home.ui.card

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


abstract class BaseCard : IGithubCard {

    abstract val layoutId: Int

    abstract fun getViewHolder(view: View): BaseViewHolder

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return getViewHolder(view)
    }

    override fun onViewRecycled(holder: BaseViewHolder) { }
}


open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)