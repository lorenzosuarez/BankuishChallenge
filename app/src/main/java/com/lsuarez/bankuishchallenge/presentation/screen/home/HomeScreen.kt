package com.lsuarez.bankuishchallenge.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.lsuarez.bankuishchallenge.R
import com.lsuarez.bankuishchallenge.presentation.components.ErrorSnackBar
import com.lsuarez.bankuishchallenge.presentation.components.GithubRepoItemRow
import com.lsuarez.bankuishchallenge.presentation.components.GoBackUpFloatingButton
import com.lsuarez.bankuishchallenge.presentation.navigation.Screen
import com.lsuarez.bankuishchallenge.util.Const
import com.lsuarez.domain.model.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.DecimalFormat

/**
 * Created by Lorenzo on 12/5/2022.
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    val allItems = viewModel.getAllItems.collectAsLazyPagingItems()
    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val firstItemVisible by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0
        }
    }

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { allItems.refresh() }
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                TopAppBar(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    title = {
                        Text(
                            modifier = Modifier.padding(horizontal = 5.dp),
                            text = stringResource(R.string.app_name)
                        )
                    },
                    navigationIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_github),
                            contentDescription = "github image"
                        )
                    }
                )
            },
            floatingActionButton = {
                GoBackUpFloatingButton(
                    modifier = Modifier.padding(bottom = 40.dp),
                    isVisibleBecauseOfScrolling = firstItemVisible.not(),
                    onBackUpClick = { coroutineScope.launch { listState.scrollToItem(0) } }
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            content = { innerPadding ->
                MainContainer(
                    allItems = allItems,
                    navController = navController,
                    listState = listState,
                    swipeRefreshState = swipeRefreshState,
                    innerPadding = innerPadding,
                    snackbarHostState = snackbarHostState,
                    coroutineScope = coroutineScope
                )
            }
        )
    }
}

@Composable
fun MainContainer(
    allItems: LazyPagingItems<Item>,
    navController: NavHostController,
    listState: LazyListState,
    swipeRefreshState: SwipeRefreshState,
    innerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        SearchFilterChip("kotlin")

        ItemListContent(
            allItems = allItems,
            navController = navController,
            listState,
            swipeRefreshState,
            snackbarHostState,
            coroutineScope
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchFilterChip(language: String) {
    FilterChip(
        modifier = Modifier.padding(horizontal = 15.dp),
        selected = false,
        enabled = false,
        onClick = { /*TODO*/ },
        leadingIcon = { Icon(Icons.Filled.Check, contentDescription = null) },
        label = { Text(text = language) }
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ItemListContent(
    allItems: LazyPagingItems<Item>,
    navController: NavHostController,
    listState: LazyListState,
    swipeRefreshState: SwipeRefreshState,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
) {
    val decimalFormat = remember { DecimalFormat("#,###") }

    when (val result = allItems.loadState.refresh) {
        is LoadState.Loading -> swipeRefreshState.isRefreshing = true
        is LoadState.Error -> {
            swipeRefreshState.isRefreshing = false
            result.error.message?.let { msg ->
                ErrorSnackBar(
                    msg,
                    coroutineScope,
                    snackbarHostState,
                    onRetry = { allItems.refresh() }
                )
            }
        }
        is LoadState.NotLoading -> {
            swipeRefreshState.isRefreshing = false
            LazyColumn(
                state = listState,
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = allItems,
                    key = { it.id }
                ) { item ->
                    item?.let { item }?.let {
                        GithubRepoItemRow(
                            item = it,
                            decimalFormat = decimalFormat,
                            onSelectedRepoItem = { item ->
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    key = Const.REPO_ITEM_DETAILS_ARGUMENT_KEY,
                                    value = item
                                )
                                navController.navigate(Screen.Details.route) {
                                    popUpTo(Screen.Details.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
