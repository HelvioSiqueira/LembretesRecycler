package com.example.lembretesrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
        val (titulo, texto) = lembretes[pos]
        holder.txtTitle.text = titulo
        holder.txtText.text = texto
    }

    override fun getItemCount(): Int = lembretes.size

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtTitle: TextView = itemView.txtTitulo
        val txtText: TextView = itemView.txtTexto
    }
}