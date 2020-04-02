package com.jack.sample.github.base.viewcontroller

import android.view.View

abstract class BaseViewController<T>(private val view: View) {

    open fun update(data: T) = Unit

}