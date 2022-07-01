package com.example.lembretesrecycler

interface LembretesRepository {
    fun add(lembrete: Lembrete)
    fun remove(position: Int)
    fun move(from: Int, to: Int)
}