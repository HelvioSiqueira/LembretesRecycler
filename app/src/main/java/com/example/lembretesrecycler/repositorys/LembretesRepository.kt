package com.example.lembretesrecycler.repositorys

import com.example.lembretesrecycler.Lembrete

interface LembretesRepository {

    fun add(lembrete: Lembrete)
    fun remove(position: Int)
    fun move(from: Int, to: Int)
    fun busca(lembrete: Lembrete): Boolean
    fun obterLembretes(): List<Lembrete>
}