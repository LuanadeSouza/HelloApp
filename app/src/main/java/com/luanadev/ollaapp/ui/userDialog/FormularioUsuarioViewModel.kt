package com.luanadev.ollaapp.ui.userDialog

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luanadev.ollaapp.data.Usuario
import com.luanadev.ollaapp.database.UsuarioDao
import br.com.alura.helloapp.preferences.PreferencesKey
import com.luanadev.ollaapp.util.ID_USUARIO_ATUAL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormularioUsuarioViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dataStore: DataStore<Preferences>,
    private val usuarioDao: UsuarioDao
) : ViewModel() {

    private val nomeUsuario = savedStateHandle.get<String>(ID_USUARIO_ATUAL)

    private val _uiState = MutableStateFlow(FormularioUsuarioUiState())
    val uiState: StateFlow<FormularioUsuarioUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            nomeUsuario?.let {
                usuarioDao.buscaPorId(it).first()?.let { usuarioBuscado ->
                    _uiState.value = _uiState.value.copy(
                        nomeUsuario = usuarioBuscado.idUsuario,
                        nome = usuarioBuscado.nome,
                        senha = usuarioBuscado.senha
                    )
                }
            }
        }

        _uiState.update { state ->
            state.copy(onNomeMudou = {
                _uiState.value = _uiState.value.copy(
                    nome = it
                )
            })
        }
    }

    fun onClickMostraMensagemExclusao() {
        _uiState.value = _uiState.value.copy(
            mostraMensagemExclusao = true
        )
    }

    suspend fun atualiza() {
        usuarioDao.atualiza(
            Usuario(
                idUsuario = _uiState.value.nomeUsuario,
                nome = _uiState.value.nome,
                senha = _uiState.value.senha
            )
        )
    }

    suspend fun apaga() {
        usuarioDao.apaga(Usuario(idUsuario = _uiState.value.nomeUsuario))
        if (nomeUsuario.equals(dataStore.data.first()[PreferencesKey.USUARIO_ATUAL])) {
            dataStore.edit { it.remove(PreferencesKey.USUARIO_ATUAL) }
        }
    }
}