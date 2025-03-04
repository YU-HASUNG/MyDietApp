package leopardcat.studio.mydietapp.ui.dietRecode.dietRecodeInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import leopardcat.studio.mydietapp.repository.FirebaseRepository

class DietRecodeInfoViewModel : ViewModel() {

    fun removeExerciseRecord(docId : String) {
        viewModelScope.launch {
            FirebaseRepository.removeExercise(docId)
        }
    }
}