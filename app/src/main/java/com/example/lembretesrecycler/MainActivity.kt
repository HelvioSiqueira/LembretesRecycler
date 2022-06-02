package com.example.lembretesrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Criando a lista de lembrete mutavel
    private var lembretes = mutableListOf<Lembrete>()

    //Instanciando o LembreteAdapter
    private var adapter = LembreteAdapter(lembretes)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSpinner()
        spnPrioridades.setSelection(3)

        initRecyclerView()

        fabAdd.setOnClickListener{
            if (edtTitle.text!!.isBlank()){
                Toast.makeText(this, "Título em branco", Toast.LENGTH_LONG).show()
            } else {
                addLembrete()
            }
        }
    }

    //Inicia um spinner com itens a serem selecionados
    private fun initSpinner(){
        val prioridades = arrayOf("Urgente", "Importante", "Irrelevante","Fixo")

        val adapterSpn = ArrayAdapter(this, android.R.layout.simple_spinner_item, prioridades)

        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spnPrioridades.adapter = adapterSpn
    }

    //Inicia o RecycleView como um LinearLayout
    private fun initRecyclerView(){
        rvLembretes.adapter = adapter

        val layoutManager = LinearLayoutManager(this)

        rvLembretes.layoutManager = layoutManager

        iniSwipeGesture()
    }

    //Função que adiciona o lembrete
    private fun addLembrete(){

        //Instancia um objeto Lembrete passando os itens digitados e selecionados
        val lembrete = Lembrete(
            edtTitle.text.toString(),
            edtText.text.toString(),
            spnPrioridades.selectedItem.toString()
        )

        //Adiciona o lembrete na lista de lembretes
        lembretes.add(lembrete)

        //Notifica o adapter que um novo item foi inserido
        adapter.notifyItemInserted(lembretes.lastIndex)

        //Limpa os InputText e deixa o edtTitle selecionado
        edtTitle.text?.clear()
        edtText.text?.clear()
        edtTitle.requestFocus()

        //Seleciona "Fixo" no spinner
        spnPrioridades.setSelection(3)
    }

    //Função que faz com que o lembrete seja excluido ao ser movido pro lado
    private fun iniSwipeGesture(){

        //Só poderá ser movido pra esquerda e em 0 possições
        val swipe = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false //Não poderá ser movido

            //Função de ação quando o lembrete for passado
            //Removemos o lembrete e notificamos o adapter
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                lembretes.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipe)
        itemTouchHelper.attachToRecyclerView(rvLembretes)
    }
}