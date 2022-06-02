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

    private var lembretes = mutableListOf<Lembrete>()
    private var adapter = LembreteAdapter(lembretes)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSpinner()
        spnPrioridades.setSelection(3)

        initRecyclerView()

        fabAdd.setOnClickListener{
            if (edtTitle.text!!.isBlank()){
                Toast.makeText(this, "Em branco", Toast.LENGTH_LONG).show()
            } else {
                addLembrete()
            }
        }
    }

    private fun initSpinner(){
        val prioridades = arrayOf("Urgente", "Importante", "Irrelevante","Fixo")

        val adapterSpn = ArrayAdapter(this, android.R.layout.simple_spinner_item, prioridades)

        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spnPrioridades.adapter = adapterSpn
    }

    private fun initRecyclerView(){
        rvLembretes.adapter = adapter

        val layoutManager = LinearLayoutManager(this)

        rvLembretes.layoutManager = layoutManager

        iniSwipeGesture()
    }

    private fun addLembrete(){
        val lembrete = Lembrete(
            edtTitle.text.toString(),
            edtText.text.toString(),
            spnPrioridades.selectedItem.toString()
        )

        lembretes.add(lembrete)
        adapter.notifyItemInserted(lembretes.lastIndex)
        edtTitle.text?.clear()
        edtText.text?.clear()
        edtTitle.requestFocus()

        spnPrioridades.setSelection(3)
    }

    private fun iniSwipeGesture(){
        val swipe = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

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