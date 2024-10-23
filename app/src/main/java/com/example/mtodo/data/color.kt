package com.example.mtodo.data

import androidx.compose.ui.graphics.Color
import com.example.mtodo.ui.theme.PrimaryColor
import com.example.mtodo.ui.theme.PrimaryColor2
import com.example.mtodo.ui.theme.PrimaryColor3
import com.example.mtodo.ui.theme.PrimaryColor4
import com.example.mtodo.ui.theme.PrimaryColor5
import com.example.mtodo.ui.theme.PrimaryColor6
import com.example.mtodo.ui.theme.SecondaryColor
import com.example.mtodo.ui.theme.SecondaryColor2
import com.example.mtodo.ui.theme.SecondaryColor3
import com.example.mtodo.ui.theme.SecondaryColor4
import com.example.mtodo.ui.theme.SecondaryColor5
import com.example.mtodo.ui.theme.SecondaryColor6

data class colors(val primaryColor: Color, val secondaryColor: Color)

val color = listOf(
    colors(PrimaryColor, SecondaryColor),
    colors(PrimaryColor2, SecondaryColor2),
    colors(PrimaryColor3, SecondaryColor3),
    colors(PrimaryColor4, SecondaryColor4),
    colors(PrimaryColor5, SecondaryColor5),
    colors(PrimaryColor6, SecondaryColor6)


)