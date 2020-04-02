package com.jack.sample.github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jack.sample.github.databinding.ActivityMainBinding
import androidx.lifecycle.ViewModelProviders
import com.jack.sample.github.home.ui.adapter.HomeAdapter
import com.jack.sample.github.home.ui.card.AvatarCard
import com.jack.sample.github.home.ui.card.RepoCard
import com.jack.sample.github.home.ui.viewmodel.HomeViewModel
import com.jack.sample.github.home.ui.card.IGithubCard

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private lateinit var _viewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initList()
    }

    private fun initBinding() {
        _viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        _binding.viewModel = _viewModel
        _binding.lifecycleOwner = this
    }

    private fun initList() {
        val githubCards = getGithubCard()

        _binding.mainListUser.apply {
            val githubAdapter = HomeAdapter(githubCards)
            this.adapter = githubAdapter
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            _viewModel.userRepoList.observe(this@MainActivity, Observer {
                val repoCards =it.viewData.value?.userRepoList?.map { RepoCard(
                    it
                ) as IGithubCard
                }?.toMutableList()
                repoCards?.let {
                    githubAdapter.updateRepositoryData(repoCards)
                }
            })
        }
    }

    private fun getGithubCard() : MutableList<IGithubCard>{
        val avatarCard = AvatarCard(_viewModel.userList)
        avatarCard.viewModel = _viewModel
        _viewModel.userList?.observe(this, Observer {
            avatarCard.update(it)
        })
        return  mutableListOf(avatarCard)
    }

}
