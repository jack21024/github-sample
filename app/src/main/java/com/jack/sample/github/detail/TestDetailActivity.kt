package com.jack.sample.github.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jack.sample.github.detail.viewmodel.DetailViewModel

class TestDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel =  ViewModelProviders.of(this).get(DetailViewModel::class.java)

        viewModel.detailViewData.observe(this, Observer {
            Log.d("TestDetailActivity", "detail# ${it.value}")
        })

        viewModel.getData("wycats")
    }


}