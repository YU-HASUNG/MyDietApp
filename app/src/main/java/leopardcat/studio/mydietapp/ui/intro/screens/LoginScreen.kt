package leopardcat.studio.mydietapp.ui.intro.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import leopardcat.studio.mydietapp.R
import leopardcat.studio.mydietapp.ui.components.CustomButton
import leopardcat.studio.mydietapp.ui.components.CustomTextField

@Composable
fun LoginScreen(
    onLogin : (String, String) -> Unit,
    onSignUp : (String, String) -> Unit,
    errorMessage : String?
) {

    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(top = 100.dp, bottom = 32.dp)
                .size(200.dp)
                .clip(RoundedCornerShape(100.dp))
        )

        CustomTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "email"
        )

        CustomTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "password",
            isPassword = true
        )

        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                modifier = Modifier.padding(10.dp),
                fontSize = 16.sp
            )
        }

        CustomButton(
            text = "Login",
            onClick = {
                if(email.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    onLogin(email, password)
                }

            },
            backgroundColor = Color.Blue
        )

        CustomButton(
            text = "Sign Up",
            onClick = {
                if(email.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    onSignUp(email, password)
                }
            },
            backgroundColor = Color.DarkGray
        )


    }

}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onLogin = {_, _ -> },
        onSignUp = {_, _ -> },
        errorMessage = null
    )
}