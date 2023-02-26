package com.sherryyuan.collapsingtopbarlibrary


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.sherryyuan.collapsingtopbarlibrary.ui.theme.Purple700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Material3ScaffoldLibrary(books: List<BookModel> = DEFAULT_BOOKS) {
    val listState = rememberLazyListState()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    val isCollapsed: Boolean by remember {
        derivedStateOf {
            scrollBehavior.state.collapsedFraction == 1f
        }
    }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBar(scrollBehavior, isCollapsed) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            state = listState
        ) {
            items(items = books) { book ->
                Book(model = book)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(scrollBehavior: TopAppBarScrollBehavior, isCollapsed: Boolean) =
    LargeTopAppBar(
        title = { Text(text = "Library") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Purple700,
            scrolledContainerColor = MaterialTheme.colorScheme.background,
            titleContentColor = if (isCollapsed) {
                MaterialTheme.colorScheme.onBackground
            } else {
                MaterialTheme.colorScheme.onPrimary
            },
        ),
        scrollBehavior = scrollBehavior,
    )
