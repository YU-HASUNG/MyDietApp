package leopardcat.studio.mydietapp.ui.intro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import leopardcat.studio.mydietapp.MainActivity
import leopardcat.studio.mydietapp.ui.intro.screens.LoginScreen
import leopardcat.studio.mydietapp.ui.theme.MyDietAppTheme

class IntroActivity: ComponentActivity() {

    private val introViewModel: IntroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyDietAppTheme {

                val context = LocalContext.current

                val user by introViewModel.authState.collectAsStateWithLifecycle()
                val error by introViewModel.errorState.collectAsStateWithLifecycle()

                if(user == null)
                {
                    //로그인 회원가입이 안되어있음
                    LoginScreen(
                        onLogin = { email, password ->
                            introViewModel.signIn(email, password)
                        },
                        onSignUp = { email, password ->
                            introViewModel.signUp(email, password)
                        },
                        errorMessage = error
                    )
                }
                else
                {
                    //로그인 회원가입이 되어있음
                    //메인 화면으로 이동
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    finish()
                }

            }
        }
    }
}