package com.luanadev.ollaapp.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luanadev.ollaapp.R
import com.luanadev.ollaapp.ui.theme.HelloAppTheme

@Composable
fun FormularioLoginTela(
    state: FormularioLoginUiState,
    modifier: Modifier = Modifier,
    onSalva: () -> Unit = {}
) {
    Column(Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.helloapp_logo_blue),
                modifier = modifier
                    .size(180.dp),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.logo_do_app),
            )
            Text(
                text = stringResource(id = R.string.criar_nova_conta),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Column(
            modifier
                .padding(16.dp)
                .weight(2f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            val focuAtual = LocalFocusManager.current
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Face, contentDescription = null
                    )
                },
                value = state.nome,
                onValueChange = state.onNomeMudou,
                label = { Text(stringResource(id = R.string.nome)) },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = (KeyboardActions(onNext = {
                    focuAtual.moveFocus(FocusDirection.Next)
                }))
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person, contentDescription = null
                    )
                },
                value = state.usuario,
                onValueChange = state.onUsuarioMudou,
                label = { Text(stringResource(id = R.string.usuario)) },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = (KeyboardActions(onNext = {
                    focuAtual.moveFocus(FocusDirection.Next)
                }))
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock, contentDescription = null
                    )
                },
                value = state.senha,
                onValueChange = state.onSenhaMudou,
                label = { Text(stringResource(id = R.string.senha)) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
                ),
                keyboardActions = (KeyboardActions(onNext = { focuAtual.moveFocus(FocusDirection.Next) }))
            )

            Spacer(Modifier.height(16.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(56.dp),
                onClick = onSalva
            ) {
                Text(text = stringResource(R.string.criar))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroLoginScreenPreview() {
    HelloAppTheme {
        FormularioLoginTela(FormularioLoginUiState())
    }
}