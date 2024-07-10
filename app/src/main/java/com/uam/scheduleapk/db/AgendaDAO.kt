package com.uam.scheduleapk.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.uam.scheduleapk.entity.Agenda

@Dao
abstract class AgendaDAO:BaseDao<Agenda> {

    @Query("SELECT * FROM Agenda")
    suspend abstract fun getAll() : List<Agenda>

    @Query("SELECT * FROM Agenda WHERE id = :pid")
    suspend abstract fun findById(pid : String) : Agenda

   /* @Transaction
    suspend fun updateData(lista : List<Agenda>) {
        deleteList(lista)
        insertList(lista)
    }*/
}