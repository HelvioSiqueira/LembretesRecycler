package com.example.lembretesrecycler.presenters

import com.example.lembretesrecycler.Lembrete
import com.example.lembretesrecycler.repositorys.LembretesRepository
import com.example.lembretesrecycler.views.MainView

class LembretesPresenter(
    private val view: MainView,
    private val repository: LembretesRepository
) {

    fun excluirLembrete(position: Int) {
        repository.remove(position)
    }

    fun trocarPosicao(from: Int, to: Int) {
        repository.move(from, to)
    }

    fun showLembretes(): List<Lembrete> {
        return repository.obterLembretes()
    }

}