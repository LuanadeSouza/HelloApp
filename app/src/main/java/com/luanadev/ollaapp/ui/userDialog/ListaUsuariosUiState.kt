package com.luanadev.ollaapp.ui.userDialog

import com.luanadev.ollaapp.data.Usuario

data class ListaUsuariosUiState(
    val nomeDeUsuario: String? = null,
    val nome: String? = null,
    val outrasContas: List<Usuario> = emptyList()
)