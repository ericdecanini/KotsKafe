package com.example.kotskafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotskafe.ui.theme.KotsKafeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotsKafeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    HomeContent()
                }
            }
        }
    }
}

@Composable
fun HomeContent() {
    Column {
        Image(painter = painterResource(id = R.drawable.coffee), contentDescription = null)
        Text(modifier = Modifier.padding(16.dp), text = "Coffees", fontSize = 24.sp, fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotsKafeTheme {
        HomeContent()
    }
}