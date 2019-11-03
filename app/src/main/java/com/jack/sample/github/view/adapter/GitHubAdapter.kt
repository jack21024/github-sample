package com.jack.sample.github.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jack.sample.github.view.githubcard.BaseViewHolder
import com.jack.sample.github.view.githubcard.CardViewHolderId
import com.jack.sample.github.view.githubcard.ICardView

class GitHubAdapter(
    private val cards: MutableList<ICardView>
) : RecyclerView.Adapter<BaseViewHolder>() {

    fun updateRepositoryData(newData: MutableList<ICardView>) {
        val newDataSize = newData.size
        val avatarCard = cards[0]
        cards.clear()
        cards.add(avatarCard)
        cards.addAll(newData)
        notifyItemRangeChanged(1, newDataSize)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return CardViewHolderId.getViewHolderById(parent, viewType)
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


}

