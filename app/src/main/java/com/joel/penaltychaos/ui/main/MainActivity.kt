package com.joel.penaltychaos.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.*
import com.joel.penaltychaos.R
import com.joel.penaltychaos.data.Item_cell
import com.joel.penaltychaos.data.btnShoot
import com.joel.penaltychaos.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cells: MutableList<MaterialButton>
    var baseDeDades: DatabaseReference = FirebaseDatabase.getInstance().reference

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
        var dataBtnShoot = btnShoot(binding.btnAction, false)

        // Generar 9 celdas
        dataItemCell.forEach { item ->
            val cell = inflater.inflate(R.layout.item_cell, binding.boardGrid, false) as MaterialButton
            cell.id = View.generateViewId()

            // Guarda referéncia del botó en la data class.
            item.button = cell

            // Listener que actualitza model i vista.
            cell.setOnClickListener {
                // Si el item està en ús no fem cap canvi.
                if (item.isUsed) {
                    return@setOnClickListener

                } else if (dataBtnShoot.selected) {
                    item.button?.strokeColor = Utils.donarColor(this, R.color.selectedItem)
                    dataBtnShoot.item_selected?.let { itemSeleccionat ->

                        // El color de l'item anterior retorna al seu estat original.
                        itemSeleccionat.button?.let { itemButton ->
                            itemButton.strokeColor = Utils.donarColor(this, R.color.black)

                        }
                    }
                }

                // Donem la celda seleccionada al item_selected.
                dataBtnShoot.item_selected = item

                }

                // Afegir a la UI la celda.
                binding.boardGrid.addView(cell)
                cells.add(cell)
            }



            // Listener del botó d'acció.
            binding.btnAction.setOnClickListener {

                dataBtnShoot.selected = !dataBtnShoot.selected

                // Assignem el color segons el valor de la variable
                val colorRes = if (dataBtnShoot.selected) R.color.selectedItem else R.color.white
                binding.btnAction.strokeColor = Utils.donarColor(this, colorRes)

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

                // Canvi al text del button seleccionat.
                dataBtnShoot.item_selected?.button?.text = itemValue
                dataBtnShoot.item_selected?.isUsed = true
                dataBtnShoot.item_selected = null
                dataBtnShoot.selected = false

            }
        }
    }

