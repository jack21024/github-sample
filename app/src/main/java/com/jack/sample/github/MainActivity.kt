package com.jack.sample.github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jack.sample.github.databinding.ActivityMainBinding
import androidx.lifecycle.ViewModelProviders
import com.jack.sample.github.adapter.UserRepoAdapter
import com.jack.sample.github.adapter.UsersAdapter
import com.jack.sample.github.model.UserRepo

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private lateinit var _viewModel: MainViewModel

    private val userListAdapter = UsersAdapter()
    private val repoListAdapter = UserRepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initAllList()
    }

    private fun initBinding() {
        _viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        _binding.viewModel = _viewModel
        _binding.lifecycleOwner = this
    }

    private fun initAllList() {
        _binding.mainListUser.let { listView ->
            userListAdapter.onClickListener = View.OnClickListener { view ->
                val pos = listView.getChildAdapterPosition(view)
                val item = userListAdapter.currentList?.get(pos)
                item?.run { _viewModel.queryUserRepoList(this.login) }
            }
            listView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            listView.adapter = userListAdapter
            _viewModel.userList.subscribe { users ->
                userListAdapter.submitList(users)
            }
        }
        _binding.mainListRepo.let { listView ->
            listView.layoutManager = LinearLayoutManager(this)
            listView.adapter = repoListAdapter
            _viewModel.userRepoList.observe(this, Observer<List<UserRepo>> { newRepos ->
                    repoListAdapter.update(newRepos)
                    repoListAdapter.notifyDataSetChanged()
            })
        }
    }

}
