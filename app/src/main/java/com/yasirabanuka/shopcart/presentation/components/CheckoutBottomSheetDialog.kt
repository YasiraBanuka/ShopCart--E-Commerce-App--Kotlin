package com.yasirabanuka.shopcart.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yasirabanuka.shopcart.presentation.screens.cart.CheckoutState
import com.yasirabanuka.shopcart.ui.theme.disableColor
import com.yasirabanuka.shopcart.ui.theme.primaryColor
import com.yasirabanuka.shopcart.utils.noRippleClickable



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CheckoutBottomSheetDialog(
    bottomSheetState: ModalBottomSheetState,
    checkoutState: CheckoutState,
    onCheckout: () -> Unit
) {
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetBackgroundColor = Color.White,
        sheetContent = {
            SheetContent(checkoutState, onCheckout)
        }) {}
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SheetContent(
    checkoutState: CheckoutState,
    onCheckout: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        Text(text = "Order Details", style = MaterialTheme.typography.h1)

        Spacer(modifier = Modifier.height(25.dp))

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Price (${checkoutState.items} items)", style = MaterialTheme.typography.body1)
            Text(text = "LKR ${checkoutState.price}", style = MaterialTheme.typography.body1)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Delivery Charges", style = MaterialTheme.typography.body1)
            Text(text = checkoutState.charges, style = MaterialTheme.typography.body1)
        }

        Spacer(modifier = Modifier.height(5.dp))
        
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(disableColor))

        Spacer(modifier = Modifier.height(5.dp))

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Grand Total", style = MaterialTheme.typography.h2, fontSize = 16.sp)
            Text(text = "LKR ${checkoutState.grandTotal}", style = MaterialTheme.typography.h2, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(primaryColor)
                .padding(vertical = 10.dp)
                .noRippleClickable { onCheckout() }
        ) {
            Text(text = "Check out", style = MaterialTheme.typography.button)
        }
    }
}
