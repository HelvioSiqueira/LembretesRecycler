package com.example.lembretesrecycler

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog

//Dialog Fragment que será exibido quando o botão info de menu for clicado
class AboutDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        //Esse listener recebe uma ação de quando um botão do dialog for clicado
        //Nesse caso quando o botão de negativo for clicado será aberto um site
        val listener = DialogInterface.OnClickListener { _, i ->
            if (i == DialogInterface.BUTTON_NEGATIVE){
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://helviosiqueira.github.io/"));
                startActivity(intent)
            }
        }

        //Cria e retorna o dialog, que terá um titulo, texto, botão positivo,
        //e botão negativo
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.about_title)
            .setMessage(R.string.about_message)
            .setPositiveButton(android.R.string.ok, null)
            .setNegativeButton(R.string.about_button_site, listener)
            .create()
    }
}