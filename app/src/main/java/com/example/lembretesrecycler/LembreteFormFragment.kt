package com.example.lembretesrecycler

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
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

    private fun hadleKeyboardEvent(actionId: Int): Boolean{
        if(EditorInfo.IME_ACTION_DONE == actionId){
            val lembrete = saveLembrete()

            if(lembrete != null){
                if(activity is OnLembreteSavedListener){
                    val listener = activity as OnLembreteSavedListener
                    listener.onLembreteSaved(lembrete)
                }

                dialog?.dismiss()
                return true
            }
        }
        return false
    }

    private fun saveLembrete(): Lembrete?{
        val lembrete = Lembrete()

        lembrete.titulo = edtText.text.toString()
        lembrete.texto = edtText.text.toString()
        lembrete.prioridade = spnPrioridades.selectedItem.toString()

        if(presenter.saveLembrete(lembrete)){
            return lembrete
        } else {
            return null
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

    interface OnLembreteSavedListener{
        fun onLembreteSaved(lembrete: Lembrete)
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