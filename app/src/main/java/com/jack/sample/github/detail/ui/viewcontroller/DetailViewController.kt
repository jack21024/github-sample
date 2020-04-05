package com.jack.sample.github.detail.ui.viewcontroller

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jack.sample.github.R
import com.jack.sample.github.base.viewcontroller.BaseViewController
import com.jack.sample.github.home.data.entity.UserDetail
import kotlinx.android.synthetic.main.activity_detail.view.*

class DetailViewController(view: View) : BaseViewController<UserDetail>(view) {

    init {
        view.apply {
            progress_bar.visibility = View.VISIBLE
        }
    }

    override fun update(data: UserDetail) {
        view.apply {
            text_login.text = data.login
            text_bio.text = data.bio
            view_user_detail_info.setData(data.login, data.isSiteAdmin)
            view_user_detail_loc.setData(data.location)
            view_user_detail_blog.setData(data.blogUrl)

            Glide.with(context)
                .load(data.avatarUrl)
                .placeholder(R.drawable.placeholder_circle_gray_150)
                .apply(RequestOptions.circleCropTransform())
                .into(image_user_pic)

            progress_bar.visibility = View.GONE
        }
    }

}