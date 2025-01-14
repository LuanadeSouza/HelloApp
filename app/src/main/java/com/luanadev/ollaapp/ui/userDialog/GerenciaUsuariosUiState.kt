package com.luanadev.ollaapp.ui.userDialog

import com.luanadev.ollaapp.data.Usuario

data class GerenciaUsuariosUiState(
    val usuarios: List<Usuario> = emptyList()
)