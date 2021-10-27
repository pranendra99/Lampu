package com.evanfauzi.lampu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.evanfauzi.lampu.R
import com.evanfauzi.lampu.holder.MainViewHolder
import com.evanfauzi.lampu.model.ModelLampu
import java.util.*

class MainAdapter(private val context: Context,
                  private val daftarModelLampu: ArrayList<ModelLampu?>?) : RecyclerView.Adapter<MainViewHolder>() {
    private val listener: FirebaseDataListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
//        holder.view.setBackgroundColor(#f0f4f4)
        holder.tombol.text = ""
        holder.no.text = "" + daftarModelLampu?.get(position)?.no
        holder.tombol.setOnClickListener { listener.onDataClick(daftarModelLampu?.get(position), position) }
        holder.tombol.setOnClickListener { listener.onItemClicked(daftarModelLampu?.get(position), position)}
        holder.view.setOnClickListener { listener.onDataClick(daftarModelLampu?.get(position), position) }
    }

    override fun getItemCount(): Int {
        return daftarModelLampu?.size!!
    }

    //interface data listener
    interface FirebaseDataListener {
        fun onDataClick(lampu: ModelLampu?, position: Int)
        fun onItemClicked(lampu: ModelLampu?, position: Int)
    }

    init {
        listener = context as FirebaseDataListener

    }
}