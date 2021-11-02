package com.evanfauzi.lampu.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.evanfauzi.lampu.R

class CekViewHolder (cekView: View) : RecyclerView.ViewHolder(cekView) {
    @JvmField
    var cek: TextView

    @JvmField
    var img: ImageView

    @JvmField
    var view: CardView

    init {
        cek = cekView.findViewById(R.id.txtCekInfo)
        img = cekView.findViewById(R.id.imgCekInfo)
        view = cekView.findViewById(R.id.cekLampuCardView)
    }
}