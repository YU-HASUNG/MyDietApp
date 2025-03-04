package leopardcat.studio.mydietapp.ui.dietRecode

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import leopardcat.studio.mydietapp.MainViewModel
import leopardcat.studio.mydietapp.model.Exercise
import leopardcat.studio.mydietapp.ui.components.AnimatedText
import leopardcat.studio.mydietapp.ui.components.CustomGradientButton
import leopardcat.studio.mydietapp.ui.components.CustomTextField

@Composable
fun DietRecodeInputScreen(
    mainViewModel: MainViewModel,
    onSaveClicked: () -> Unit,
    onCancelClicked: () -> Unit
) {

    val context = LocalContext.current

    var exerciseType by remember { mutableStateOf("") }
    var calorie by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Spacer(modifier = Modifier.padding(20.dp))

            AnimatedText(
                text = "Diet Record Input",
                color = Color.White,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.padding(20.dp))

            CustomTextField(
                value = exerciseType,
                onValueChange = { exerciseType = it },
                placeholder = "운동 종류"
            )

            Spacer(modifier = Modifier.padding(5.dp))

            CustomTextField(
                value = calorie,
                onValueChange = { calorie = it },
                placeholder = "칼로리"
            )

            Spacer(modifier = Modifier.padding(5.dp))

            CustomTextField(
                value = duration,
                onValueChange = { duration = it },
                placeholder = "시간"
            )



        }


    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        Column(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 50.dp)
        ) {

            CustomGradientButton(
                text = "저장",
                onClick = {

                    val calorieValue = calorie.toIntOrNull()
                    val durationValue = duration.toIntOrNull()
                    if(exerciseType.isEmpty() || calorieValue == null || durationValue == null)
                    {
                        Toast.makeText(context, "입력값을 확인해주세요", Toast.LENGTH_SHORT).show()
                    } else {
                        val exercise = Exercise(
                            docId = "",
                            name = exerciseType,
                            calorie = calorieValue,
                            duration = durationValue
                        )
                        mainViewModel.saveExerciseRecord(exercise)
                        onSaveClicked()
                    }
                },
                gradientColors = listOf(Color(0xFF6A1B9A), Color(0xFFAB47BC))
            )

            Spacer(modifier = Modifier.padding(5.dp))

            CustomGradientButton(
                text = "취소",
                onClick = {
                    onCancelClicked()
                },
                gradientColors = listOf(Color.Gray, Color.DarkGray)
            )

        }

    }


}

@Preview
@Composable
fun DietRecodeInputScreenPreview() {
//    DietRecodeInputScreen()
}