package com.evanfauzi.lampu.holder

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.ToggleButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.evanfauzi.lampu.R

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @JvmField
    var tombol: ToggleButton

//    @JvmField
//    var tombol: RelativeLayout

    @JvmField
    var no: TextView

    @JvmField
    var view: CardView

    init {
        tombol = itemView.findViewById(R.id.off_button)
        no = itemView.findViewById(R.id.nomer)
        view = itemView.findViewById(R.id.cardView)
    }
}