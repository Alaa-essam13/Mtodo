package com.example.mtodo.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mtodo.R

@Composable
fun passwordtextField(password: String,passwordVisible:Boolean,onVisability:()->Unit,onValueChange:(String)->Unit) {
    OutlinedTextField(
        value = password,
        onValueChange = { onValueChange(it) },
        label = { Text(text = "Password", color = Color.LightGray) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible) R.drawable.closedeyes else R.drawable.openeye
            IconButton(onClick = onVisability) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "Toggle Password Visibility",
                    alpha = .3f,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(8.dp)
                )
            }
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.LightGray
        ),
        shape = RoundedCornerShape(15.dp)
    )
}