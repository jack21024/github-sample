package com.jack.sample.github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jack.sample.github.databinding.ActivityMainBinding
import androidx.lifecycle.ViewModelProviders
class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private lateinit var _viewModel: MainViewModel

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
        _binding.mainListUser.let {
            it.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        }
        _binding.mainListRepo.let {
            it.layoutManager = LinearLayoutManager(this)
        }
    }

}
