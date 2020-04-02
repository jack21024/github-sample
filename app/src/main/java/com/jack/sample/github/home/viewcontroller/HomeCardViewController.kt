package com.jack.sample.github.home.viewcontroller

import android.view.View
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jack.sample.github.base.viewcontroller.BaseViewController
import com.jack.sample.github.home.data.entity.User
import com.jack.sample.github.home.ui.adapter.HomeCardAdapter
import com.jack.sample.github.home.ui.card.AvatarCard
import com.jack.sample.github.home.ui.card.IGithubCard
import kotlinx.android.synthetic.main.activity_main.view.*

class HomeCardViewController(
    private val view: RecyclerView,
    val onCardClicked: (() -> Unit)?
) : BaseViewController<MutableList<IGithubCard>>(view) {

    init {
        view.apply {
            this.adapter = HomeCardAdapter(mutableListOf(AvatarCard()))
            this.layoutManager = LinearLayoutManager(context)
        }
    }

    fun updateAvatar(userList: PagedList<User>) {
        view.main_list_user.apply {
            (adapter as HomeCardAdapter).let {
                var avatarCard = it.getItem(0) as AvatarCard
                avatarCard?.let {
                    it.update(userList)
                }
            }
        }
    }

    override fun update(data: MutableList<IGithubCard>) {
        view.main_list_user.apply {
            (adapter as HomeCardAdapter).let {
                it.updateRepositoryData(data)
            }
        }
    }
}
