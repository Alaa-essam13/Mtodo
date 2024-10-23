package com.example.mtodo.authScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtodo.Components.LogoText
import com.example.mtodo.Components.MyTextField
import com.example.mtodo.Components.passwordtextField
import com.example.mtodo.R
import com.example.mtodo.ui.theme.Mypurple
import com.example.mtodo.ui.theme.Purple40


@Composable
fun LoginPage(onLoginClick: () -> Unit, onRegisterClick: () -> Unit) {
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
            TopHello()
            Spacer(modifier = Modifier.height(20.dp))
            MyTextField(name = "Email", s = email) { email = it }
            Spacer(modifier = Modifier.height(15.dp))
            passwordtextField(
                password = password,
                passwordVisible = passwordVisible,
                onVisability = { passwordVisible = !passwordVisible },
                onValueChange = { password = it })
            Spacer(modifier = Modifier.height(40.dp))
            MyLoginButton() { onLoginClick() }
            Spacer(modifier = Modifier.height(20.dp))
            FacebookAndGmailSection()
            Spacer(modifier = Modifier.height(40.dp))
            LastText() {
                onRegisterClick()
            }
        }
    }
}

@Composable
private fun LastText(onRegisterClick: () -> Unit) {
    Text(text = buildAnnotatedString {
        append("Not a member?")
        withStyle(
            style = SpanStyle(
                color = Purple40,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        ) {
            append(" Register now")
        }
    }, modifier = Modifier.clickable { onRegisterClick() })
}

@Composable
private fun FacebookAndGmailSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth(.8f)
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f))
        Text(
            text = "Or Continue With",
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Light
        )
        HorizontalDivider(modifier = Modifier.weight(1f))
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Google Button
        OutlinedButton(
            onClick = { /* Google Login */ },
            modifier = Modifier
                .height(48.dp)
                .weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Black
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Google Icon",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Google", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Facebook Button
        Button(
            onClick = { /* Facebook Login */ },
            modifier = Modifier
                .height(48.dp)
                .weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4267B2), // Facebook blue color
                contentColor = Color.White
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = "Facebook Icon",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Facebook", fontSize = 16.sp)
        }
    }
}

@Composable
private fun MyLoginButton(onLoginClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Purple40,
            contentColor = Color.White
        ), onClick = onLoginClick, modifier = Modifier
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

@Composable
private fun TopHello() {
    Text(
        text = "Hello Again",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Mypurple
    )
    Text(
        text = "Welcome Back",
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        color = Mypurple
    )
}

//@Preview(showSystemUi = true)
//@Composable
//fun LoginPagePreview() {
//    LoginPage()
//}