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
    private val lembretes: List<Lembrete>,
    private val callback: (Lembrete) -> Unit) : RecyclerView.Adapter<LembreteAdapter.VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_lembrete, parent, false)

        val vh = VH(v)
        vh.itemView.setOnClickListener{
            val lembrete = lembretes[vh.absoluteAdapterPosition]
            callback(lembrete)
        }
        return vh
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
            "Urgente" -> Color.RED
            "Importante" -> Color.BLUE
            "Irrilevante" -> Color.GREEN
            else -> Color.WHITE
        }
    }
}