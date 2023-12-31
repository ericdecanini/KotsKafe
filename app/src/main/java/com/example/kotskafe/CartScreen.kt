@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.kotskafe

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CartContent(navController: NavController) {
    Column {
        TopAppBar(
            title = { Text(text = "Cart") },
            actions = {},
        )
        Divider()

        if (cart.isNotEmpty()) {
            CartList(navController = navController)
        } else {
            EmptyCart()
        }
    }
}

fun onSubmitOrder(navController: NavController, context: Context) {
    cart.clear()
    navController.navigateUp()
    Toast.makeText(context, "Your order has been submitted", Toast.LENGTH_SHORT).show()
}

@Composable
fun CartItem(coffee: Coffee) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = coffee.name, fontWeight = FontWeight.Medium)
            Text(text = String.format("%.2f", coffee.price))
        }
        Divider()
    }
}

@Composable
fun EmptyCart() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Your cart is empty",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun CartList(navController: NavController) {
    val totalPrice = cart.sumOf { it.price }
    val context = LocalContext.current

    for (coffee in cart) {
        CartItem(coffee = coffee)
    }
    Text(
        modifier = Modifier.padding(16.dp),
        text = "Total: $" + String.format("%.2f", totalPrice),
        fontWeight = FontWeight.Medium
    )
    Button(
        modifier = Modifier.padding(16.dp),
        onClick = { onSubmitOrder(navController, context) },
    ) {
        Text(text = "Submit Order")
    }
}