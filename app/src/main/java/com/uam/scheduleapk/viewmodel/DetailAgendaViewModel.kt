package com.uam.scheduleapk.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.uam.scheduleapk.db.toAgendaDomain
import com.uam.scheduleapk.entity.Agenda
import com.uam.scheduleapk.repository.RepositoryAgenda
import com.uam.scheduleapk.repository.RepositoryDetailAgenda
import kotlinx.coroutines.launch


class DetailAgendaViewModel( context: Context,idAgenda : String) : ViewModel(){

    val repository = RepositoryDetailAgenda(context)

    val repositoryAgenda = RepositoryAgenda(context)

    var agendaMutable by mutableStateOf(Agenda())
        private set

    var mstate by mutableStateOf(State())
        private set

    fun onNombreCliente(nombreCliente: String){
        agendaMutable = agendaMutable.copy(nombreCliente = nombreCliente)
    }

    fun onFecha(fecha : String) {
        agendaMutable = agendaMutable.copy(fecha = fecha)
    }

    fun onMotivo(motivo: String) {
        agendaMutable = agendaMutable.copy(motivo = motivo)
    }

    fun onComentarios(comentarios: String) {
        agendaMutable = agendaMutable.copy(comentarios = comentarios)
    }

    fun onSave(idAgenda: String) {
        if (idAgenda.equals("-1")) {
            viewModelScope.launch {
                //Save Api
                val response = repositoryAgenda.createAgenda(agendaMutable.toAgendaDomain())
                if (response.isSuccess) {
                   mstate = mstate.copy(error = false, mensaje = "Insercion con exito")
                }
                else {
                    mstate = mstate.copy(error = true
                        , mensaje = "Error al insertar ${response.exceptionOrNull()?.message}")
                }
                //Save db
                //repository.saveDetail(response.getOrNull()!!.toAgendaDb())
            }

        }
    }

    fun resetDato() {
        mstate = mstate.copy(error = false
            , mensaje = null)
    }


    init {
        viewModelScope.launch {
            Log.d("VIEWMODEL","IDAGENDA $idAgenda")
            if (idAgenda.equals("-1")) {
                agendaMutable = Agenda()
            }
            else {
                agendaMutable = repository.getDetail(idAgenda)
            }
            Log.d("VIEWMODEL","AGENDA $agendaMutable")
        }
    }

    data class State(
        val mensaje : String? = null,
        val error : Boolean = false
    )



}

class DetailAgendaViewModelFactory(private val context: Context, private val idAgenda : String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailAgendaViewModel(context,idAgenda) as T
    }
}