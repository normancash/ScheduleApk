package com.uam.scheduleapk.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.uam.scheduleapk.db.toAgendaDb
import com.uam.scheduleapk.model.ListAgenda
import com.uam.scheduleapk.repository.RepositoryAgenda
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AgendaViewModel(context: Context) : ViewModel() {

    private val repository = RepositoryAgenda(context)

    private val _state = MutableStateFlow<UIState>(UIState())
    val state: StateFlow<UIState> = _state

    init {
        viewModelScope.launch {
            _state.update { it.copy(loading = true) }
            val response = repository.getAll()
            if (response.isSuccess) {
                _state.update{it.copy(listAgenda = response.getOrNull()!!)}
                repository.updateAgenda(response.getOrNull()!!.map { it.toAgendaDb()})
            }
            _state.update{it.copy(loading = false)}
        }
    }

    data class UIState(
        val listAgenda: ListAgenda = ListAgenda(),
        val loading: Boolean = false
    )

}

class AgendaViewModelFactory(private val context: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AgendaViewModel(context) as T
    }
}