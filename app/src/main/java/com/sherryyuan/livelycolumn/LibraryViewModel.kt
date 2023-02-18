package com.sherryyuan.livelycolumn

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LibraryViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DEFAULT_BOOKS)
    val uiState: StateFlow<List<BookModel>> = _uiState.asStateFlow()

    fun update(books: List<BookModel>) {
        _uiState.update { books }
    }
}

private val DEFAULT_BOOKS = listOf(
    BookModel.PaperBookModel(
        title = "Tomorrow and Tomorrow and Tomorrow",
        author = "Gabrielle Zevin",
        pageCount = 416
    ),
    BookModel.PaperBookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
    BookModel.AudioBookModel(title = "Fairy Tail", author = "Stephen King", minutes = 1453),
    BookModel.PaperBookModel(
        title = "Tomorrow and Tomorrow and Tomorrow",
        author = "Gabrielle Zevin",
        pageCount = 416
    ),
    BookModel.PaperBookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
    BookModel.AudioBookModel(title = "Fairy Tail", author = "Stephen King", minutes = 1453),
    BookModel.PaperBookModel(
        title = "Tomorrow and Tomorrow and Tomorrow",
        author = "Gabrielle Zevin",
        pageCount = 416
    ),
    BookModel.PaperBookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
    BookModel.AudioBookModel(title = "Fairy Tail", author = "Stephen King", minutes = 1453),
)
