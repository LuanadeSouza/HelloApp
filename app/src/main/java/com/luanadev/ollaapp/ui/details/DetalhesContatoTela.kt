package com.luanadev.ollaapp.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luanadev.ollaapp.ui.components.AsyncImagePerfil
import com.luanadev.ollaapp.R
import com.luanadev.ollaapp.extensions.converteParaString
import com.luanadev.ollaapp.ui.theme.HelloAppTheme

@Composable
fun DetalhesContatoTela(
    state: DetalhesContatoUiState,
    modifier: Modifier = Modifier,
    onClickVoltar: () -> Unit = {},
    onClickEditar: () -> Unit = {},
    onApagaContato: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            DetalhesContatoAppBar(
                onClickVoltar = onClickVoltar,
                onClickApagar = onApagaContato,
                onClickEditar = onClickEditar
            )
        },
    ) { paddingValues ->
        Column(
            modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AsyncImagePerfil(
                urlImagem = state.fotoPerfil,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = state.nome,
                style = MaterialTheme.typography.headlineMedium
            )

            HorizontalDivider(thickness = 1.dp)

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(R.string.ligar),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Column(
                    Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(R.string.mensagem),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            HorizontalDivider(thickness = 1.dp)

            Column(
                Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {

                Text(
                    modifier = Modifier.padding(bottom = 22.dp),
                    text = stringResource(R.string.informacoes),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "${state.nome} ${state.sobrenome}",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = stringResource(R.string.nome_completo),
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = state.telefone,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    text = stringResource(id = R.string.telefone),
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )

                state.aniversario?.let {
                    Text(
                        text = it.converteParaString(),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.aniversario),
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesContatoAppBar(
    onClickVoltar: () -> Unit,
    onClickApagar: () -> Unit,
    onClickEditar: () -> Unit
) {
//    TopAppBar {
//        Row(
//            Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            IconButton(
//                onClick = onClickVoltar
//            ) {
//                Icon(
//                    Icons.Default.ArrowBack,
//                    tint = Color.White,
//                    contentDescription = stringResource(R.string.voltar)
//                )
//            }
//
//            Row {
//                IconButton(
//                    onClick = onClickEditar
//                ) {
//                    Icon(
//                        Icons.Default.Edit,
//                        tint = Color.White,
//                        contentDescription = stringResource(R.string.editar)
//                    )
//                }
//
//                IconButton(onClick = { onClickApagar() }) {
//                    Icon(
//                        Icons.Default.Delete,
//                        tint = Color.White,
//                        contentDescription = stringResource(R.string.deletar)
//                    )
//                }
//            }
//        }
//    }
}


@Preview
@Composable
fun DetalhesContatoScreenPreview() {
    HelloAppTheme {
        DetalhesContatoTela(state = DetalhesContatoUiState())
    }
}