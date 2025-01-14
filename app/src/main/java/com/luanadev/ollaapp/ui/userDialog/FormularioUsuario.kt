package com.luanadev.ollaapp.ui.userDialog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luanadev.ollaapp.R
import com.luanadev.ollaapp.ui.components.CaixaDialogoConfirmacao
import com.luanadev.ollaapp.ui.theme.HelloAppTheme

@Composable
fun FormularioUsuarioTela(
    state: FormularioUsuarioUiState,
    modifier: Modifier = Modifier,
    onClickVolta: () -> Unit = {},
    onClickSalva: () -> Unit = {},
    onClickApaga: () -> Unit = {},
    onClickMostraMensagemExclusao: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            FormularioUsuarioAppBar(onClickVoltar = onClickVolta)
        },
    ) { paddingValues ->
        Column(
            modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val focuAtual = LocalFocusManager.current

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    enabled = false,
                    value = state.nomeUsuario,
                    onValueChange = {},
                    label = { Text(stringResource(id = R.string.usuario)) },
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = null
                        )
                    },
                    value = state.nome,
                    onValueChange = state.onNomeMudou,
                    label = { Text(stringResource(id = R.string.nome)) },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = (KeyboardActions(onNext = { focuAtual.moveFocus(FocusDirection.Next) }))
                )

                Spacer(Modifier.height(16.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(56.dp), onClick = onClickSalva
                ) {
                    Text(text = stringResource(R.string.salvar))
                }

                Spacer(Modifier.height(8.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(56.dp),
                    onClick = onClickMostraMensagemExclusao,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {
                    Text(
                        text = stringResource(R.string.excluir_usuario),
                        color = Color.White
                    )
                }
            }
        }
    }

    if (state.mostraMensagemExclusao) {
        CaixaDialogoConfirmacao(
            titulo = stringResource(R.string.tem_certeza),
            mensagem = stringResource(R.string.aviso_apagar_usuario),
            onClikConfirma = onClickApaga,
            onClickCancela = { state.mostraMensagemExclusaoMudou(false) },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioUsuarioAppBar(onClickVoltar: () -> Unit = {}) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.editar_usuario)) },
        navigationIcon = {
            IconButton(
                onClick = onClickVoltar
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.voltar)
                )
            }
        }
    )
}

@Preview
@Composable
fun FormularioUsuarioTelaPreview() {
    HelloAppTheme {
        FormularioUsuarioTela(state = FormularioUsuarioUiState())
    }
}