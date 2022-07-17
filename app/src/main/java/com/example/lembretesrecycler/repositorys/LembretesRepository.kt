package com.example.lembretesrecycler.repositorys

import com.example.lembretesrecycler.Lembrete

interface LembretesRepository {

    fun add(lembrete: Lembrete)
    fun search(term: String, callback: (List<Lembrete>) -> Unit)
    fun remove(position: Int, term: String = "", callback: (Lembrete) -> Unit)
    fun move(from: Int, to: Int)
    fun busca(lembrete: Lembrete): Boolean
    fun obterLembretes(): List<Lembrete>
}