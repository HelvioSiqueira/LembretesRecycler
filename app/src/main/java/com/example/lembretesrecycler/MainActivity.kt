package com.example.lembretesrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.lembretesrecycler.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSpinner()
        spnPrioridades.setSelection(3)
    }

    private fun initSpinner(){
        val prioridades = arrayOf("Urgente", "Importante", "Irrelevante","Fixo")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, prioridades)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spnPrioridades.adapter = adapter
    }
}