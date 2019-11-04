package com.jack.sample.github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jack.sample.github.databinding.ActivityMainBinding
import androidx.lifecycle.ViewModelProviders
import com.jack.sample.github.ui.githubpage.cards.GithubAdapter
import com.jack.sample.github.ui.githubpage.cards.entity.AvatarCard
import com.jack.sample.github.ui.githubpage.cards.entity.RepositoryCard
import com.jack.sample.github.model.UserRepo
import com.jack.sample.github.ui.githubpage.GithubPageModel
import com.jack.sample.github.ui.githubpage.cards.interfaces.IGithubCard

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private lateinit var _viewModel: GithubPageModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initList()
    }

    private fun initBinding() {
        _viewModel = ViewModelProviders.of(this).get(GithubPageModel::class.java)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        _binding.viewModel = _viewModel
        _binding.lifecycleOwner = this
    }

    private fun initList() {
        val githubCards = getGithubListCard()

        _binding.mainListUser.apply {
            val githubAdapter = GithubAdapter(githubCards)
            this.adapter = githubAdapter
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            _viewModel.userRepoList.observe(this@MainActivity,  Observer<List<UserRepo>> { newRepos ->

                Log.e("Card", "Main#userRepoList#observe list.size=${newRepos.size}")
                val repoCards =newRepos.map { RepositoryCard(
                    it
                ) as IGithubCard
                }.toMutableList()
                githubAdapter.updateRepositoryData(repoCards)
            })
        }
    }

    private fun getGithubListCard() : MutableList<IGithubCard>{
        val avatarCard = AvatarCard(_viewModel.userList)
        avatarCard.viewModel = _viewModel
        return  mutableListOf(avatarCard)
    }

}
