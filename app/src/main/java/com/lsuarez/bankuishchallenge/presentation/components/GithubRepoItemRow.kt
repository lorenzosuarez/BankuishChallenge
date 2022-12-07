package com.lsuarez.bankuishchallenge.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.lsuarez.domain.model.Item
import java.text.DecimalFormat

/**
 * Created by Lorenzo on 12/6/2022.
 */

@Composable
fun GithubRepoItemRow(
    repoItem: Item,
    decimalFormat: DecimalFormat,
    onSelectedRepoItem: (repoItem: Item) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp
            )
            .clickable {
                onSelectedRepoItem(repoItem)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                ProfileImage(
                    repoItem.owner.avatarUrl,
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "@${repoItem.owner.login}",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = "[${repoItem.owner.type.uppercase()}]",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
                Row(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(5.2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(1.5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Star count",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(10.dp)
                    )

                    Text(
                        text = decimalFormat.format(repoItem.stargazersCount),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            Divider(
                modifier = Modifier.padding(top = 12.dp),
                color = MaterialTheme.colorScheme.outline,
                thickness = 0.2.dp
            )

            Text(
                text = repoItem.fullName,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 10.dp, bottom = 8.dp),
            )
            Text(
                text = repoItem.description ?: "",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
