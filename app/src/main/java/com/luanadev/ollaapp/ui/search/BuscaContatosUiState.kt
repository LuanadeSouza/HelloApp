package com.luanadev.ollaapp.ui.search

import com.luanadev.ollaapp.data.Contato

data class BuscaContatosUiState(
    val contatos: List<Contato> = emptyList(),
    val usuarioAtual: String? = null,
    val valorBusca: String = "",
    val onValorBuscaMudou: (String) -> Unit = {}
)