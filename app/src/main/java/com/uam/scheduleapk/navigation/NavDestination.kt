package com.uam.scheduleapk.navigation

import kotlinx.serialization.Serializable


@Serializable
object Home

@Serializable
object Agenda

@Serializable
data class DetailAgenda(val id : String)
