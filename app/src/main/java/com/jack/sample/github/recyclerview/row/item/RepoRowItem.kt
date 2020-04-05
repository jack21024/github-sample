package com.jack.sample.github.recyclerview.row.item

import com.jack.sample.github.recyclerview.row.enums.CardRowType
import com.jack.sample.github.home.data.entity.UserRepo

class RepoRowItem(
    val userRepo: UserRepo
) : CardRowItem(CardRowType.REPOSITORY)