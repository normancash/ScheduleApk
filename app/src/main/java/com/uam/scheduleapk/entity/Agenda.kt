package com.uam.scheduleapk.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Agenda(
    @PrimaryKey
    val id: String,
    val comentarios: String,
    val fecha: String,
    val motivo: String,
    val nombreCliente: String
) {
    constructor() : this("","","","","")
}
