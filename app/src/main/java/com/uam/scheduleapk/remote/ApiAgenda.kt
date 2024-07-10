package com.uam.scheduleapk.remote

import com.uam.scheduleapk.model.AgendaItem
import com.uam.scheduleapk.model.ListAgenda
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiAgenda {

    @GET("agenda/all")
    suspend fun getAll(): Response<ListAgenda>

    @POST("agenda/create")
    suspend fun create(@Body agenda: AgendaItem): Response<AgendaItem>
}