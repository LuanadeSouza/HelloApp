package com.luanadev.ollaapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.helloapp.sampleData.contatosExemplo
import com.luanadev.ollaapp.R
import com.luanadev.ollaapp.data.Contato
import com.luanadev.ollaapp.ui.components.AsyncImagePerfil
import com.luanadev.ollaapp.ui.theme.HelloAppTheme


@Composable
fun ListaContatosTela(
    state: ListaContatosUiState,
    modifier: Modifier = Modifier,
    onClickListaUsuarios: () -> Unit = {},
    onClickAbreDetalhes: (Long) -> Unit = {},
    onClickAbreCadastro: () -> Unit = {},
    onClickBuscaContatos: () -> Unit = {}
) {
    Scaffold(topBar = {
        AppBarListaContatos(
            onClickListaUsuarios = onClickListaUsuarios,
            onClickBuscaContatos = onClickBuscaContatos
        )
    }, floatingActionButton = {
        FloatingActionButton(
            containerColor = MaterialTheme.colorScheme.primary,
            onClick = { onClickAbreCadastro() },
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.adicionar_novo_contato)
            )
        }
    }) { paddingValues ->
        LazyColumn(modifier.padding(paddingValues)) {
            items(state.contatos) { contato ->
                ContatoItem(contato) { idContato ->
                    onClickAbreDetalhes(idContato)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarListaContatos(onClickListaUsuarios: () -> Unit, onClickBuscaContatos: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.nome_do_app)) },
        actions = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onClickBuscaContatos) {
                    Icon(
                        Icons.Default.Search, contentDescription = stringResource(R.string.buscar)
                    )
                }

                Spacer(modifier = Modifier.size(8.dp))

                AsyncImagePerfil(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .clickable {
                            onClickListaUsuarios()
                        },
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    )
}

@Composable
fun ContatoItem(
    contato: Contato, onClick: (Long) -> Unit
) {
    Card(
        onClick = { onClick(contato.id) },
    ) {
        Row(
            Modifier.padding(16.dp),
        ) {
            AsyncImagePerfil(
                urlImagem = contato.fotoPerfil, modifier = Modifier
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
                    text = contato.nome,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(), text = contato.sobrenome
                )
            }
        }
    }
}

@Preview
@Composable
fun ListaContatosPreview() {
    HelloAppTheme {
        ListaContatosTela(
            state = ListaContatosUiState(contatosExemplo)
        )
    }
}

@Preview
@Composable
fun ContatoItemPreview() {
    HelloAppTheme {
        ContatoItem(contatosExemplo.first()) {}
    }
}
