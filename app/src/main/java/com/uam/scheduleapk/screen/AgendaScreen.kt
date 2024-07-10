package com.uam.scheduleapk.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.uam.scheduleapk.navigation.DetailAgenda
import com.uam.scheduleapk.viewmodel.AgendaViewModel
import com.uam.scheduleapk.viewmodel.AgendaViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AgendaScreen(padding: PaddingValues, navController : NavController) {

    val context = LocalContext.current



    val agendaModel = viewModel(
        AgendaViewModel::class.java,
        factory = AgendaViewModelFactory(context)
    )

    val collectState by agendaModel.state.collectAsState()


    if (collectState.loading) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    Scaffold(
        Modifier.padding(padding),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  navController.navigate(DetailAgenda("-1"))  },
                containerColor = (Color.Red),
                content = {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            )
        },
        content = {
            lazyData(collectState,navController)
        }
    )

}

@Composable
fun lazyData(
    collectState: AgendaViewModel.UIState,
    navController: NavController
) {
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(collectState.listAgenda) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
                    .clickable {
                        navController.navigate(DetailAgenda(it.id))
                    },
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = it.nombreCliente, fontWeight = Bold)
                    Text(text = it.motivo)
                    Text(text = it.fecha)
                    Text(text = it.comentarios)
                }
            }
        }
    }
}