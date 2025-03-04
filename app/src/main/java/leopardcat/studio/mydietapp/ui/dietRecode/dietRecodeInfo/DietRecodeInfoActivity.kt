package leopardcat.studio.mydietapp.ui.dietRecode.dietRecodeInfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import leopardcat.studio.mydietapp.ui.components.AnimatedText
import leopardcat.studio.mydietapp.ui.components.CustomGradientButton
import leopardcat.studio.mydietapp.ui.theme.MyDietAppTheme

class DietRecodeInfoActivity: ComponentActivity() {

    private val dietRecodeInfoViewModel by viewModels<DietRecodeInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val docId = intent.getStringExtra("docId")
        val name = intent.getStringExtra("name")
        val duration = intent.getStringExtra("duration")
        val calorie = intent.getStringExtra("calorie")

        setContent {

            MyDietAppTheme {

                DietRecodeInfoScreen(
                    dietRecodeInfoViewModel = dietRecodeInfoViewModel,
                    docId = docId ?: "",
                    name = name?: "",
                    duration = duration ?: "",
                    calorie = calorie ?: ""
                )

            }

        }

    }

}

@Composable
fun DietRecodeInfoScreen(
    docId: String,
    name: String,
    duration: String,
    calorie: String,
    dietRecodeInfoViewModel: DietRecodeInfoViewModel
) {

    val context = LocalContext.current
    val activity = context as DietRecodeInfoActivity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp)
    ) {

        AnimatedText(
            text = "Info",
            color = Color.Blue,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.padding(20.dp))

        Text(
            text = "운동명 : $name",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )

        Text(
            text = "운동시간 : $duration",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )

        Text(
            text = "소모 칼로리 : $calorie",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )

        Spacer(modifier = Modifier.padding(20.dp))

        CustomGradientButton(
            text = "삭제하기",
            onClick = {
                dietRecodeInfoViewModel.removeExerciseRecord(docId)
                activity.finish()
            },
            gradientColors = listOf(Color(0xFF6A1B9A), Color(0xFFAB47BC))
        )

        Spacer(modifier = Modifier.padding(10.dp))

        CustomGradientButton(
            text = "나가기",
            onClick = {
                activity.finish()
            },
            gradientColors = listOf(Color.Gray, Color.DarkGray)
        )

    }

}