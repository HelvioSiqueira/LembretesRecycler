package com.example.lembretesrecycler.fragments

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.lembretesrecycler.Lembrete
import com.example.lembretesrecycler.R
import com.example.lembretesrecycler.presenters.LembreteFormPresenter
import com.example.lembretesrecycler.repositorys.MemoryRepository
import com.example.lembretesrecycler.views.LembreteFormView
import kotlinx.android.synthetic.main.fragment_lembrete_form.*

class LembreteFormFragment: DialogFragment(), LembreteFormView {

    private val presenter = LembreteFormPresenter(this, MemoryRepository)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lembrete_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initSpinner()

        //setOnEditorActionListener() pega a entrada que foi digitada no teclado
        //i = interaction
        edtText.setOnEditorActionListener { _, i, _ ->
            hadleKeyboardEvent(i)
        }

        dialog?.setTitle("Testando")
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun errorInvalidLembrete() {
        Toast.makeText(requireContext(), R.string.error_invalid_lembrete, Toast.LENGTH_LONG).show()
    }

    override fun errorSaveLembrete() {
        Toast.makeText(requireContext(), R.string.error_lembrete_add, Toast.LENGTH_LONG).show()
    }

    //hadleKeyboardEvent() checa a entrada que foi passada pra ele e
    //se for um IME_ACTION_DONE instancia a função saveLembrete()(que efetivamente irá salvar o lembrete)
    //dialog?.dismiss() fechar o dialog
    private fun hadleKeyboardEvent(actionId: Int): Boolean{
        if(EditorInfo.IME_ACTION_DONE == actionId){
            val lembrete = saveLembrete()

            if(lembrete != null){
                dialog?.dismiss()
                return true
            }
        }
        return false
    }

    //Função pega os dados digitados adiciona-os no objeto lembrete
    //e chamama o saveLembrete() do presenter
    override fun saveLembrete(): Lembrete?{
        val lembrete = Lembrete()

        lembrete.titulo = edtTitle.text.toString()
        lembrete.texto = edtText.text.toString()
        lembrete.prioridade = spnPrioridades.selectedItem.toString()

        return if(presenter.saveLembrete(lembrete)){
            lembrete
        } else {
            null
        }
    }

    fun open(fm: FragmentManager){
        if(fm.findFragmentByTag(DIALOG_TAG) == null){
            show(fm, DIALOG_TAG)
        }
    }

    //Inicia um spinner com itens a serem selecionados
    private fun initSpinner() {
        val prioridades = arrayOf("Urgente", "Importante", "Flexivel", "Fixo")

        val adapterSpn = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, prioridades)

        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spnPrioridades.adapter = adapterSpn
    }

    companion object{
        private const val DIALOG_TAG = "editDialog"
        private const val EXTRA_HOTEL_ID = "hotel_id"

        fun newInstance(lembreteId: Long) = LembreteFormFragment().apply{
            arguments = Bundle().apply {
                putLong(EXTRA_HOTEL_ID, lembreteId)
            }
        }
    }

}