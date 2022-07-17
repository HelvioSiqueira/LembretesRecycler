package com.example.lembretesrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.lembretesrecycler.fragments.AboutDialogFragment
import com.example.lembretesrecycler.fragments.LembreteFormFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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