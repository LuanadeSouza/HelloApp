package com.luanadev.ollaapp.ui.userDialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luanadev.ollaapp.database.UsuarioDao
import com.luanadev.ollaapp.ui.userDialog.GerenciaUsuariosUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GerenciaUsuariosViewModel @Inject constructor(
    private val usuarioDao: UsuarioDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(GerenciaUsuariosUiState())
    val uiState: StateFlow<GerenciaUsuariosUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            carregaDados()
        }
    }

    private suspend fun carregaDados() {
        usuarioDao.buscaTodos().collect{
            _uiState.value = _uiState.value.copy(
                usuarios = it
            )
        }
    }
}