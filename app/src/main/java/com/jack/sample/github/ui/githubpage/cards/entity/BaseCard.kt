package com.jack.sample.github.ui.githubpage.cards.entity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jack.sample.github.ui.githubpage.cards.interfaces.IGithubCard


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