package com.yasirabanuka.shopcart.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yasirabanuka.shopcart.R
import com.yasirabanuka.shopcart.ui.theme.backgroundColor


@Composable
fun IncrementDecrementContainer(
    quantity: Int,
    increment: () -> Unit,
    decrement: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IncrementDecrement(icon = R.drawable.ic_minus) { decrement() }
        Text(text = quantity.toString(), style = MaterialTheme.typography.h2, fontSize = 11.sp)
        IncrementDecrement(icon = R.drawable.ic_add) { increment() }
    }
}

@Composable
fun IncrementDecrement(
    icon: Int,
    onClick: () -> Unit,
) {
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(22.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(backgroundColor)
        )
    }
}