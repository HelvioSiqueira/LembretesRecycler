package com.example.lembretesrecycler

interface MainView {
    fun addLembrete()
    fun initSwipeGesture()
    fun showLembretes(): List<Lembrete>
}