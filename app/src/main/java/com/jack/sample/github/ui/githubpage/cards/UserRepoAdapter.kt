package com.jack.sample.github.ui.githubpage.cards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jack.sample.github.R
import com.jack.sample.github.model.UserRepo
import kotlinx.android.synthetic.main.list_item_user_repo.view.*

class UserRepoAdapter : RecyclerView.Adapter<UserRepoAdapter.ViewHolder>() {

    private val _repoList: ArrayList<UserRepo> = ArrayList()

    fun update(list: List<UserRepo>) {
        _repoList.clear()
        _repoList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_user_repo, null, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return _repoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = _repoList[position]
        holder.desc.text = item.description
        holder.update_at.text = item.updated_at
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val desc: TextView = itemView.item_userrepo_tv_desc
        val update_at: TextView = itemView.item_userrepo_tv_update_at
    }
}