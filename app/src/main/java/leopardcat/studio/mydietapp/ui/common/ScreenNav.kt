package leopardcat.studio.mydietapp.ui.common

import leopardcat.studio.mydietapp.R

// 메인 화면 탭
enum class DietAppScreen(val icon: Int) {
    Home(R.drawable.tab1_icon),
    Diet(R.drawable.tab2_icon),
    Profile(R.drawable.tab3_icon)
}

// 프로필 화면 스크린들
enum class Screen {
    ProfileInfo,
    ProfileEdit
}