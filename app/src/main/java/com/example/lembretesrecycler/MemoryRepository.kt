package com.example.lembretesrecycler

import java.util.*

object MemoryRepository : LembretesRepository {

    private var lembretes = mutableListOf<Lembrete>()

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
}