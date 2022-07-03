package com.example.lembretesrecycler

import java.util.*

object MemoryRepository : LembretesRepository {

    private var lembretes = mutableListOf<Lembrete>()

    init {
        add(Lembrete("Passear", "Passear com o cachorro", "Urgente", "01/07/2022"))
    }

    override fun add(lembrete: Lembrete) {
        lembretes.add(lembrete)
    }

    override fun remove(position: Int) {
        lembretes.removeAt(position)
    }

    override fun move(from: Int, to: Int) {
        Collections.swap(lembretes, from, to)
    }

    override fun busca(lembrete: Lembrete): Boolean {

        var achou = false

        lembretes.forEach {
            achou = it.titulo == lembrete.titulo
        }

        return achou
    }

    override fun obterLembretes(): List<Lembrete> {
        return this.lembretes
    }

    override fun recuperarLembretesAoRotacionar(lembretesSalvos: Any?) {
        lembretes.addAll(lembretesSalvos as Collection<Lembrete>)
    }
}