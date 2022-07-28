package com.example.lembretesrecycler

import android.content.Context
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.item_cor.view.*

class ColorListAdapter(private val cores: List<Cor>, private var ctx:Context): RecyclerView.Adapter<ColorListAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_cor, parent, false)

        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val (cor, nome) = cores[position]

        holder.cor.corDoQuadrado = getColor(cor)
        holder.nome_cor.text = nome
    }

    override fun getItemCount() = cores.size

    class VH(itemView: View): RecyclerView.ViewHolder(itemView){
        val cor = itemView.cor
        val nome_cor = itemView.nome_cor
    }

    private fun getColor(cor: String) = Color.parseColor(cor)
}