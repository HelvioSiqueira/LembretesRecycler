package com.example.lembretesrecycler

import java.util.*

object MemoryRepository : LembretesRepository {

    private var lembretes = mutableListOf<Lembrete>()

    init {
        add(Lembrete("Passear", "Passear com o chachorro", "Urgente", "01/07/2022"))
    }

    override fun add(lembrete: Lembrete) {

        if (lembretes.contains(lembrete)) {
            val index = lembretes.indexOfFirst { it.titulo == lembrete.titulo }

            if (index > -1) {
                lembretes[index] = lembrete
            } else {
                lembretes.add(lembrete)
            }
        }
    }

    override fun remove(position: Int) {
        lembretes.removeAt(position)
    }

    override fun move(from: Int, to: Int) {
        Collections.swap(lembretes, from, to)
    }

    override fun obterLembretes(): List<Lembrete> {
        return lembretes
    }

    override fun recuperarLembretesAoRotacionar(lembretesSalvos: Any?) {
        lembretes.addAll(lembretesSalvos as Collection<Lembrete>)
    }
}