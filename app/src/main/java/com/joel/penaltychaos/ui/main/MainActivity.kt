package com.joel.penaltychaos.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.joel.penaltychaos.R
import com.joel.penaltychaos.databinding.ActivityMainBinding
import com.joel.penaltychaos.data.Item_cell
import com.joel.penaltychaos.R.layout.item_cell

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cells: MutableList<MaterialButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.game_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val inflater = LayoutInflater.from(this)
        cells = mutableListOf()

        val itemCellData = MutableList(9) { index -> Item_cell(index, false, "0") }

        // Generar 9 celdas
        itemCellData.forEach { item ->
            val cell = inflater.inflate(R.layout.item_cell, binding.boardGrid, false) as MaterialButton
            cell.id = View.generateViewId()

            // Listener que actualiza modelo y vista
            cell.setOnClickListener {
                val numActual = item.value.toIntOrNull() ?: 0
                item.value = (numActual + 1).toString()
                cell.text = item.value
                item.isUsed = true
            }

            // Guardar referencia del botón en la data class
            item.button = cell

            // Añadir a la UI y lista de botones
            binding.boardGrid.addView(cell)
            cells.add(cell)
        }
    }
}
