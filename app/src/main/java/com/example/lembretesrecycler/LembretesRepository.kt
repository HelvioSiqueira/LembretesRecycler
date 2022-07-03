package com.example.lembretesrecycler

interface LembretesRepository {

    fun add(lembrete: Lembrete)
    fun remove(position: Int)
    fun move(from: Int, to: Int)
    fun busca(lembrete: Lembrete): Boolean
    fun obterLembretes(): List<Lembrete>
    fun recuperarLembretesAoRotacionar(lembretesSalvos: Any?)
}