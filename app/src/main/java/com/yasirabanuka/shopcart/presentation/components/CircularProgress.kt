package com.yasirabanuka.shopcart.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yasirabanuka.shopcart.ui.theme.secondaryColor



@Composable
fun CircularProgress(
    modifier: Modifier = Modifier,
    color: Color = secondaryColor,
    strokeWidth: Dp = 1.dp
) {
    CircularProgressIndicator(
        strokeWidth = strokeWidth,
        modifier = modifier.size(15.dp),
        color = color
    )
}