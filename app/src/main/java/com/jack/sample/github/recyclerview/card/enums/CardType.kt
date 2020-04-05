package com.jack.sample.github.recyclerview.card.enums

enum class CardType(val id: Int) {

    AVATAR(100);

    companion object {

        fun fromId(id: Int): CardType {
            values().forEach {
                if(id == it.id) {
                    return it
                }
            }
            return AVATAR
        }

    }

}