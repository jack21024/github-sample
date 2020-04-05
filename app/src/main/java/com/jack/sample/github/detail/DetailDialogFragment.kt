package com.jack.sample.github.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jack.sample.github.R
import com.jack.sample.github.detail.ui.viewcontroller.DetailViewController
import com.jack.sample.github.detail.viewmodel.DetailViewModel
import com.jack.sample.github.recyclerview.row.item.DetailRowItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailDialogFragment : DialogFragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var viewController : DetailViewController

    private var userName: String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.activity_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.run {
            userName = getString(DetailRowItem.BUNDLE_STR_USER_NAME)
        }
        initViewController()
        initViewModel()
    }

    override fun onStart() {
        super.onStart()
        initDialogLayout()
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

    private fun initDialogLayout() {
        dialog?.apply {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            window.setBackgroundDrawableResource(R.color.gs_white)
        }
    }

    companion object {

        fun create(bundle: Bundle?): DetailDialogFragment =
            DetailDialogFragment().apply {
                bundle?.let {
                    arguments = bundle
                }
            }

    }

}