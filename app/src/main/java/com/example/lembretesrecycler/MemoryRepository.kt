package com.example.lembretesrecycler

object MemoryRepository : LembretesRepository {

    private var lembretes = mutableListOf<Lembrete>()

    override fun add(lembrete: Lembrete){

        if(lembretes.contains(lembrete)){
            val index = lembretes.indexOfFirst { it.titulo == lembrete.titulo }

            if(index > -1){
                lembretes[index] = lembrete
            } else {
                lembretes.add(lembrete)
            }
        }

    }
}