package com.example.nfcall_in_one.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nfcall_in_one.ui.screens.*

object Routes {
    const val ONBOARDING = "onboarding"
    const val HOME = "home"
    const val READ_SCANNING = "read_scanning"
    const val READ_RESULT = "read_result"
    const val WRITE = "write"
    const val CLONE = "clone"
    const val TAG_DETAIL = "tag_detail"
    const val HISTORY = "history"
    const val SETTINGS = "settings"
}

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Routes.ONBOARDING
    ) {
        composable(Routes.ONBOARDING) {
            OnboardingScreen(
                onGetStarted = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.ONBOARDING) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.HOME) {
            HomeScreen(
                onNavigateToRead = { navController.navigate(Routes.READ_SCANNING) },
                onNavigateToWrite = { navController.navigate(Routes.WRITE) },
                onNavigateToClone = { navController.navigate(Routes.CLONE) },
                onNavigateToHistory = { navController.navigate(Routes.HISTORY) },
                onNavigateToSettings = { navController.navigate(Routes.SETTINGS) },
                onNavigateToTagDetail = { navController.navigate(Routes.TAG_DETAIL) }
            )
        }
        composable(Routes.READ_SCANNING) {
            ReadScanningScreen(
                onBack = { navController.popBackStack() },
                onTagDetected = { navController.navigate(Routes.READ_RESULT) }
            )
        }
        composable(Routes.READ_RESULT) {
            ReadResultScreen(
                onBack = { navController.popBackStack() },
                onWriteToNew = { navController.navigate(Routes.WRITE) },
                onClone = { navController.navigate(Routes.CLONE) }
            )
        }
        composable(Routes.WRITE) {
            WriteScreen(
                onBack = { navController.popBackStack() },
                onNavigateToHome = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                },
                onNavigateToHistory = { navController.navigate(Routes.HISTORY) }
            )
        }
        composable(Routes.CLONE) {
            CloneScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.TAG_DETAIL) {
            TagDetailScreen(
                onBack = { navController.popBackStack() },
                onClone = { navController.navigate(Routes.CLONE) }
            )
        }
        composable(Routes.HISTORY) {
            HistoryScreen(
                onBack = { navController.popBackStack() },
                onNavigateToHome = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                },
                onNavigateToWrite = { navController.navigate(Routes.WRITE) },
                onNewScan = { navController.navigate(Routes.READ_SCANNING) },
                onTagDetail = { navController.navigate(Routes.TAG_DETAIL) }
            )
        }
        composable(Routes.SETTINGS) {
            SettingsScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
