package com.lsuarez.bankuishchallenge.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.lsuarez.bankuishchallenge.R

/**
 * Created by Lorenzo on 12/6/2022.
 */

@Composable
fun ProfileImage(
    data: String
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_launcher_background),
        contentDescription = "Avatar",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .size(45.dp)
            .clip(RoundedCornerShape(size = 10.dp))
            .background(Color.White)
    )
}