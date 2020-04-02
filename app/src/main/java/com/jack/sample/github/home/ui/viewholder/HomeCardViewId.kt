package com.jack.sample.github.home.ui.viewholder

import android.view.ViewGroup
import com.jack.sample.github.home.ui.card.*
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor


class HomeCardViewId {
    companion object {
        const val VIEW_TYPE_USER_AVATAR = 1
        const val VIEW_TYPE_USER_REPO = 2

        fun create(parent: ViewGroup, id: Int): BaseViewHolder {
            val cardClazz = when (id) {
                VIEW_TYPE_USER_AVATAR -> AvatarCard::class
                else -> RepoCard::class
            }

            return createImpl(parent, cardClazz)
        }

        private fun createImpl(parent: ViewGroup, kClazz: KClass<out BaseCard>) : BaseViewHolder {
            val cardInstance = createDummyCard(kClazz)
            return cardInstance.createViewHolder(parent)
        }

        private fun createDummyCard(kClazz: KClass<out BaseCard>): BaseCard {
            val ctor = kClazz.primaryConstructor
            val instance = ctor?.let {
                when(it.parameters.size) {
                    0 -> ctor.call()
                    1 -> ctor.call(null)
                    2 -> ctor.call(null, null)
                    3 -> ctor.call(null, null, null)
                    4 -> ctor.call(null, null, null, null)
                    5 -> ctor.call(null, null, null, null, null)
                    else -> NullCard()
                }
            }
            return instance ?: NullCard()
        }
    }
}

