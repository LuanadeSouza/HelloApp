package com.luanadev.ollaapp.ui.userDialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luanadev.ollaapp.R
import com.luanadev.ollaapp.data.Usuario
import com.luanadev.ollaapp.ui.components.AsyncImagePerfil
import com.luanadev.ollaapp.ui.theme.HelloAppTheme

@Composable
fun GerenciaUsuariosTela(
    state: GerenciaUsuariosUiState,
    modifier: Modifier = Modifier,
    onClickAbreDetalhes: (String) -> Unit = {},
    onClickVolta: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            AppBarGerenciaUsuarios(
                onClickVolta = onClickVolta
            )
        }
    ) { paddingValues ->

        LazyColumn(modifier.padding(paddingValues)) {
            items(state.usuarios) { usuario ->
                UsuarioGerenciaItem(usuario) { nomeUsuario ->
                    onClickAbreDetalhes(nomeUsuario)
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarGerenciaUsuarios(
    onClickVolta: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.gerenciar_usuarios))
        },
        navigationIcon = {
            IconButton(
                onClick = onClickVolta
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.voltar)
                )
            }
        })
}

@Composable
fun UsuarioGerenciaItem(
    usuario: Usuario,
    onClick: (String) -> Unit
) {
    Card(
        onClick = { onClick(usuario.idUsuario) },
    ) {
        Row(
            Modifier.padding(16.dp),
        ) {
            AsyncImagePerfil(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Column(
                Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = usuario.nome,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = usuario.idUsuario
                )
            }
        }
    }
}


@Preview
@Composable
fun AppBarGerenciaUsuariosPreview() {
    HelloAppTheme {
        AppBarGerenciaUsuarios()
    }
}

@Preview
@Composable
fun BuscaContatosPreview() {
    HelloAppTheme {
        GerenciaUsuariosTela(
            state = GerenciaUsuariosUiState()
        )
    }
}
