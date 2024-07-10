package com.uam.scheduleapk.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update


interface BaseDao<T> {

    @Insert
    suspend fun insertObj(obj:T)

    @Insert
    suspend fun insertList(obj : List<T>)

    @Delete
    suspend fun deleteObj(obj:T)

    @Delete
    suspend fun deleteList(obj : List<T>)

    @Update
    suspend fun updateObj(obj : T)

}