package com.jack.sample.github.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.jack.sample.github.R
import com.jack.sample.github.home.ui.card.RepoCard
import com.jack.sample.github.home.ui.viewmodel.HomeViewModel
import com.jack.sample.github.home.ui.card.IGithubCard
import com.jack.sample.github.home.viewcontroller.HomeCardViewController
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class HomeActivity : AppCompatActivity() {

    private lateinit var _viewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewController()
        initViewModel()
    }

    lateinit var homeViewController: HomeCardViewController
    private fun initViewController() {
        homeViewController = HomeCardViewController(main_list_user, null)
    }

    private fun initViewModel() {
        _viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)


        _viewModel.loading.observe(this, Observer { loading ->
            main_progress.apply {
                visibility = if(loading) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
        })

        _viewModel.userList?.observe(this, Observer {
            it.addWeakCallback(it, object : PagedList.Callback() {
                override fun onInserted(position: Int, count: Int) {
                    it.removeWeakCallback(this)
                    if (count > 0) {
                        Timber.d("users is fetched at first, load repositories.")
                        it.first().let { user ->
                            _viewModel.getUserRepoList(user.login)
                        }
                    } else {
                        Timber.e("fetch users failed.")
                    }
                }

                override fun onChanged(position: Int, count: Int) {}
                override fun onRemoved(position: Int, count: Int) {}

            })
            homeViewController.updateAvatar(it)
        })

        _viewModel.userRepoList.observe(this@HomeActivity, Observer {
            val repoCards =it.viewData.value?.userRepoList?.map { RepoCard(
                it
            ) as IGithubCard
            }?.toMutableList()
            repoCards?.let {
                homeViewController.update(repoCards)
            }
        })
    }

//    private fun initBinding() {
//        _viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
//        _binding = DataBindingUtil.setContentView(this,
//            R.layout.activity_main
//        )
//        _binding.viewModel = _viewModel
//        _binding.lifecycleOwner = this
//    }
//
//    private fun initList() {
//        val githubCards = getGithubCard()
//
//        _binding.mainListUser.apply {
//            val githubAdapter = HomeCardAdapter(githubCards)
//            this.adapter = githubAdapter
//            this.layoutManager = LinearLayoutManager(this@HomeActivity)
//            _viewModel.userRepoList.observe(this@HomeActivity, Observer {
//                val repoCards =it.viewData.value?.userRepoList?.map { RepoCard(
//                    it
//                ) as IGithubCard
//                }?.toMutableList()
//                repoCards?.let {
//                    githubAdapter.updateRepositoryData(repoCards)
//                }
//            })
//        }
//    }
//
//    private fun getGithubCard() : MutableList<IGithubCard>{
//        val avatarCard = AvatarCard(_viewModel.userList)
//        avatarCard.viewModel = _viewModel
//        _viewModel.userList?.observe(this, Observer {
//            avatarCard.update(it)
//        })
//        return  mutableListOf(avatarCard)
//    }

}
