package com.jack.sample.github.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jack.sample.github.R
import com.jack.sample.github.detail.ui.viewcontroller.DetailViewController
import com.jack.sample.github.detail.viewmodel.DetailViewModel
import com.jack.sample.github.recyclerview.row.item.DetailRowItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var viewController : DetailViewController

    private var userName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent.getStringExtra(DetailRowItem.BUNDLE_STR_USER_NAME)?.let {
            userName = it
        }
        initViewController()
        initViewModel()
    }

    private fun initViewController() {
        viewController = DetailViewController(detail_layout_root)
    }

    private fun initViewModel() {
        viewModel =  ViewModelProviders.of(this).get(DetailViewModel::class.java)

        viewModel.detailViewData.observe(this, Observer {
            it.value?.let {  data ->
                viewController.update(data)
            }
        })

        userName?.let {
            viewModel.getData(it)
        }
    }

    companion object {

        fun start(context: Context, bundle: Bundle?) {
            val intent = Intent(context, DetailActivity::class.java)
            bundle?.let {
                intent.putExtras(bundle)
            }
            context.startActivity(intent)
        }

    }

}