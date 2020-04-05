package com.jack.sample.github.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jack.sample.github.R
import com.jack.sample.github.home.ui.viewmodel.HomeViewModel
import com.jack.sample.github.home.ui.viewcontroller.HomeViewController
import com.jack.sample.github.recyclerview.card.enums.CardType
import com.jack.sample.github.recyclerview.card.item.CardItem
import com.jack.sample.github.recyclerview.row.enums.CardRowType
import com.jack.sample.github.recyclerview.row.item.CardRowItem
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    private lateinit var _viewModel: HomeViewModel
    private lateinit var homeViewController: HomeViewController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewController()
        initViewModel()
    }

    private fun initViewController() {
        homeViewController = HomeViewController(main_list_user) { item, _ ->
            when (item) {
                is CardRowItem -> {
                    when (item.type) {
                        CardRowType.DETAIL -> {
                            Toast.makeText(this, "See profile.", Toast.LENGTH_LONG).show()
                        }
                        else -> {
                            Toast.makeText(this, "${item} clicked.", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                is CardItem -> {
                    when (item.type) {
                        CardType.AVATAR -> {
                            item.user?.let {
                                _viewModel.getUserRepoList(it.login)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initViewModel() {
        _viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        _viewModel.loading.observe(this, Observer { loading ->
            main_progress.apply {
                visibility = if (loading) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
        })
        _viewModel.homeViewData.observe(this, Observer {
            it.data?.let {
                homeViewController.update(it)
            }
        })
        _viewModel.userRepoRowData.observe(this, Observer {
            it?.let {
                homeViewController.updateRepositoryData(it)
            }
        })

        _viewModel.start()
    }


}
