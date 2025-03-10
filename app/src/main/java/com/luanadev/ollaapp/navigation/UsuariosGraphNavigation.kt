package br.com.alura.helloapp.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import com.luanadev.ollaapp.util.ID_USUARIO_ATUAL
import com.luanadev.ollaapp.navigation.DestinosHelloApp
import com.luanadev.ollaapp.navigation.FormularioUsuario
import com.luanadev.ollaapp.navigation.ListaUsuarios
import com.luanadev.ollaapp.ui.userDialog.CaixaDialogoContasUsuario
import com.luanadev.ollaapp.ui.userDialog.FormularioUsuarioTela
import com.luanadev.ollaapp.ui.userDialog.FormularioUsuarioViewModel
import com.luanadev.ollaapp.ui.userDialog.GerenciaUsuariosTela
import com.luanadev.ollaapp.ui.userDialog.GerenciaUsuariosViewModel
import com.luanadev.ollaapp.ui.userDialog.ListaUsuariosViewModel
import kotlinx.coroutines.launch

fun NavGraphBuilder.usuariosGraph(
    onVolta: () -> Unit,
    onNavegaParaLogin: () -> Unit,
    onNavegaParaHome: () -> Unit,
    onNavegaGerenciaUsuarios: () -> Unit,
    onNavegaParaFormularioUsuario: (String) -> Unit,
) {
    navigation(
        startDestination = ListaUsuarios.rota,
        route = DestinosHelloApp.UsuariosGraph.rota
    ) {
        dialog(
            route = ListaUsuarios.rotaComArgumentos,
            arguments = ListaUsuarios.argumentos
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getLong(
                ID_USUARIO_ATUAL
            )?.let { usuarioAtual ->

                val viewModel = hiltViewModel<ListaUsuariosViewModel>()
                val state by viewModel.uiState.collectAsState()

                val coroutineScope = rememberCoroutineScope()

                CaixaDialogoContasUsuario(
                    state = state,
                    onClickDispensa = onVolta,
                    onClickAdicionaNovaConta = {
                        onNavegaParaLogin()
                    },
                    onClickListaContatosPorUsuario = { novoUsuario ->
                        coroutineScope.launch {
                            viewModel.atualizaUsuarioLogado(novoUsuario)
                            onNavegaParaHome()
                        }
                    },
                    onClickGerenciaUsuarios = {
                        onNavegaGerenciaUsuarios()
                    }
                )
            }
        }

        composable(
            route = DestinosHelloApp.GerenciaUsuarios.rota
        ) {
            val viewModel = hiltViewModel<GerenciaUsuariosViewModel>()
            val state by viewModel.uiState.collectAsState()

            GerenciaUsuariosTela(
                state = state,
                onClickAbreDetalhes = { usuarioAtual ->
                    onNavegaParaFormularioUsuario(usuarioAtual)
                },
                onClickVolta = onVolta
            )
        }

        composable(
            route = FormularioUsuario.rotaComArgumentos,
            arguments = FormularioUsuario.argumentos
        ) { usuarioAtual ->
            val viewModel = hiltViewModel<FormularioUsuarioViewModel>()
            val state by viewModel.uiState.collectAsState()
            val coroutineScope = rememberCoroutineScope()

            FormularioUsuarioTela(
                state = state,
                onClickVolta = onVolta,
                onClickSalva = {
                    coroutineScope.launch {
                        viewModel.atualiza()
                        onVolta()
                    }
                },
                onClickApaga = {
                    coroutineScope.launch {
                        viewModel.apaga()
                        onVolta()
                    }
                },
                onClickMostraMensagemExclusao = {
                    viewModel.onClickMostraMensagemExclusao()
                }
            )
        }
    }
}
