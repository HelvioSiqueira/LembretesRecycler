package com.example.lembretesrecycler

interface MainView {
    fun initSwipeGesture()
    fun showLembretes(): List<Lembrete>
}