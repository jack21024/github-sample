package com.jack.sample.github.home.ui.viewcontroller

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.jack.sample.github.base.recyclerview.item.BaseCardItem
import com.jack.sample.github.base.viewcontroller.BaseViewController
import com.jack.sample.github.recyclerview.row.RowAdapter
import com.jack.sample.github.recyclerview.row.item.CardRowItem
import kotlinx.android.synthetic.main.activity_main.view.*

class HomeViewController(
    view: View,
    onCardClicked: ((item: BaseCardItem, view: View) -> Unit)?
) : BaseViewController<List<CardRowItem>>(view) {

    private val rowAdapter = RowAdapter(onCardClicked)

    init {
        view.apply {
            main_list_user.apply {
                adapter = rowAdapter
                layoutManager = LinearLayoutManager(context)
            }
            main_progress.visibility = View.VISIBLE
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
        view.apply {
            main_progress.visibility = View.GONE
        }
    }

}
