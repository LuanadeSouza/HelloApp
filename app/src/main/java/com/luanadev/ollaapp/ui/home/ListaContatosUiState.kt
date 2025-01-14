package com.luanadev.ollaapp.ui.home

import com.luanadev.ollaapp.data.Contato

data class ListaContatosUiState(
    val contatos: List<Contato> = emptyList(),
    val logado: Boolean = true
)