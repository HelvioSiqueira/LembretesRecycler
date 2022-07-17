package com.example.lembretesrecycler.presenters

import com.example.lembretesrecycler.Lembrete
import com.example.lembretesrecycler.repositorys.LembretesRepository
import com.example.lembretesrecycler.views.MainView

class LembretesPresenter(
    private val view: MainView,
    private val repository: LembretesRepository
) {

    private var deletedLembrete = Lembrete()

    //Recebe o lembrete passado como callback e salva na variavel
    fun excluirLembrete(position: Int, term: String) {
        repository.remove(position, term){
            deletedLembrete = it
        }
        view.showMessageLembreteDeleted()
    }

    fun searchLembretes(term: String){
        repository.search(term){ lembretes ->
            view.showLembretes(lembretes)
        }
    }

    //Adiciona novamente novamente o lembrete salvo na deletedLembrete
    fun reverterExclusao(){
        repository.add(deletedLembrete)
    }

    fun trocarPosicao(from: Int, to: Int) {
        repository.move(from, to)
    }

    fun showLembretes(): List<Lembrete> {
        return repository.obterLembretes()
    }

}