package com.example.lembretesrecycler

class LembretesPresenter(
    private val view: MainView,
    private val repository: LembretesRepository
) {

    fun addLembrete(lembrete: Lembrete) {
        repository.add(lembrete)
    }

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