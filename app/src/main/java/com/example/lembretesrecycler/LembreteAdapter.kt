package com.example.lembretesrecycler

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.res.TypedArray
import android.os.Build
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lembretesrecycler.fragments.LembreteListFragment
import kotlinx.android.synthetic.main.item_lembrete.view.*

//A classe LembreteAdapter necessita de uma subclasse do tipo RecyclerView.Adapter que deve ser do
//tipo VH(ViewHolder)

//Uso o ctx para poder ter acesso aos arquivo de layout array
class LembreteAdapter(
    private val lembretes: List<Lembrete>,
    private val ctx: LembreteListFragment
) : RecyclerView.Adapter<LembreteAdapter.VH>() {

    //Objeto TypedArray que só será iniciado quando for usado a primeira vez
    private val icones: TypedArray by lazy {
        ctx.resources.obtainTypedArray(R.array.icones)
    }

    //Cria a instancia do ViewHolder baseado no layout item_lembrete
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        //Infla o layout item_lembrete para poder ser usado
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_lembrete, parent, false)

        val vh = VH(v)

        vh.itemView.btnSeta.setOnClickListener {
            if (vh.itemView.card_oculto.visibility == View.VISIBLE) {

                vh.itemView.btnSeta.setImageResource(R.drawable.ic_baseline_expand_more_24)

                TransitionManager.beginDelayedTransition(vh.itemView.card, AutoTransition())
                vh.itemView.card_oculto.visibility = View.GONE

                vh.txtText.maxLines = 1
            } else {
                vh.itemView.btnSeta.setImageResource(R.drawable.ic_baseline_expand_less_24)

                TransitionManager.beginDelayedTransition(vh.itemView.card, AutoTransition())
                vh.itemView.card_oculto.visibility = View.VISIBLE

                vh.txtText.maxLines = 5
            }
        }

        return VH(v)
    }

    //Preenche-se o ViewHolder com as informações do objeto
    override fun onBindViewHolder(holder: VH, pos: Int) {
        val (titulo, texto, prioridade, data) = lembretes[pos]

        holder.txtTitle.text = titulo
        holder.txtText.text = texto
        holder.data.text = data

        //Definindo a cor do ViewHolder
        //Selecionando a cor do background dessa forma fazia com que não houvesse
        //O arredondamento da view, pois cor seria aplicada a toda a view no layout
        //holder.itemView.setBackgroundColor(setCor(prioridade))

        //Aplicando a cor no proprio card o arredondamento transparece
        holder.itemView.card.setBackgroundColor(setCor(prioridade))
        holder.icone.setImageDrawable(icones.getDrawable(numPrioridade(prioridade)))
    }

    //Define a quantidade de itens que a lista irá exibir
    override fun getItemCount(): Int = lembretes.size

    //Criado a classe do tipo ViewHolder para ser retornada
    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.txtTitulo
        val txtText: TextView = itemView.txtTexto
        val icone: ImageView = itemView.icone
        val data: TextView = itemView.dataAdd
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

    //Transforma a prioridade em um inteiro para usa-lo no obtainTypedArray
    private fun numPrioridade(prioridade: String): Int {
        val ic = when (prioridade) {
            "Urgente" -> 0
            "Importante" -> 1
            "Flexivel" -> 2
            else -> 3
        }
        return ic
    }

}