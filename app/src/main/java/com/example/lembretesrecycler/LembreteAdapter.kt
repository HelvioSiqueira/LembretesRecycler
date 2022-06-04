package com.example.lembretesrecycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.item_lembrete.view.*

//A classe LembreteAdapter necessita de uma subclasse do tipo RecyclerView.Adapter que deve ser do
//tipo VH(ViewHolder)
class LembreteAdapter (private val lembretes: List<Lembrete>) : RecyclerView.Adapter<LembreteAdapter.VH>(){

    //Cria a instancia do ViewHolder baseado no layout item_lembrete
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        //Infla o layout item_lembrete para poder ser usado
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_lembrete, parent, false)

        return VH(v)
    }

    //Preenche-se o ViewHolder com as informações do objeto
    override fun onBindViewHolder(holder: VH, pos: Int){
        val (titulo, texto, prioridade) = lembretes[pos]

        holder.txtTitle.text = titulo
        holder.txtText.text = texto

        //Definindo a cor do ViewHolder
        holder.itemView.setBackgroundColor(setCor(prioridade))
    }

    //Define a quantidade de itens que a lista irá exibir
    override fun getItemCount(): Int = lembretes.size

    //Criado a classe do tipo ViewHolder para ser retornada
    class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtTitle: TextView = itemView.txtTitulo
        val txtText: TextView = itemView.txtTexto
    }

    //Função que retorna a cor que terá o itemView dependendo da "prioridade"
    private fun setCor(prioridade: String): Int {
        return when (prioridade) {
            "Urgente" -> Color.parseColor("#EF5350")
            "Importante" -> Color.parseColor("#42A5F5")
            "Irrelevante" -> Color.parseColor("#66BB6A")
            else -> Color.WHITE

        }
    }
}