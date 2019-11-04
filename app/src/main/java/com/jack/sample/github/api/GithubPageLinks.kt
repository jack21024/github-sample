package com.jack.sample.github.api

import com.jack.sample.github.api.GithubService.Companion.HEADER_LAST
import com.jack.sample.github.api.GithubService.Companion.HEADER_LINK
import com.jack.sample.github.api.GithubService.Companion.HEADER_NEXT
import com.jack.sample.github.api.GithubService.Companion.META_FIRST
import com.jack.sample.github.api.GithubService.Companion.META_LAST
import com.jack.sample.github.api.GithubService.Companion.META_NEXT
import com.jack.sample.github.api.GithubService.Companion.META_PREV
import com.jack.sample.github.api.GithubService.Companion.META_REL
import okhttp3.Headers

class GithubPageLinks(headers: Headers) {

    companion object {
        private val DELIM_LINKS = "," //$NON-NLS-1$
        private val DELIM_LINK_PARAM = ";" //$NON-NLS-1$

        fun getNext(headers: Headers): String = GithubPageLinks(headers).next
    }

    lateinit var first: String
        private set

    lateinit var last: String
        private set

    lateinit var next: String
        private set

    lateinit var prev: String
        private set

    init {
        val linkHeader = headers.get(HEADER_LINK)
        if (linkHeader != null) {
            val links = linkHeader.split(DELIM_LINKS.toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            for (link in links) {
                val segments = link.split(DELIM_LINK_PARAM.toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
                if (segments.size < 2)
                    continue

                var linkPart = segments[0].trim { it <= ' ' }
                if (!linkPart.startsWith("<") || !linkPart.endsWith(">"))
                //$NON-NLS-1$ //$NON-NLS-2$
                    continue
                linkPart = linkPart.substring(1, linkPart.length - 1)

                for (i in 1 until segments.size) {
                    val rel = segments[i].trim { it <= ' ' }.split("=".toRegex())
                        .dropLastWhile { it.isEmpty() }.toTypedArray() //$NON-NLS-1$
                    if (rel.size < 2 || META_REL != rel[0])
                        continue

                    var relValue = rel[1]
                    if (relValue.startsWith("\"") && relValue.endsWith("\""))
                    //$NON-NLS-1$ //$NON-NLS-2$
                        relValue = relValue.substring(1, relValue.length - 1)

                    if (META_FIRST == relValue)
                        first = linkPart
                    else if (META_LAST == relValue)
                        last = linkPart
                    else if (META_NEXT == relValue)
                        next = linkPart
                    else if (META_PREV == relValue)
                        prev = linkPart
                }
            }
        } else {
            next = headers.get(HEADER_NEXT) ?: ""
            last = headers.get(HEADER_LAST) ?: ""
        }
    }

}