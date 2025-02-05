package leopardcat.studio.mydietapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import leopardcat.studio.mydietapp.ui.main.DietAppScreen
import leopardcat.studio.mydietapp.ui.main.MainNavigation
import leopardcat.studio.mydietapp.ui.main.screens.DietScreen
import leopardcat.studio.mydietapp.ui.main.screens.HomeScreen
import leopardcat.studio.mydietapp.ui.main.screens.ProfileScreen
import leopardcat.studio.mydietapp.ui.theme.MyDietAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDietAppTheme {

                var currentScreen by remember{ mutableStateOf(DietAppScreen.Home) }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = 60.dp)
                            .fillMaxSize()
                    ) {
                        when(currentScreen) {
                            DietAppScreen.Home -> HomeScreen()
                            DietAppScreen.Diet -> DietScreen()
                            DietAppScreen.Profile -> ProfileScreen()
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    MainNavigation(currentScreen = currentScreen, onTabSelected = {
                        currentScreen = it
                    })
                }
            }
        }
    }
}
