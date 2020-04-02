package com.jack.sample.github.home.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jack.sample.github.home.ui.card.BaseViewHolder
import com.jack.sample.github.home.ui.viewholder.HomeCardViewId
import com.jack.sample.github.home.ui.card.IGithubCard

class HomeAdapter(
    private val cards: MutableList<IGithubCard>
) : RecyclerView.Adapter<BaseViewHolder>() {

    fun updateRepositoryData(newData: MutableList<IGithubCard>) {
        removeExpiredRepositoryData()
        cards.addAll(newData)
        notifyItemRangeChanged(1, cards.lastIndex)
    }

    private fun removeExpiredRepositoryData() {
        val avatarCard = cards[0]
        val oldSize = cards.size
        if(oldSize > 0) {
            cards.clear()
            notifyItemRangeRemoved(1, oldSize - 2)
        }
        cards.add(avatarCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return HomeCardViewId.create(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return cards[position].viewTypeId
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        cards[position].bindViewHolder(holder)
    }

    override fun onViewRecycled(holder: BaseViewHolder) {
        super.onViewRecycled(holder)
        val pos = holder.layoutPosition
        cards[pos].onViewRecycled(holder)
    }
}

