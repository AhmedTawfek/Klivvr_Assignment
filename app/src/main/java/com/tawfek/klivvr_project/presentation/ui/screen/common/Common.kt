package com.tawfek.klivvr_project.presentation.ui.screen.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerEffect(modifier: Modifier = Modifier) {
    // Simulating as a shimmer effect.
    // I will not create a real shimmer effect because it would take much time ... :(
    Text(text = "IAM A SHIMMER :)")
    Spacer(modifier = Modifier.height(10.dp))
    CircularProgressIndicator(modifier = modifier,color = Color.Gray)
}