package com.joel.penaltychaos

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.gridlayout.widget.GridLayout
import android.view.View
import com.google.android.material.button.MaterialButton
import com.joel.penaltychaos.databinding.ActivityMainBinding
import java.util.zip.Inflater


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

        // Generar 9 celdas
        for (i in 0 until 9) {
            val cell = inflater.inflate(R.layout.item_cell, binding.boardGrid, false) as MaterialButton
            cell.id = View.generateViewId()
            cell.setOnClickListener {
                if (cell.text.isEmpty()) {
                    cell.text = "❌"
                    cell.isEnabled = false
                }
            }
            binding.boardGrid.addView(cell)
            cells.add(cell)
        }
    }
}

