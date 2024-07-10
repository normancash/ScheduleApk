package com.uam.scheduleapk.screen

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.uam.scheduleapk.MainCompose
import com.uam.scheduleapk.R
import com.uam.scheduleapk.compose.TextCustomField
import com.uam.scheduleapk.navigation.Agenda
import com.uam.scheduleapk.viewmodel.DetailAgendaViewModel
import com.uam.scheduleapk.viewmodel.DetailAgendaViewModelFactory

@Composable
fun DetailAgendaScreen(padding: PaddingValues, navController: NavHostController, idAgenda: String) {

    val context = LocalContext.current

    val detailAgendaModel = viewModel(
        DetailAgendaViewModel::class.java,
        factory = DetailAgendaViewModelFactory(context,idAgenda)
    )

    val dato = detailAgendaModel.agendaMutable

    val state = detailAgendaModel.mstate

    /*LaunchedEffect(key1 = detailAgendaModel.error) {
        if (!detailAgendaModel.error) {
            navController.navigate(Agenda)
        }
        Toast.makeText(context,detailAgendaModel.mensaje,Toast.LENGTH_LONG).show()
        detailAgendaModel.resetDato()
    }*/

    LaunchedEffect(key1 = state.mensaje) {
        if (state.mensaje != null){
            Toast.makeText(context,state.mensaje,Toast.LENGTH_LONG).show()
            if (!state.error) {
                navController.navigate(Agenda)
            }
            detailAgendaModel.resetDato()
        }
    }
   /* if (detailAgendaModel.log) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }*/
    Log.d("COMPOSE","AGENDA ${dato}")
    Box(modifier = Modifier.padding(padding)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween) {
            TextCustomField(name = dato.nombreCliente,
                pLabel = stringResource(id = R.string.nombreCliente)
                ,onNameChange = {detailAgendaModel.onNombreCliente(it)})
            TextCustomField(name = dato.motivo,
                pLabel = stringResource(id = R.string.motivo)
                ,onNameChange = {detailAgendaModel.onMotivo(it)})
            TextCustomField(name = dato.fecha,
                pLabel = stringResource(id = R.string.fecha)
                ,onNameChange = {detailAgendaModel.onFecha(it)})
            TextCustomField(name = dato.comentarios,
                pLabel = stringResource(id = R.string.comentarios)
                ,onNameChange = {detailAgendaModel.onComentarios(it)})

            Row()
            {
                Button(onClick = {detailAgendaModel.onSave(idAgenda)}) {
                    Text(text= stringResource(id = R.string.saveAgenda))
                }
                Button(onClick = {navController.popBackStack()}) {
                    Text(text= stringResource(id = R.string.cancelBoton))
                }
            }

        }
    }

}