package com.example.lembretesrecycler

class LembreteFormPresenter(
    private val view: LembreteFormView,
    private val repository: LembretesRepository) {

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