package com.example.lembretesrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.lembretesrecycler.fragments.AboutDialogFragment
import com.example.lembretesrecycler.fragments.LembreteFormFragment
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.widget.SearchView
import com.example.lembretesrecycler.fragments.LembreteListFragment

class MainActivity :
    AppCompatActivity(),
    SearchView.OnQueryTextListener,
    MenuItem.OnActionExpandListener {

    private val listFragment: LembreteListFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentList) as LembreteListFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fadAdd.setOnClickListener {
            LembreteFormFragment().open(supportFragmentManager)
        }
    }

    //Função necessaria que infla a barra de menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.lembrete, menu)
        return true
    }

    //Função onde é definida a ação de de cada item do menu
    //a função usa o id do item definido no layout
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_info ->
                AboutDialogFragment().show(supportFragmentManager, "sobre")

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?) = true

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onMenuItemActionExpand(p0: MenuItem?) = true

    override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
        TODO("Not yet implemented")
    }
}