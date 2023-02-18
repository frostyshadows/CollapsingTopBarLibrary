package com.sherryyuan.livelycolumn

sealed class BookModel(open val title: String, open val author: String) {
    data class PaperBookModel(
        override val title: String,
        override val author: String,
        val pageCount: Int,
    ) : BookModel(title, author)

    data class AudioBookModel(
        override val title: String,
        override val author: String,
        val minutes: Int,
    ) : BookModel(title, author)
}