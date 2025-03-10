package com.luanadev.ollaapp.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.luanadev.ollaapp.ui.form.FormularioContatoTela
import com.luanadev.ollaapp.util.ID_CONTATO
import com.luanadev.ollaapp.R
import com.luanadev.ollaapp.ui.form.FormularioContatoViewModel
import kotlinx.coroutines.launch

fun NavGraphBuilder.formularioContatoGraph(
    onVolta: () -> Unit
) {
    composable(
        route = FormularioContato.rotaComArgumentos,
        arguments = FormularioContato.argumentos
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getLong(
            ID_CONTATO
        )?.let {
            val viewModel = hiltViewModel<FormularioContatoViewModel>()
            val state by viewModel.uiState.collectAsState()
            val context = LocalContext.current

            LaunchedEffect(state.aniversario) {
                viewModel.defineTextoAniversario(
                    context.getString(R.string.aniversario)
                )
            }
            val coroutineScope = rememberCoroutineScope()

            FormularioContatoTela(
                state = state,
                onClickSalva = {
                    coroutineScope.launch {
                        viewModel.salva()
                    }
                    onVolta()
                },
                onCarregaImagem = {
                    viewModel.carregaImagem(it)
                }
            )
        }
    }
}