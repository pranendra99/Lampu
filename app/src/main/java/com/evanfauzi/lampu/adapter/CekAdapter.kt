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
                  private val mDatabaseReference: DatabaseReference,
                  private val cek1: DatabaseReference,
                  private val cek2: DatabaseReference,
                  private val cek3: DatabaseReference,
                  private val cek4: DatabaseReference,
                  private val cek5: DatabaseReference,
                  private val cek6: DatabaseReference,
                  private val cek7: DatabaseReference,
                  private val cek8: DatabaseReference,
                  private val cek9: DatabaseReference,
                  private val cek10: DatabaseReference) : RecyclerView.Adapter<CekViewHolder>() {
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

                //CEK 1
                cek1.addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val cek = snapshot.getValue(Int::class.java)!!
                        Log.d("TAG", "onDataChange cek 1: "+ cek)

                        if (position==0 && cek==100){
                            cekholder.img.setBackgroundResource(R.drawable.sukses)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dinyalakan"
                        }else if (position==0 && cek==101){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dinyalakan"
                        }else if (position==0 && cek==111){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dimatikan"
                        }else if (position==0 && cek==110){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dimatikan"
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

                //CEK 2
                cek2.addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val cek = snapshot.getValue(Int::class.java)!!
                        Log.d("TAG", "onDataChange cek 2: "+ cek)

                        if (position==1 && cek==100){
                            cekholder.img.setBackgroundResource(R.drawable.sukses)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dinyalakan"
                        }else if (position==1 && cek==101){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dinyalakan"
                        }else if (position==1 && cek==111){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dimatikan"
                        }else if (position==1 && cek==110){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dimatikan"
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

                //CEK 3
                cek3.addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val cek = snapshot.getValue(Int::class.java)!!
                        Log.d("TAG", "onDataChange cek 3: "+ cek)

                        if (position==2 && cek==100){
                            cekholder.img.setBackgroundResource(R.drawable.sukses)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dinyalakan"
                        }else if (position==2 && cek==101){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dinyalakan"
                        }else if (position==2 && cek==111){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dimatikan"
                        }else if (position==2 && cek==110){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dimatikan"
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

                //CEK 4
                cek4.addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val cek = snapshot.getValue(Int::class.java)!!
                        Log.d("TAG", "onDataChange cek 4: "+ cek)

                        if (position==3 && cek==100){
                            cekholder.img.setBackgroundResource(R.drawable.sukses)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dinyalakan"
                        }else if (position==3 && cek==101){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dinyalakan"
                        }else if (position==3 && cek==111){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dimatikan"
                        }else if (position==3 && cek==110){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dimatikan"
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

                //CEK 5
                cek5.addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val cek = snapshot.getValue(Int::class.java)!!
                        Log.d("TAG", "onDataChange cek 5: "+ cek)

                        if (position==4 && cek==100){
                            cekholder.img.setBackgroundResource(R.drawable.sukses)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dinyalakan"
                        }else if (position==4 && cek==101){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dinyalakan"
                        }else if (position==4 && cek==111){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dimatikan"
                        }else if (position==4 && cek==110){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dimatikan"
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

                //CEK 6
                cek6.addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val cek = snapshot.getValue(Int::class.java)!!
                        Log.d("TAG", "onDataChange cek 6: "+ cek)

                        if (position==5 && cek==100){
                            cekholder.img.setBackgroundResource(R.drawable.sukses)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dinyalakan"
                        }else if (position==5 && cek==101){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dinyalakan"
                        }else if (position==5 && cek==111){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dimatikan"
                        }else if (position==5 && cek==110){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dimatikan"
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

                //CEK 7
                cek7.addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val cek = snapshot.getValue(Int::class.java)!!
                        Log.d("TAG", "onDataChange cek 7: "+ cek)

                        if (position==6 && cek==100){
                            cekholder.img.setBackgroundResource(R.drawable.sukses)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dinyalakan"
                        }else if (position==6 && cek==101){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dinyalakan"
                        }else if (position==6 && cek==111){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dimatikan"
                        }else if (position==6 && cek==110){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dimatikan"
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

                //CEK 8
                cek8.addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val cek = snapshot.getValue(Int::class.java)!!
                        Log.d("TAG", "onDataChange cek 8: "+ cek)

                        if (position==7 && cek==100){
                            cekholder.img.setBackgroundResource(R.drawable.sukses)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dinyalakan"
                        }else if (position==7 && cek==101){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dinyalakan"
                        }else if (position==7 && cek==111){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dimatikan"
                        }else if (position==7 && cek==110){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dimatikan"
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

                //CEK 9
                cek9.addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val cek = snapshot.getValue(Int::class.java)!!
                        Log.d("TAG", "onDataChange cek 9: "+ cek)

                        if (position==8 && cek==100){
                            cekholder.img.setBackgroundResource(R.drawable.sukses)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dinyalakan"
                        }else if (position==8 && cek==101){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dinyalakan"
                        }else if (position==8 && cek==111){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dimatikan"
                        }else if (position==8 && cek==110){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dimatikan"
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

                //CEK 10
                cek10.addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val cek = snapshot.getValue(Int::class.java)!!
                        Log.d("TAG", "onDataChange cek 10: "+ cek)

                        if (position==9 && cek==100){
                            cekholder.img.setBackgroundResource(R.drawable.sukses)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dinyalakan"
                        }else if (position==9 && cek==101){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dinyalakan"
                        }else if (position==9 && cek==111){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dimatikan"
                        }else if (position==9 && cek==110){
                            cekholder.img.setBackgroundResource(R.drawable.gagal)
                            cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " gagal dimatikan"
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

//                if (position<=10 && status==0){
//                    cekholder.img.setBackgroundResource(R.drawable.sukses)
//                    cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dinyalakan"
//                }else if (position<=10 && status==1){
//                    cekholder.img.setBackgroundResource(R.drawable.gagal)
//                    cekholder.cek.text = (position+1).toString() + ". Lampu " + (position+1).toString() + " berhasil dimatikan"
//                }

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