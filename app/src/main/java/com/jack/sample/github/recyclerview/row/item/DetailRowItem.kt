package com.jack.sample.github.recyclerview.row.item

import android.os.Bundle
import com.jack.sample.github.recyclerview.row.enums.CardRowType

class DetailRowItem(
    val userName: String
) : CardRowItem(
    type = CardRowType.DETAIL,
    bundle = Bundle().apply {
        putString(BUNDLE_STR_USER_NAME, userName)
    }
) {

    companion object {
        const val BUNDLE_STR_USER_NAME = "BUNDLE_STR_USER_NAME"
    }

}