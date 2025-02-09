package leopardcat.studio.mydietapp.ui.main.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import leopardcat.studio.mydietapp.MainViewModel
import leopardcat.studio.mydietapp.ui.common.Screen
import leopardcat.studio.mydietapp.ui.profile.ProfileEditScreen
import leopardcat.studio.mydietapp.ui.profile.ProfileInfoScreen

@Composable
fun ProfileScreen(mainViewModel: MainViewModel) {

    var currentScreen by remember { mutableStateOf(Screen.ProfileInfo) }
    val name by mainViewModel.name.collectAsState()
    val age by mainViewModel.age.collectAsState()
    val height by mainViewModel.height.collectAsState()

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
                    mainViewModel.saveProfile(newName, newAge, newHeight)
                    currentScreen = Screen.ProfileInfo
                },
                onCancelClicked = {
                    currentScreen = Screen.ProfileInfo
                }
            )
        }
    }
}