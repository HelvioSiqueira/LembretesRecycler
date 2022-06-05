package com.example.lembretesrecycler

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_lembrete.view.*

//A classe LembreteAdapter necessita de uma subclasse do tipo RecyclerView.Adapter que deve ser do
//tipo VH(ViewHolder)
class LembreteAdapter (private val lembretes: List<Lembrete>, private val ctx:Context) : RecyclerView.Adapter<LembreteAdapter.VH>(){

    private val icones: TypedArray by lazy{
        ctx.resources.obtainTypedArray(R.array.icones)
    }

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

        val ic = when(prioridade){
            "Urgente" -> 0
            "Importante" -> 1
            "Flexivel" -> 2
            else -> 3
        }

        //Definindo a cor do ViewHolder
        holder.itemView.setBackgroundColor(setCor(prioridade))
        holder.icone.setImageDrawable(icones.getDrawable(ic))
    }

    //Define a quantidade de itens que a lista irá exibir
    override fun getItemCount(): Int = lembretes.size

    //Criado a classe do tipo ViewHolder para ser retornada
    class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtTitle: TextView = itemView.txtTitulo
        val txtText: TextView = itemView.txtTexto
        val icone: ImageView = itemView.icone
    }

    //Função que retorna a cor que terá o itemView dependendo da "prioridade"
    private fun setCor(prioridade: String): Int {
        return when (prioridade) {
            "Urgente" -> Color.parseColor("#EF5350")
            "Importante" -> Color.parseColor("#42A5F5")
            "Flexivel" -> Color.parseColor("#66BB6A")
            else -> Color.WHITE

        }
    }

    private fun setIcon(prioridade: String): Drawable {
        return when (prioridade) {
            "Urgente" -> R.drawable.ic_atencao.toDrawable()
            "Importante" -> R.drawable.ic_update.toDrawable()
            "Flexivel" -> R.drawable.ic_snooze.toDrawable()
            else -> R.drawable.ic_pin.toDrawable()

        }
    }
}