package com.joel.penaltychaos.data
import com.google.android.material.button.MaterialButton

data class Item_cell(
    val index: Int,       // 0..8
    var value: String,    // "❌", "0", "1"...
    var isUsed: Boolean = false,
    var button: MaterialButton? = null
)


