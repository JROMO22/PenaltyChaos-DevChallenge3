package com.joel.penaltychaos.data
import com.google.android.material.button.MaterialButton

data class Item_cell(
    val index: Int,       // 0..8
    var value: String,    // "❌", "⭕", ""...
    var button: MaterialButton? = null // referencia UI
)


