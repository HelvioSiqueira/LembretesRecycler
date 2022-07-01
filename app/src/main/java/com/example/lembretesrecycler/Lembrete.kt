package com.example.lembretesrecycler

import java.text.SimpleDateFormat
import java.util.*

data class Lembrete(
    var titulo: String = "",
    var texto: String = "",
    var prioridade: String = "",
    var data: String = ""
){
    init {
        data = obterData()
    }

    private fun obterData(): String {

        val date = Calendar.getInstance().time

        //Seleciona o formato da data
        val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        return dateTimeFormat.format(date).toString()
    }
}