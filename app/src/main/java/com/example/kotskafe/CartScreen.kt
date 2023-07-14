@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.kotskafe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CartContent() {
    val totalPrice = cart.sumOf { it.price }

    Column {
        TopAppBar(
            title = { Text(text = "Cart") },
            actions = {},
        )
        Divider()
        for (coffee in cart) {
            CartItem(coffee = coffee)
        }
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Total: $" + String.format("%.2f", totalPrice),
            fontWeight = FontWeight.Medium
        )
    }
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