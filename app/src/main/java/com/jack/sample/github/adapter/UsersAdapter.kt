package com.jack.sample.github.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jack.sample.github.R
import com.jack.sample.github.model.User

class UsersAdapter : PagedListAdapter<User, UsersAdapter.ViewHolder>(UserDiff) {


    var onClickListener: View.OnClickListener? = null

    private fun createViewHolder(view: View) : ViewHolder {
        val avatar: ImageView = view.findViewById(R.id.list_item_user_avatar)
        val username: TextView = view.findViewById(R.id.list_item_user_username)
        view.setOnClickListener(onClickListener)
        return ViewHolder(view,username, avatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lsit_item_user, parent, false)
        return createViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var user = getItem(position)!!
        holder.username.text =user.login
        Glide.with(holder.itemView.context)
            .load(user.avatar_url)
            .placeholder(R.color.colorPrimary)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.avatar)
    }


    class ViewHolder(
        itemView: View,
        var username: TextView,
        var avatar: ImageView
    ) : RecyclerView.ViewHolder(itemView)
}

private object UserDiff: DiffUtil.ItemCallback<User> () {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
}