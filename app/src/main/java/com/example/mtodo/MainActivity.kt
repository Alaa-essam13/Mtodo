package com.example.mtodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.mtodo.authScreens.LoginPage
import com.example.mtodo.authScreens.SignUp
import com.example.mtodo.data.TodoDatabase
import com.example.mtodo.firstTimeScreens.OnBoardingScreen
import com.example.mtodo.pages.TodoViewModel
import com.example.mtodo.pages.addpage.addPage
import com.example.mtodo.pages.homepage.Home
import com.example.mtodo.pages.tasks.TaskList
import com.example.mtodo.ui.theme.MtodoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            name = "todos_db"
        ).fallbackToDestructiveMigration().build()
    }
    private val viewModel by viewModels<TodoViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TodoViewModel(db.dao) as T
                }
            }
        }
    )
    private val onboardingutils by lazy { onboarding(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MtodoTheme {
                Surface {
                    if (onboardingutils.isOnboardingCompleted())
                        navigation()
                    else {
                        ShowOnboardingScreen()
                    }

                }
            }
        }
    }

    @Composable
    fun ShowOnboardingScreen() {
        val scope = rememberCoroutineScope()
        OnBoardingScreen {
            onboardingutils.markOnboardingCompleted()
            scope.launch {
                setContent {
                    navigation()
                }
            }
        }
    }

    @Composable
    fun navigation(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "Home") {
//            composable("Login") {
//                LoginPage(
//                    onLoginClick = { navController.navigate("Home") },
//                    onRegisterClick = { navController.navigate("SignUp") })
//            }
//            composable("SignUp") {
//                SignUp(
//                    onLoginClick = { navController.navigate("Home") },
//                    onBackToLoginClick = { navController.popBackStack() })
//            }
            composable("Home") { Home(viewModel, onTasksClick = {navController.navigate("AllTasks")}) { navController.navigate("Add") } }
            composable(
                "Add",
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { 1000 },
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { 1000 },
                        animationSpec = tween(700)
                    )
                }) { addPage(viewModel){
                    navController.popBackStack()
            } }
            composable(
                "AllTasks",
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { 1000 },
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { 1000 },
                        animationSpec = tween(700)
                    )
                }) { TaskList(viewModel) }
        }
    }
}
