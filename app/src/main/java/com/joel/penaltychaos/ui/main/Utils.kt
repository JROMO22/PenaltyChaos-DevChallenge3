package com.joel.penaltychaos.ui.main

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.joel.penaltychaos.data.Item_cell

class Utils {

    companion object {
        fun donarColor(context: Context, @ColorRes colorRes: Int): ColorStateList {
            return ColorStateList.valueOf(ContextCompat.getColor(context, colorRes))
        }

        fun get_isUsed(cell: MaterialButton) {

        }
    }
}