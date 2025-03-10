package com.luanadev.ollaapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.luanadev.ollaapp.R

@Composable
fun AsyncImagePerfil(modifier: Modifier = Modifier, urlImagem: String? = null) {
    AsyncImage(
        modifier = modifier,
        contentScale = ContentScale.Crop,
        model = ImageRequest.Builder(LocalContext.current).data(urlImagem).build(),
        placeholder = painterResource(R.drawable.default_profile_picture),
        error = painterResource(R.drawable.default_profile_picture),
        contentDescription = stringResource(R.string.foto_perfil_contato),
    )
}