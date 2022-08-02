package com.example.lembretesrecycler.di

import com.example.lembretesrecycler.SQLiteRepository
import com.example.lembretesrecycler.presenters.LembreteFormPresenter
import com.example.lembretesrecycler.presenters.LembretesPresenter
import com.example.lembretesrecycler.repositorys.LembretesRepository
import com.example.lembretesrecycler.views.LembreteFormView
import com.example.lembretesrecycler.views.MainView
import org.koin.dsl.module

val androidModule = module {
    single { this }

    single { SQLiteRepository(ctx = get()) as LembretesRepository }

    factory { (view: MainView)->
        LembretesPresenter(view, repository = get())
    }

    factory { (view: LembreteFormView)->
        LembreteFormPresenter(view, repository = get())
    }
}