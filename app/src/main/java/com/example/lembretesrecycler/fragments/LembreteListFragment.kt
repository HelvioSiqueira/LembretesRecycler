package com.example.lembretesrecycler.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lembretesrecycler.Lembrete
import com.example.lembretesrecycler.LembreteAdapter
import com.example.lembretesrecycler.R
import com.example.lembretesrecycler.presenters.LembretesPresenter
import com.example.lembretesrecycler.repositorys.MemoryRepository
import com.example.lembretesrecycler.views.MainView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_lembrete.*
import kotlinx.android.synthetic.main.lembrete_list.*

class LembreteListFragment : Fragment(), MainView {
    private val presenter = LembretesPresenter(this, MemoryRepository)

    //Criando a lista de lembrete mutavel
    var lembretes = presenter.showLembretes()

    //Instanciando o LembreteAdapter
    private var adapter = LembreteAdapter(lembretes, this)

    //Variavel que irá salvar o termo da pesquisa
    //Para poder ser usado em um filter no repository.remove()
    //Evitando assim um bug(Quando fazia pesquisa e um lembrete era deletado
    // o lembrete excluido na verdade era o que tinha o indice da posição do lembrete da pesquisa)
    private var term = ""
    private var lastTerm = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lembrete_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.searchLembretes("")

        initSwipeGesture()

    }

    //Inicia o RecycleView como um LinearLayout
    private fun initRecyclerView(adapter: LembreteAdapter) {

        rvLembretes.adapter = adapter

        val layoutManager = LinearLayoutManager(requireContext())

        rvLembretes.layoutManager = layoutManager

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

                presenter.excluirLembrete(position, term)

                adapter.notifyItemRemoved(position)

                //Gambiarra, ainda vou tentar resolver kkkkkkkk
                if(adapter.itemCount < lembretes.size){
                    presenter.searchLembretes(lastTerm)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipe)

        itemTouchHelper.attachToRecyclerView(rvLembretes)
    }

    override fun showLembretes(lembretes: List<Lembrete>) {
        adapter = LembreteAdapter(lembretes, this)
        initRecyclerView(adapter)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun showMessageLembreteDeleted() {
        Snackbar.make(requireView(),
            getString(R.string.message_lembrete_deleted),
            Snackbar.LENGTH_LONG).setAction(R.string.undo){
            presenter.reverterExclusao()
            adapter.notifyDataSetChanged()
            presenter.searchLembretes(term)
        }
            .show()
    }

    fun search(text: String){
        lastTerm = text
        presenter.searchLembretes(text)
        term = text
    }

    fun clearSearch(){
        presenter.searchLembretes("")
    }
}