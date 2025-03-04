package leopardcat.studio.mydietapp.ui.main.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import leopardcat.studio.mydietapp.MainViewModel
import leopardcat.studio.mydietapp.ui.common.DietRecordScreens
import leopardcat.studio.mydietapp.ui.dietRecode.DietRecodeInputScreen
import leopardcat.studio.mydietapp.ui.dietRecode.DietRecodeListScreen

@Composable
fun DietScreen(
    mainViewModel: MainViewModel
) {

     var currentScreen by rememberSaveable { mutableStateOf(DietRecordScreens.DietRecordInfo) }

    when(currentScreen) {
        DietRecordScreens.DietRecordInfo -> {
            DietRecodeListScreen(
                mainViewModel,
                onAddClicked = {
                    currentScreen = DietRecordScreens.DietRecordAdd
                }
            )
        }
        DietRecordScreens.DietRecordAdd -> {
            DietRecodeInputScreen(
                mainViewModel,
                onSaveClicked = {
                    currentScreen = DietRecordScreens.DietRecordInfo
                },
                onCancelClicked = {
                    currentScreen = DietRecordScreens.DietRecordInfo
                }
            )
        }
    }
}