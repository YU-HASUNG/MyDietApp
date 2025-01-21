package leopardcat.studio.mydietapp.ui.intro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import leopardcat.studio.mydietapp.ui.theme.MyDietAppTheme

class IntroActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MyDietAppTheme {
                Text(text = "Intro")
            }

        }
    }
}