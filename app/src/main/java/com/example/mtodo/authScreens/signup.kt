package com.example.mtodo.authScreens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtodo.Components.*
import com.example.mtodo.ui.theme.Mypurple
import com.example.mtodo.ui.theme.Purple40

@Composable
fun SignUp(onLoginClick: () -> Unit, onBackToLoginClick: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        LogoText()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            HelloMessage()
            Spacer(modifier = Modifier.height(20.dp))
            MyTextField("your name", name) { name = it }
            Spacer(modifier = Modifier.height(15.dp))
            MyTextField("Email", email) { email = it }
            Spacer(modifier = Modifier.height(15.dp))
            passwordtextField(
                password,
                passwordVisible,
                { passwordVisible = !passwordVisible },
                { password = it })
            Spacer(modifier = Modifier.height(10.dp))
            PrivacyText()
            Spacer(modifier = Modifier.height(40.dp))
            MyButton() { onLoginClick() }
            Spacer(modifier = Modifier.height(20.dp))
            LastText() { onBackToLoginClick() }
        }
    }
}

@Composable
private fun LastText(onLoginClick: () -> Unit) {
    Text(text = buildAnnotatedString {
        append("Already have an account?")
        withStyle(
            style = SpanStyle(
                color = Purple40,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        ) {
            append(" Log In")
        }
    }, color = Color.Gray, modifier = Modifier.clickable { onLoginClick() })
}


@Composable
private fun PrivacyText() {
    Text(text = buildAnnotatedString {
        append("by signing up you agree our ")
        withStyle(
            style = SpanStyle(
                color = Purple40,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("Terms & conditions")
        }
        append(" of use and ")
        withStyle(
            style = SpanStyle(
                color = Purple40,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("privacy policy.")
        }
    }, color = Color.Gray)
}


@Composable
private fun HelloMessage() {
    Text(
        text = "Hello!",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Mypurple
    )
    Text(
        text = "Welcome to Mtodo app",
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        color = Mypurple
    )
    Text(
        text = "sign up to get started.",
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        color = Mypurple
    )
}


//
//@Preview(showSystemUi = true)
//@Composable
//private fun pp() {
//    SignUp()
//}