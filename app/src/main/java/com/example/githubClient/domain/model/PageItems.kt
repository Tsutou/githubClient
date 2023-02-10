package com.example.githubClient.domain.model

data class PageItems<T>(
    val items: List<T>,
    val totalCount: Int,
    val startCursor: PagingCursor,
    val endCursor: PagingCursor
) {

    data class PagingCursor(val value: String, val isHalfway: Boolean) {

        companion object {
            val EMPTY = PagingCursor(value = "", isHalfway = false)
        }
    }

    fun isEmpty(): Boolean {
        return this == empty<T>()
    }

    fun hasNext(): Boolean {
        return endCursor.isHalfway
    }

    companion object {

        fun <T> empty(): PageItems<T> = PageItems(
            items = emptyList(),
            totalCount = 0,
            startCursor = PagingCursor.EMPTY,
            endCursor = PagingCursor.EMPTY
        )
    }
}

operator fun <T> PageItems<T>.plus(nextPage: PageItems<T>): PageItems<T> {
    return PageItems(
        items = this.items + nextPage.items,
        totalCount = nextPage.totalCount,
        startCursor = this.startCursor,
        endCursor = nextPage.endCursor
    )
}