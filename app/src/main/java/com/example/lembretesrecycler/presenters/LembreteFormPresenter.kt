package com.example.lembretesrecycler.presenters

import com.example.lembretesrecycler.Lembrete
import com.example.lembretesrecycler.LembreteValidator
import com.example.lembretesrecycler.repositorys.LembretesRepository
import com.example.lembretesrecycler.views.LembreteFormView

class LembreteFormPresenter(
    private val view: LembreteFormView,
    private val repository: LembretesRepository
) {

    private val validator = LembreteValidator()

    fun saveLembrete(lembrete: Lembrete): Boolean{
        return if(validator.validate(lembrete)){
            try {
                repository.add(lembrete)
                true
            } catch (e: Exception){
                view.errorSaveLembrete()
                false
            }
        } else {
            view.errorInvalidLembrete()
            false
        }
    }
}