package by.heroes.heroesofdisney.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import by.heroes.heroesofdisney.ui.StartViewModel
import by.heroes.heroesofdisney.ui.screen.AboutHero
import by.heroes.heroesofdisney.ui.screen.ListHeroes
import by.heroes.heroesofdisney.ui.screen.ShowView

@Composable
fun NavigationGraph(
    viewModel: StartViewModel,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = START_ROUTE) {
        composable(START_ROUTE) { ShowView(navController) }
        composable(HERO_LIST_ROUTE) { ListHeroes(viewModel, navController) }
        composable("$ABOUT_HERO_ROUTE/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            AboutHero(viewModel, savedStateHandle = it)
        }
    }
}