package com.example.mtodo.firstTimeScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtodo.R
import com.example.mtodo.ui.theme.Mypurple
import com.example.mtodo.ui.theme.Purple40

@Composable
fun ssScreen(modifier: Modifier = Modifier) {
    Column(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.img2),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.7f),
            contentScale = ContentScale.FillWidth
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                text = "Manage your tasks",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Mtodo", fontSize =30.sp, fontWeight = FontWeight.Bold, color = Mypurple)
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Mypurple,
                    contentColor = Color.White
                ), onClick = { /*TODO*/ }, modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 18.sp
                )

            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = buildAnnotatedString {
                append("Already have an account?")
                withStyle(
                    style = SpanStyle(color = Purple40, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                ){
                    append(" Log In")
                }
            }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun sscreenpev() {
    ssScreen()
}