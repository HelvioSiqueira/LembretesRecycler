package com.example.lembretesrecycler.views

import com.example.lembretesrecycler.Lembrete

interface MainView {
    fun initSwipeGesture()
    fun showLembretes(): List<Lembrete>
}