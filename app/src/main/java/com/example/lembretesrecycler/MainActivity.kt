package com.example.lembretesrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {
    private val presenter = LembretesPresenter(this, MemoryRepository)

    //Criando a lista de lembrete mutavel
    var lembretes = presenter.showLembretes()

    //Instanciando o LembreteAdapter
    private var adapter = LembreteAdapter(lembretes, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
    }


    //Inicia o RecycleView como um LinearLayout
    private fun initRecyclerView() {
        rvLembretes.adapter = adapter

        val layoutManager = LinearLayoutManager(this)

        rvLembretes.layoutManager = layoutManager

        initSwipeGesture()
    }

    //Função que faz com que o lembrete seja excluido ao ser movido pro lado
    override fun initSwipeGesture() {
        //Só poderá ser movido pra esquerda e em 0 posições
        val swipe = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                //Pega as posição atual e posição alvo
                val from: Int = viewHolder.absoluteAdapterPosition
                val to: Int = target.absoluteAdapterPosition

                //Faz a troca de posições na lista lembretes e notifica o adapter da mudança
                presenter.trocarPosicao(from, to)

                adapter.notifyItemMoved(from, to)

                return true
            } //Se fosse false não poderia ser movido

            //Função de ação quando o lembrete for passado
            //Removemos o lembrete e notificamos o adapter
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition

                presenter.excluirLembrete(position)

                adapter.notifyItemRemoved(position)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipe)
        itemTouchHelper.attachToRecyclerView(rvLembretes)
    }

    override fun showLembretes(): List<Lembrete> {
        return presenter.showLembretes()
    }

    //Função necessaria que infla a barra de menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.lembrete, menu)
        return true
    }

    //Função onde é definida a ação de de cada item do menu
    //a função usa o id do item definido no layout
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_info ->
                AboutDialogFragment().show(supportFragmentManager, "sobre")

            R.id.action_new ->
                LembreteFormFragment().open(supportFragmentManager)
        }

        return super.onOptionsItemSelected(item)
    }
}