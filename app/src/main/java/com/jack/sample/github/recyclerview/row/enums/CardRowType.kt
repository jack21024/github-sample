package com.jack.sample.github.recyclerview.row.enums

enum class CardRowType(val id: Int) {

    REPOSITORY(100),
    PAGED_CARDS(200);

    companion object {

        fun fromId(id: Int): CardRowType {
            values().forEach {
                if(id == it.id) {
                    return it
                }
            }
            return REPOSITORY
        }

    }

}