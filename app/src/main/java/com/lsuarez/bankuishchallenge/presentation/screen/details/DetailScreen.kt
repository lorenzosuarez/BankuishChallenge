package com.lsuarez.bankuishchallenge.presentation.screen.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.lsuarez.bankuishchallenge.R
import com.lsuarez.bankuishchallenge.presentation.components.ProfileImage
import com.lsuarez.bankuishchallenge.presentation.navigation.Screen
import com.lsuarez.domain.model.Item
import java.text.DecimalFormat

/**
 * Created by Lorenzo on 12/5/2022.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(repoItem: Item, navController: NavHostController, context: Context) {
    val decimalFormat = remember { DecimalFormat("#,###") }
    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screen.Home.route)
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
                        .verticalScroll(rememberScrollState())
                ) {
                    // Name repository
                    InformationItem(
                        painterResource = painterResource(R.drawable.ic_circle),
                        str = repoItem.fullName
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    // Topics
                    FlowRow(
                        modifier = Modifier.padding(4.dp),
                        mainAxisSize = SizeMode.Expand,
                        mainAxisSpacing = 3.dp,
                        crossAxisSpacing = 3.dp,
                    ) {
                        repoItem.topics.forEach { item ->
                            AssistChip(
                                onClick = { },
                                label = { Text(text = item) }
                            )
                        }
                    }
                    DividerLine()
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ProfileImage(repoItem.owner.avatarUrl)
                        Column(verticalArrangement = Arrangement.Center) {
                            Text(
                                text = "@${repoItem.owner.login}",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = "[${repoItem.owner.type.uppercase()}]",
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = stringResource(R.string.description),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                    repoItem.description?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    InformationItem(
                        painterResource(
                            R.drawable.ic_star
                        ),
                        decimalFormat.format(repoItem.stargazersCount),
                        Color(0xFFF4FF81)
                    )
                    DividerLine()
                    InformationItem(
                        painterResource(
                            R.drawable.ic_eye
                        ),
                        decimalFormat.format(repoItem.watchers),
                        Color(0xFF0D47A1)
                    )
                    DividerLine()
                    InformationItem(
                        painterResource(
                            R.drawable.ic_fork
                        ),
                        decimalFormat.format(repoItem.forks),
                        Color(0xFFFF1744)
                    )
                    DividerLine()
                    repoItem.homepage?.let { homepage ->
                        if (homepage.isNotEmpty()) {
                            InformationItem(
                                painterResource = painterResource(R.drawable.ic_link),
                                str = homepage,
                                color = Color(0xFF4214C5),
                                isURL = true
                            )
                            DividerLine()
                        }
                    }
                }
            },
            bottomBar = {
                OutlinedButton(
                    onClick = {
                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(repoItem.htmlUrl)
                            ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.see_on_github),
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        )
    }
}

@Composable
fun DividerLine() {
    Divider(
        modifier = Modifier.padding(top = 12.dp),
        color = MaterialTheme.colorScheme.outline,
        thickness = 0.2.dp
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun InformationItem(
    painterResource: Painter,
    str: String,
    color: Color = MaterialTheme.colorScheme.secondary,
    isURL: Boolean = false

) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource,
            contentDescription = null,
            tint = color
        )
        Text(
            text = str,
            modifier = Modifier.padding(start = 15.dp),
            style = MaterialTheme.typography.labelMedium,
            color = if (!isURL) MaterialTheme.colorScheme.outline else Color(0xFF4214C5)
        )
    }
}
