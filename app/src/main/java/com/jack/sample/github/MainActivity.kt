package com.jack.sample.github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jack.sample.github.databinding.ActivityMainBinding
import androidx.lifecycle.ViewModelProviders
import com.jack.sample.github.view.adapter.GitHubAdapter
import com.jack.sample.github.view.githubcard.AvatarCard
import com.jack.sample.github.view.githubcard.ICardView
import com.jack.sample.github.view.githubcard.RepositoryCard
import com.jack.sample.github.model.UserRepo

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private lateinit var _viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initList()
    }

    private fun initBinding() {
        _viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        _binding.viewModel = _viewModel
        _binding.lifecycleOwner = this
    }

    private fun initList() {
        val githubCards = getGithubListCard()

        _binding.mainListUser.apply {
            val githubAdapter = GitHubAdapter(githubCards)
            this.adapter = githubAdapter
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            _viewModel.userRepoList.observe(this@MainActivity,  Observer<List<UserRepo>> { newRepos ->

                Log.e("Card", "Main#userRepoList#observe list.size=${newRepos.size}")
                val repoCards =newRepos.map { RepositoryCard(it) as ICardView }.toMutableList()
                githubAdapter.updateRepositoryData(repoCards)
            })
        }
    }

    private fun getGithubListCard() : MutableList<ICardView>{
        val avatarCard = AvatarCard(_viewModel.userList)
        avatarCard.viewModel = _viewModel
        return  mutableListOf(avatarCard)
    }

}
