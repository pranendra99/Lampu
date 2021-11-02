package com.evanfauzi.lampu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evanfauzi.lampu.R
import com.evanfauzi.lampu.holder.KetViewHolder
import com.evanfauzi.lampu.holder.MainViewHolder
import com.evanfauzi.lampu.model.ModelLampu
import com.google.firebase.database.DatabaseReference
import java.util.*

class KetAdapter (private val context: Context,
                  private val daftarModelLampu: ArrayList<ModelLampu?>?) : RecyclerView.Adapter<KetViewHolder>() {
    private val listener: FirebaseDataListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.panduan, parent, false)
        return KetViewHolder(view)
    }

    override fun onBindViewHolder(ketholder: KetViewHolder, position: Int) {
        ketholder.ket.text = (position+1).toString() + ". Untuk menyalakan " + daftarModelLampu?.get(position)?.namalampu
    }

    override fun getItemCount(): Int {
        return daftarModelLampu?.size!!
    }

    //interface data listener
    interface FirebaseDataListener {
        fun onDataClick(lampu: ModelLampu?, position: Int)
//        fun onItemClicked(lampu: ModelLampu?, position: Int)
    }

    init {
        listener = context as FirebaseDataListener
    }

}