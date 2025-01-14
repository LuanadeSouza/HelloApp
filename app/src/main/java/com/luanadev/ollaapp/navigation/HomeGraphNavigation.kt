package com.luanadev.ollaapp.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.luanadev.ollaapp.DestinosHelloApp
import com.luanadev.ollaapp.preferences.dataStore
import com.luanadev.ollaapp.ui.home.ListaContatosTela
import com.luanadev.ollaapp.ui.home.ListaContatosViewModel
import com.luanadev.ollaapp.ui.navegaParaDetalhes
import com.luanadev.ollaapp.ui.navegaParaFormularioContato
import com.luanadev.ollaapp.ui.navegaParaLoginDeslogado
import kotlinx.coroutines.launch

fun NavGraphBuilder.homeGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = DestinosHelloApp.ListaContatos.rota,
        route = DestinosHelloApp.HomeGraph.rota,
    ) {
        composable(route = DestinosHelloApp.ListaContatos.rota) {
            val viewModel = hiltViewModel<ListaContatosViewModel>()
            val state by viewModel.uiState.collectAsState()

            val dataStore = LocalContext.current.dataStore
            val coroutineScope = rememberCoroutineScope()

            ListaContatosTela(
                state = state,
                onClickAbreDetalhes = { idContato ->
                    navController.navegaParaDetalhes(idContato)
                },
                onClickAbreCadastro = {
                    navController.navegaParaFormularioContato()
                },
                onClickDesloga = {
                    coroutineScope.launch {
                        viewModel.desloga()
                        navController.navegaParaLoginDeslogado()
                    }
                })

        }
    }
}

