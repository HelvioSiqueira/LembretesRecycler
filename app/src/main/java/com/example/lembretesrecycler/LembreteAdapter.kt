package com.example.lembretesrecycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_lembrete.view.*

class LembreteAdapter (
    private val lembretes: List<Lembrete>) : RecyclerView.Adapter<LembreteAdapter.VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_lembrete, parent, false)

        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, pos: Int){
        val (titulo, texto, prioridade) = lembretes[pos]

        holder.txtTitle.text = titulo
        holder.txtText.text = texto
        holder.itemView.setBackgroundColor(setCor(prioridade))
    }

    override fun getItemCount(): Int = lembretes.size

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtTitle: TextView = itemView.txtTitulo
        val txtText: TextView = itemView.txtTexto
        val prioridade: Spinner? = itemView.spnPrioridades
    }

    private fun setCor(prioridade: String): Int {
        return when (prioridade) {
            "Urgente" -> Color.parseColor("#EF5350")
            "Importante" -> Color.parseColor("#42A5F5")
            "Irrelevante" -> Color.parseColor("#66BB6A")
            else -> Color.WHITE

        }
    }
}