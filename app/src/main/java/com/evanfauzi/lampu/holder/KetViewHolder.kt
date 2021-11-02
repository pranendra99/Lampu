package com.evanfauzi.lampu.holder

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.evanfauzi.lampu.R

class KetViewHolder(ketView: View) : RecyclerView.ViewHolder(ketView) {
    @JvmField
    var ket: TextView

    @JvmField
    var view: CardView

    init {
        ket = ketView.findViewById(R.id.txtKetView)
        view = ketView.findViewById(R.id.ketCardView)
    }
}