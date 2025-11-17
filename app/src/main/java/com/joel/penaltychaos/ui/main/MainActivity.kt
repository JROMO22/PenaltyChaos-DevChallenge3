package com.joel.penaltychaos.ui.main

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.joel.penaltychaos.R
import com.joel.penaltychaos.databinding.ActivityMainBinding
import com.joel.penaltychaos.data.Item_cell
import com.joel.penaltychaos.data.btnShoot
import com.joel.penaltychaos.ui.main.Utils


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

        val dataItemCell = MutableList(9) { index -> Item_cell(index,  "0") }
        var dataBtnShoot = btnShoot(binding.btnShot, false)

        // Generar 9 celdas
        dataItemCell.forEach { item ->
            val cell = inflater.inflate(R.layout.item_cell, binding.boardGrid, false) as MaterialButton
            cell.id = View.generateViewId()

            // Listener que actualitza model i vista.
            cell.setOnClickListener {
                // Si el btnShoot està seleccionat i l'item_cell no està utlitzat.
                if ((dataBtnShoot.selected)) {
                    // El color del item anterior retorna al seu estat original.
                    dataBtnShoot.item_selected?.button?.strokeColor = ColorStateList.valueOf(
                        ContextCompat.getColor(this, R.color.black)
                    )

                    cell.strokeColor = ColorStateList.valueOf(
                        ContextCompat.getColor(this, R.color.selectedItem)
                    )

                    // Utils.afegirValor(item)
                    // cell.text = item.value

                    // Donem la celda seleccionada al item_selected.
                    dataBtnShoot.item_selected = item

                }

                }

                // Guarda referéncia del botó en la data class.
                item.button = cell

                // Afegir a la UI la celda.
                binding.boardGrid.addView(cell)
                cells.add(cell)
            }

            binding.btnShot.setOnClickListener {

                dataBtnShoot.selected = !dataBtnShoot.selected

                // Assignem el color segons el valor de la variable
                val colorRes = if (dataBtnShoot.selected) R.color.selectedItem else R.color.white
                binding.btnShot.strokeColor = Utils.donarColor(this, colorRes)

                if (!dataBtnShoot.selected) {
                    // Canvi al contorn del botó deseleccionat.
                    dataBtnShoot.item_selected?.button?.strokeColor = Utils.donarColor(this, R.color.black)

                    // Perd l'item seleccionat.
                    dataBtnShoot.item_selected = null
                }

            }

            binding.btnFinish.setOnClickListener {
                // Canvi al contorn del botó accionador.
                dataBtnShoot.button.strokeColor = Utils.donarColor(this, R.color.white)


                // Canvi al contorn del botó seleccionat.
                dataBtnShoot.item_selected?.button?.strokeColor = Utils.donarColor(this,R.color.black)


                // Sumem 1 al valor de shoot.
                // Reinicia la referéncia ja que no està seleccionat.
                var itemValue = "0"
                dataBtnShoot.item_selected?.let { item ->
                    item.value = (item.value.toInt() + 1).toString()
                    itemValue = item.value
                }
                dataBtnShoot.item_selected?.button?.text = itemValue
                dataBtnShoot.item_selected = null
                dataBtnShoot.selected = false

            }
        }
    }

