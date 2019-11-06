package com.jack.sample.github.ui.githubpage.cards.entity

import android.os.Parcelable
import android.view.View
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jack.sample.github.ui.githubpage.GithubPageModel
import com.jack.sample.github.R
import com.jack.sample.github.model.User
import com.jack.sample.github.ui.githubpage.cards.UsersAdapter
import com.jack.sample.github.ui.githubpage.cards.viewholder.GithubCardViewId
import io.reactivex.Observable
import kotlinx.android.synthetic.main.list_item_user_avatar.view.*
import timber.log.Timber

class AvatarCard(
    datas: Observable<PagedList<User>>?
) : BaseCard() {

    var viewModel: GithubPageModel? = null

    private var avatarLayoutManager: LinearLayoutManager? = null
        set(value) {
            restoreAvatarState(value)
            field = value
        }

    private val avatarAdapter = UsersAdapter()

    private val avatarPool = RecyclerView.RecycledViewPool()

    private var avatarState: Parcelable? = null

    override val viewTypeId: Int =
        GithubCardViewId.VIEW_TYPE_USER_AVATAR

    override val layoutId: Int = R.layout.list_item_user_avatar

    init {
        datas?.subscribe {
            avatarAdapter.submitList(it)
        }
    }

    override fun getViewHolder(view: View): BaseViewHolder {
        return ViewHolder(view)
    }

    override fun bindViewHolder(holder: BaseViewHolder) {
        Timber.d("bind avatars.")

        if (holder is ViewHolder) {
            val context = holder.itemView.context
            avatarLayoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            holder.avatarList.apply {
                avatarAdapter.onClickListener = View.OnClickListener { view ->
                    val pos = this.getChildAdapterPosition(view)
                    val item = avatarAdapter.currentList?.get(pos)

                    Timber.d("load repositories with login ${item!!.login}.")

                    item?.run { viewModel?.queryUserRepoList(this.login) }
                }
                this.adapter = avatarAdapter
                this.layoutManager = avatarLayoutManager
                this.setRecycledViewPool(avatarPool)
            }
        }
    }

    override fun onViewRecycled(holder: BaseViewHolder) {
        saveAvatarState()
    }

    private fun saveAvatarState() {
        avatarLayoutManager?.let {
            avatarState = it.onSaveInstanceState()
        }
    }

    private fun restoreAvatarState(layoutManager: LinearLayoutManager?) {
        avatarState?.let {
            layoutManager?.onRestoreInstanceState(it)
        }
    }


    class ViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val avatarList: RecyclerView = itemView.item_user_avatar_list
    }

}