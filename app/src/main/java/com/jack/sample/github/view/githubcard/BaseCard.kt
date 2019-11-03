package com.jack.sample.github.view.githubcard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView


abstract class BaseCard : ICardView {

    abstract val layoutId: Int

    abstract fun getViewHolder(view: View): BaseViewHolder

    override fun createViewHolder(context: Context): BaseViewHolder {
        val view = LayoutInflater.from(context).inflate(layoutId, null, false)
        return getViewHolder(view)
    }
}

interface ICardView {
    val viewTypeId: Int
    fun createViewHolder(context: Context): BaseViewHolder
    fun bindViewHolder(holder: BaseViewHolder)
}

open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)