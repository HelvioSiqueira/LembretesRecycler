package com.example.lembretesrecycler

class LembreteValidator {

    fun validate(info: Lembrete) = with(info){
        checkTitulo(titulo) && checkTexto(texto)
    }

    private fun checkTitulo(titulo: String) = titulo.length in 2..20
    private fun checkTexto(texto: String) = texto.length in 4..800
}