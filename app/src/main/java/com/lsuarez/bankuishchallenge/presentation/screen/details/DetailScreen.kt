package com.lsuarez.bankuishchallenge.presentation.screen.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lsuarez.bankuishchallenge.presentation.navigation.Screen
import com.lsuarez.domain.model.Item

/**
 * Created by Lorenzo on 12/5/2022.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(repoItem: Item, navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Home.route) {
                                    inclusive = true
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "github image"
                            )
                        }
                    },
                    title = { }
                )
            },
            floatingActionButton = {},
            floatingActionButtonPosition = FabPosition.End,
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 20.dp)
                ) {
                    // Name repository
                    Row {
                        Icon(
                            modifier = Modifier.size(15.dp),
                            imageVector = Icons.Filled.Info,
                            contentDescription = "github image"
                        )
                        Text(
                            modifier = Modifier.padding(start = 10.dp),
                            text = repoItem.fullName,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    // Topics
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        items(items = repoItem.topics, itemContent = { item ->
                            AssistChip(
                                onClick = { /*TODO*/ },
                                label = { Text(text = item) }
                            )
                        })
                    }
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = repoItem.fullName,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            }
        )
    }
}