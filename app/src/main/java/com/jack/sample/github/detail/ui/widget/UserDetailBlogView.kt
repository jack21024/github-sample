package com.jack.sample.github.detail.ui.widget

import android.content.Context
import android.graphics.Color
import android.graphics.LightingColorFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.jack.sample.github.R
import kotlinx.android.synthetic.main.view_user_detail_blog.view.*

class UserDetailBlogView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    init {
        LayoutInflater.from(this@UserDetailBlogView.context)
            .inflate(R.layout.view_user_detail_blog, this, true)
    }

    fun setData(blogUrl: String?) {
        image_blog.colorFilter =
            LightingColorFilter(
                Color.WHITE,
                ContextCompat.getColor(this.context, R.color.gs_gray_500)
            )
        blogUrl?.run {
            text_blog.text = blogUrl
        }
    }
}