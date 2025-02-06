package leopardcat.studio.mydietapp.ui.main.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import leopardcat.studio.mydietapp.ui.profile.ProfileEditScreen
import leopardcat.studio.mydietapp.ui.profile.ProfileInfoScreen

enum class Screen {
    ProfileInfo,
    ProfileEdit
}

@Composable
fun ProfileScreen() {

    var currentScreen by remember { mutableStateOf(Screen.ProfileInfo) }
    var name by rememberSaveable { mutableStateOf("이름을 입력해주세요") }
    var age by rememberSaveable { mutableStateOf("20") }
    var height by rememberSaveable { mutableStateOf("175") }

    when(currentScreen) {
        Screen.ProfileInfo -> {
            ProfileInfoScreen(
                name = name,
                age = age,
                height = height,
                onEditClicked = {
                    currentScreen = Screen.ProfileEdit
                }
            )
        }
        Screen.ProfileEdit -> {
            ProfileEditScreen(
                initialName = name,
                initialAge = age,
                initialHeight = height,
                onSaveClicked = { newName, newAge, newHeight ->
                    name = newName
                    age = newAge
                    height = newHeight
                    currentScreen = Screen.ProfileInfo
                },
                onCancelClicked = {
                    currentScreen = Screen.ProfileInfo
                }
            )
        }
    }
}