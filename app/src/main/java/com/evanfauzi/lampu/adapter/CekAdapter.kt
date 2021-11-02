package com.evanfauzi.lampu.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evanfauzi.lampu.R
import com.evanfauzi.lampu.holder.CekViewHolder
import com.evanfauzi.lampu.model.ModelLampu
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class CekAdapter (private val context: Context,
                  private val daftarModelLampu: ArrayList<ModelLampu?>?,
                  private val mDatabaseReference: DatabaseReference) : RecyclerView.Adapter<CekViewHolder>() {
    private val listener: FirebaseDataListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CekViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cek_lampu_view, parent, false)
        return CekViewHolder(view)
    }

    override fun onBindViewHolder(cekholder: CekViewHolder, position: Int) {
        val lmp = daftarModelLampu?.get(position)?.key
        Log.d("TAG", "ob: "+lmp)
        mDatabaseReference.child(lmp!!).addValueEventListener(object:
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lampu: ModelLampu = snapshot.getValue(ModelLampu::class.java)!!
                val status = lampu.nilai
                Log.d("TAG", "onBindViewHolder: "+snapshot.getValue())
                if(status == 1){
                    cekholder.img.setBackgroundResource(R.drawable.gagal)
                    cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dimatikan"
                }else if (status == 0){
                    cekholder.img.setBackgroundResource(R.drawable.sukses)
                    cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dinyalakan"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

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