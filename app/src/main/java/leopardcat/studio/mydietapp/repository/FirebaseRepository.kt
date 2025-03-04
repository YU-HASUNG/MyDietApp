package leopardcat.studio.mydietapp.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import leopardcat.studio.mydietapp.model.Exercise

object FirebaseRepository {

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    private val db: FirebaseFirestore
        get() = FirebaseFirestore.getInstance()

    private val currentUser
        get() = FirebaseAuth.getInstance().currentUser

    //회원가입
    suspend fun signUp(email: String, password: String): FirebaseUser? {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        return result.user
    }

    //로그인
    suspend fun signIn(email: String, password: String): FirebaseUser? {
        val result = auth.signInWithEmailAndPassword(email, password).await()
        return result.user
    }

    //유저 정보 저장
    fun saveProfileData(name: String, age: String, height: String, onComplete: (Boolean) -> Unit) {
        val profile = hashMapOf(
            "name" to name,
            "age" to age,
            "height" to height
        )

        currentUser?.let { user ->
            db.collection("profiles")
                .document(user.uid)
                .set(profile)
                .addOnSuccessListener { onComplete(true) }
                .addOnFailureListener { onComplete(false) }
        } ?: onComplete(false)
    }

    //유저 정보 불러오기
    fun loadProfileData(onComplete: (String, String, String) -> Unit) {
        currentUser?.let { user ->
            db.collection("profiles")
                .document(user.uid)
                .get()
                .addOnSuccessListener { document ->
                    if(document != null && document.exists()) {
                        val name = document.getString("name") ?: ""
                        val age = document.getString("age") ?: ""
                        val height = document.getString("height") ?: ""
                        onComplete(name, age, height)
                    } else {
                        onComplete("", "", "")
                    }
                }
                .addOnFailureListener {
                    onComplete("", "", "")
                }
        } ?: onComplete("", "", "")
    }

    // 운동 기록 만들기
    suspend fun createExercise(exercise : Exercise){
        currentUser?.let { user->
            db.collection("diet_records")
                .document(user.uid)
                .collection("exercises")
                .add(exercise)
                .await()
        }
    }

    // 운동 기록 불러오기
    suspend fun readExercises() : List<Exercise> {

        return currentUser?.let { user->
            val documents = db.collection("diet_records")
                .document(user.uid)
                .collection("exercises")
                .get()
                .await()
            documents.documents.mapNotNull { document->
                document.toObject(Exercise::class.java)?.copy(docId = document.id)
            }
        } ?: emptyList()

    }

    // 운동 기록 삭제하기
    suspend fun removeExercise(documentId : String){
        currentUser?.let { user->
            db.collection("diet_records")
                .document(user.uid)
                .collection("exercises")
                .document(documentId)
                .delete()
                .await()
        }
    }
}