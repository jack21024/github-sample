package com.jack.sample.github.view.githubcard

import android.util.Log
import android.view.View
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jack.sample.github.MainViewModel
import com.jack.sample.github.R
import com.jack.sample.github.model.User
import com.jack.sample.github.view.adapter.UsersAdapter
import io.reactivex.Observable
import kotlinx.android.synthetic.main.list_item_user_avatar.view.*

class AvatarCard(
    datas: Observable<PagedList<User>>?
) : BaseCard() {

    var viewModel: MainViewModel? = null

    private val avatarAdapter = UsersAdapter()

    private val viewPool = RecyclerView.RecycledViewPool()


    override val viewTypeId: Int =
        CardViewHolderId.VIEW_TYPE_USER_AVATAR

    override val layoutId: Int = R.layout.list_item_user_avatar

    init {
        datas?.subscribe {
            Log.d("Card", "AvatarCard#bindViewHolder#datas.subscribe userList.size=${it.size}")
            avatarAdapter.submitList(it)
        }
    }

    override fun getViewHolder(view: View): BaseViewHolder {
        return ViewHolder(view)
    }

    override fun bindViewHolder(holder: BaseViewHolder) {
        Log.e("Card", "AvatarCard#bindViewHolder#IN")

        if (holder is ViewHolder) {
            val context = holder.itemView.context
            val layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            holder.avatarList.apply {
                avatarAdapter.onClickListener = View.OnClickListener { view ->
                    val pos = this.getChildAdapterPosition(view)
                    val item = avatarAdapter.currentList?.get(pos)

                    Log.i("Card", "AvatarCard#click pos=$pos login=${item!!.login}")

                    item?.run { viewModel?.queryUserRepoList(this.login) }
                }
                this.adapter = avatarAdapter
                this.layoutManager = layoutManager
                this.setRecycledViewPool(viewPool)
            }
        }
    }

    class ViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val avatarList: RecyclerView = itemView.item_user_avatar_list
    }

}