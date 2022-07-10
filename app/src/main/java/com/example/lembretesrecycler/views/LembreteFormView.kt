package com.example.lembretesrecycler.views

import com.example.lembretesrecycler.Lembrete

interface LembreteFormView {
    fun saveLembrete(): Lembrete?
    fun errorInvalidLembrete()
    fun errorSaveLembrete()
}