package com.jack.sample.github.recyclerview.row.enums

enum class CardRowType(val id: Int) {

    PAGED_CARDS(100),
    USER_REPO(200);

    companion object {

        fun fromId(id: Int): CardRowType {
            values().forEach {
                if(id == it.id) {
                    return it
                }
            }
            return USER_REPO
        }

    }

}