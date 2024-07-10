package com.uam.scheduleapk.navigation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.uam.scheduleapk.screen.AgendaScreen
import com.uam.scheduleapk.screen.DetailAgendaScreen
import com.uam.scheduleapk.screen.LoginScreen



@Composable
fun AppNavigate(padding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Agenda, // custom type for first screen
    ) {
        composable<Home> { // custom type as generic
            LoginScreen(padding)
        }
        composable<Agenda> {
             AgendaScreen(padding,navController)
        }
        composable<DetailAgenda> { backStackEnty ->
            val detailAgenda = backStackEnty.toRoute<DetailAgenda>()
            Log.d("NAVIGATION","detail ${detailAgenda}")
            DetailAgendaScreen(padding,navController,detailAgenda.id)
        }
        // unpacking the back stack entry to obtain our value
        //val customValue = backstackEntry.toRoute<Routes.SecondScreen>()
        //Log.i("SecondScreen", customValue.customPrimitive)
        //AgendaApp(padding,navController)
    }
}


