package com.example.lembretesrecycler.repositorys

import com.example.lembretesrecycler.Lembrete
import java.util.*

object MemoryRepository : LembretesRepository {

    private var lembretes = mutableListOf<Lembrete>()

    init {
        add(Lembrete("Passear", "Passear com o cachorro", "Urgente", ""))
        add(Lembrete("Jogar", "Dlc de the Witcher 3", "Flexivel", ""))
        add(Lembrete("Correr na praça", "Até 19h", "Importante", ""))
        add(Lembrete("Passar roupa", "Passar roupa quando terminar de lavar", "Urgente", ""))
        add(Lembrete("Fazer compras", "Precisa comprar o que falta pra fazer o almoço(cebola, batata, macarrão)", "Urgente", ""))
    }

    override fun add(lembrete: Lembrete) {
        lembretes.add(lembrete)
    }

    override fun search(term: String, callback: (List<Lembrete>) -> Unit) {
        callback(
            if (term.isEmpty()) lembretes
            else lembretes.filter {
                it.titulo.uppercase().contains(term.uppercase())
            }
        )
    }

    //Passa o lembrete excluido como callback para ser possivel reverter a exclusão
    override fun remove(position: Int, term: String, callback: (Lembrete) -> Unit) {
        //Primeiramente faz um filtro usando o termo pesquisado pra pegar o lembrete a ser excluido
        //pela posição e depois exclui o lembrete(objeto)
        search(term){
            callback(it[position])

            lembretes.remove(it[position])
        }
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