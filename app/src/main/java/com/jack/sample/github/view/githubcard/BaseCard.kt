package com.jack.sample.github.view.githubcard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


abstract class BaseCard : ICardView {

    abstract val layoutId: Int

    abstract fun getViewHolder(view: View): BaseViewHolder

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return getViewHolder(view)
    }

    override fun onViewRecycled(holder: BaseViewHolder) { }
}

interface ICardView {
    val viewTypeId: Int
    fun createViewHolder(parent: ViewGroup): BaseViewHolder
    fun bindViewHolder(holder: BaseViewHolder)
    fun onViewRecycled(holder: BaseViewHolder)
}

open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)