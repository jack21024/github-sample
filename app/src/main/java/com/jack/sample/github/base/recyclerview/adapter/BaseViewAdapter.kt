package com.jack.sample.github.base.recyclerview.adapter

import androidx.recyclerview.widget.RecyclerView
import com.jack.sample.github.base.recyclerview.viewholder.BaseViewHolder

abstract class BaseViewAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private val items = ArrayList<T>()

    abstract fun onBind(viewHolder: BaseViewHolder<T>, position: Int)

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        onBind(holder, position)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<T>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            onReBind(holder, position)
        }
    }

    override fun getItemCount(): Int = items.size

    open fun onReBind(viewHolder: BaseViewHolder<T>, position: Int) {
        onBind(viewHolder, position)
    }

    fun getItems(): List<T> = items

    fun setItems(items: List<T>) = this.items.apply {
        clear()
        addAll(items)
    }

    fun setItem(position: Int, item: T) = items.set(position, item)

    fun getItem(position: Int): T? = items.getOrNull(position)

    fun addItem(item: T) = items.add(item)

    fun addItems(items: List<T>) = this.items.addAll(items)

    fun addItems(position: Int, items: List<T>) = this.items.addAll(position, items)

    fun removeItem(position: Int) = items.removeAt(position)

    fun removeItem(item: T) = items.remove(item)

    fun clearItems() = items.clear()

}
