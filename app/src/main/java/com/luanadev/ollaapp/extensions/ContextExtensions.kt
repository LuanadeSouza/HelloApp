package com.luanadev.ollaapp.extensions

import android.content.Context
import android.widget.Toast

fun Context.mostraMensagem(
    texto: String,
    duracao: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(
        this,
        texto,
        duracao
    ).show()
}