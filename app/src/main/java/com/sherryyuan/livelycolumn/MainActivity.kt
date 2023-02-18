package com.sherryyuan.livelycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sherryyuan.livelycolumn.ui.theme.LivelyColumnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LivelyColumnTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Library()
                }
            }
        }
    }
}

@Composable
fun Library(viewModel: LibraryViewModel = viewModel()) {
    val libraryState: List<BookModel> by viewModel.uiState.collectAsState()

    val listState = rememberLazyListState()

    val isCollapsed = listState.firstVisibleItemIndex > 0

    Scaffold(
        topBar = { CollapsedHeader(isCollapsed) }
    ) {
        LazyColumn(state = listState) {
            item { ExpandedHeader() }
            items(items = libraryState) { book ->
                when (book) {
                    is BookModel.PaperBookModel -> PaperBook(model = book)
                    is BookModel.AudioBookModel -> AudioBook(model = book)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun CollapsedHeader(isCollapsed: Boolean) {
    val color: Color by animateColorAsState(if (isCollapsed) Color.Transparent else MaterialTheme.colors.primaryVariant)
    Box(
        modifier = Modifier
            .background(color)
            .fillMaxWidth()
            .height(56.dp)
            .padding(16.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        AnimatedVisibility(visible = isCollapsed) {
            Text("Library")
        }
    }
}

@Composable
fun ExpandedHeader() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Text("Library", color = MaterialTheme.colors.onPrimary)
    }
}

@Composable
fun PaperBook(model: BookModel.PaperBookModel) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(model.title)
            Text(model.author)
            Text("${model.pageCount} pages")
        }
    }
}

@Composable
fun AudioBook(model: BookModel.AudioBookModel) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(model.title)
            Text(model.author)
            Text("${model.minutes} minutes")
        }
    }
}
