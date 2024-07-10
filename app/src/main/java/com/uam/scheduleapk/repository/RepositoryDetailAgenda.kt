package com.uam.scheduleapk.repository

import android.content.Context
import android.util.Log
import com.uam.scheduleapk.db.DataBaseApp
import com.uam.scheduleapk.db.toAgendaDb
import com.uam.scheduleapk.db.toAgendaDomain
import com.uam.scheduleapk.entity.Agenda

class RepositoryDetailAgenda(private val  context: Context) {

    val db = DataBaseApp.getDatabase(context)


    suspend fun getDetail(id : String) : Agenda {
        val agenda = db.agendaDAO().findById(id)
        Log.d("REPOSITORY","AGENDA $agenda")
        return agenda
    }

    suspend fun saveDetail(agenda : Agenda) {
        db.agendaDAO().insertObj(agenda)
    }

}
