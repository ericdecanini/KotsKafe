@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.kotskafe

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kotskafe.ui.theme.KotsKafeTheme

val availableCoffees = listOf(
    Coffee("Americano", 3.10),
    Coffee("Latte", 4.25),
    Coffee("Cappuccino", 4.25),
    Coffee("Espresso", 2.10),
    Coffee("Flat white", 3.80),
)

@Composable
fun HomeContent(navController: NavController) {
    Column {
        TopAppBar(
            title = { Text(text = "Kot's Kafe") },
            actions = {
                IconButton(onClick = { navController.navigate("cart") }) {
                    Box {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart")
                        Badge(
                            modifier = Modifier.alpha(if (cart.isNotEmpty()) 1f else 0f).align(Alignment.BottomEnd).padding(top = 12.dp, start = 12.dp),
                            containerColor = Color.Red,
                            contentColor = Color.White
                        ) {
                            Text(text = cart.size.toString(), fontSize = 12.sp)
                        }
                    }
                }
            }
        )

        Image(painter = painterResource(id = R.drawable.coffee), contentDescription = null)
        Text(modifier = Modifier.padding(16.dp), text = "Coffees", fontSize = 24.sp, fontWeight = FontWeight.Medium)

        Divider(modifier = Modifier.padding(top = 16.dp))
        for (coffee in availableCoffees) {
            CoffeeItem(coffee)
        }
    }
}

@Composable
fun CoffeeItem(coffee: Coffee) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                cart.add(coffee)
                Toast
                    .makeText(context, "${coffee.name} added to cart", Toast.LENGTH_SHORT)
                    .show()
                Log.v("MainActivity", "Cart: $cart")
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = coffee.name)
        Text(text = String.format("%.2f", coffee.price))
    }
    Divider()
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    KotsKafeTheme {
        HomeContent(rememberNavController())
    }
}