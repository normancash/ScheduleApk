package com.uam.scheduleapk.repository

import android.content.Context
import android.util.Log
import com.uam.scheduleapk.db.DataBaseApp
import com.uam.scheduleapk.entity.Agenda
import com.uam.scheduleapk.model.AgendaItem
import com.uam.scheduleapk.model.ListAgenda
import com.uam.scheduleapk.remote.ApiAdapter
import com.uam.scheduleapk.remote.ApiAgenda
import retrofit2.Response

class RepositoryAgenda(private val context : Context) {
    val apiAgenda : ApiAgenda by lazy {
        ApiAdapter.getInstance().create(ApiAgenda :: class.java)
    }

    suspend fun getAll() : Result<ListAgenda> {
        val retorno : ListAgenda
        return try {
            val response : Response<ListAgenda> = apiAgenda.getAll()
            retorno = response.body() as ListAgenda
            Log.d("OK", "${retorno}")
            Result.success(retorno)
        } catch (e : Exception) {
            Log.d("error", "${e.message}")
            Result.failure(e)
        }
    }

    suspend fun updateAgenda(lista : List<Agenda>) {
        val db = DataBaseApp.getDatabase(context)
        db.agendaDAO().deleteList(lista)
        db.agendaDAO().insertList(lista)
    }

    suspend fun createAgenda(agendaItem: AgendaItem) : Result<AgendaItem> {
        val retorno: AgendaItem
        return try {
            val response: Response<AgendaItem> = apiAgenda.create(agendaItem)
            retorno = response.body() as AgendaItem
            Result.success(retorno)
        } catch (e: Exception) {
            Log.d("error", "${e.message}")
            Result.failure(e)
        }
    }



}