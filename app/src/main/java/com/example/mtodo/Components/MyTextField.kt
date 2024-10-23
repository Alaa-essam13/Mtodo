package com.example.mtodo.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mtodo.ui.theme.Mypurple

@Composable
fun MyTextField(name: String = "", s: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value =s,
        onValueChange = { onTextChange(it) },
        label = { Text(text = name, color = Color.LightGray) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true, colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Mypurple,
            unfocusedBorderColor = Color.LightGray,
        ),
        shape = RoundedCornerShape(15.dp)
    )
}