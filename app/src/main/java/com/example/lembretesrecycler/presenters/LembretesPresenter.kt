package com.example.lembretesrecycler.presenters

import com.example.lembretesrecycler.Lembrete
import com.example.lembretesrecycler.repositorys.LembretesRepository
import com.example.lembretesrecycler.views.MainView

class LembretesPresenter(
    private val view: MainView,
    private val repository: LembretesRepository
) {

    private var deletedLembrete = Lembrete()

    fun excluirLembrete(position: Int) {
        repository.remove(position){
            deletedLembrete = it
        }
        view.showMessageLembreteDeleted()
    }

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