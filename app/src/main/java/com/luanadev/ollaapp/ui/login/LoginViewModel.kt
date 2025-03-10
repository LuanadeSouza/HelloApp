package com.luanadev.ollaapp.ui.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import br.com.alura.helloapp.preferences.PreferencesKey
import com.luanadev.ollaapp.database.UsuarioDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val usuarioDao: UsuarioDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState>
        get() = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(
                onUsuarioMudou = {
                    _uiState.value = _uiState.value.copy(
                        usuario = it
                    )
                },
                onSenhaMudou = {
                    _uiState.value = _uiState.value.copy(
                        senha = it
                    )
                },
            )
        }
    }

    suspend fun tentaLogar() {
        val usuarioBuscado = usuarioDao.buscaPorId(_uiState.value.usuario).first()

        if (usuarioBuscado != null &&
            usuarioBuscado.senha == _uiState.value.senha
        ) {
            dataStore.edit {
                it[PreferencesKey.LOGADO] = true
                it[PreferencesKey.USUARIO_ATUAL] = _uiState.value.usuario
            }
            logaUsuario()
        } else {
            exibeErro()
        }

    }

    private fun exibeErro() {
        _uiState.value = _uiState.value.copy(
            exibirErro = true
        )
    }

    private fun logaUsuario() {
        _uiState.value = _uiState.value.copy(
            logado = true
        )
    }
}

