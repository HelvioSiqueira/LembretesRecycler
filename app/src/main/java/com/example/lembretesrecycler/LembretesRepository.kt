package com.example.lembretesrecycler

interface LembretesRepository {
    fun add(lembrete: Lembrete)
    fun remove(position: Int)
    fun move(from: Int, to: Int)
    fun obterLembretes(): List<Lembrete>
    fun recuperarLembretesAoRotacionar(lembretesSalvos: Any?)
}