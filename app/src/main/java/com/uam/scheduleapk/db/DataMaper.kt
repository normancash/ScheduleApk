package com.uam.scheduleapk.db

import com.uam.scheduleapk.entity.Agenda as AgendaDb
import com.uam.scheduleapk.model.AgendaItem as AgendaDomain

fun AgendaDb.toAgendaDomain() : AgendaDomain = AgendaDomain(
    comentarios,fecha,id,motivo,nombreCliente
)
fun AgendaDomain.toAgendaDb() : AgendaDb = AgendaDb(
    id,comentarios,fecha,motivo,nombreCliente
)