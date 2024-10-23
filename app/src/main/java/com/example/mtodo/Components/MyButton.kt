package com.example.mtodo.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtodo.ui.theme.Purple40

@Composable
fun MyButton(onLoginClick:()->Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Purple40,
            contentColor = Color.White
        ), onClick =onLoginClick, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(50.dp)
    ) {
        Text(
            text = "Log In",
            fontSize = 18.sp
        )

    }
}