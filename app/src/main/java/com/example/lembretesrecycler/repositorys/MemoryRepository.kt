package com.example.lembretesrecycler.repositorys

import com.example.lembretesrecycler.Lembrete
import java.util.*
import javax.security.auth.callback.Callback

object MemoryRepository : LembretesRepository {

    private var lembretes = mutableListOf<Lembrete>()

    init {
        add(Lembrete("Passear", "Passear com o cachorro", "Urgente", "01/07/2022"))
    }

    override fun add(lembrete: Lembrete) {
        lembretes.add(lembrete)
    }

    override fun remove(position: Int, callback: (Lembrete) -> Unit) {

        callback(lembretes[position])

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
        return lembretes
    }
}