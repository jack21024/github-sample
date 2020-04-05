package com.jack.sample.github.base.recyclerview.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    constructor(@LayoutRes layoutResId: Int, parent: ViewGroup) :
            this(LayoutInflater.from(parent.context).inflate(layoutResId, parent, false))

    abstract fun bind(data: T)
    abstract fun onItemClicked(view: View)

    open fun onRecycled() {}

//    open fun rebind(data: T) {}

    init {
        itemView.apply {
            setOnClickListener {
                onItemClicked(it)
            }
        }
    }
}
