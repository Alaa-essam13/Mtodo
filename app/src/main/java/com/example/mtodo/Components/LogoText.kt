package com.example.mtodo.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mtodo.ui.theme.Mypurple

@Composable
fun LogoText() {
    Text(
        text = "Mtodo",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Mypurple,
        modifier = Modifier.fillMaxWidth()
    )
}