package com.jack.sample.github.home.viewcontroller

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jack.sample.github.base.recyclerview.item.BaseCardItem
import com.jack.sample.github.base.viewcontroller.BaseViewController
import com.jack.sample.github.recyclerview.row.RowAdapter
import com.jack.sample.github.recyclerview.row.item.CardRowItem

class HomeViewController(
    view: RecyclerView,
    onCardClicked: ((item: BaseCardItem, view: View) -> Unit)?
) : BaseViewController<List<CardRowItem>>(view) {

    private val rowAdapter = RowAdapter(onCardClicked)

    init {
        view.apply {
            this.adapter = rowAdapter
            this.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun update(data: List<CardRowItem>) {
        rowAdapter.let {
            it.clearItems()
            it.addItems(data)
            it.notifyDataSetChanged()
        }
    }

    fun updateRepositoryData(data: List<CardRowItem>) {
        val cardRowList = rowAdapter.getItems().let {
            ArrayList<CardRowItem>().apply {
                add(it.first())
                addAll(data)
            }
        }
        rowAdapter.let {
            it.clearItems()
            it.addItems(cardRowList)
            it.notifyItemRangeChanged(1, cardRowList.lastIndex)
        }
    }

}
