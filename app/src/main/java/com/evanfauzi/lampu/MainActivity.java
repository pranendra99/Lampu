package com.evanfauzi.lampu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.evanfauzi.lampu.adapter.KetAdapter;
import com.evanfauzi.lampu.adapter.MainAdapter;
import com.evanfauzi.lampu.holder.MainViewHolder;
import com.evanfauzi.lampu.model.ModelLampu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements MainAdapter.FirebaseDataListener, KetAdapter.FirebaseDataListener {
    private RecyclerView mRecyclerView;
    private RecyclerView ketRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MainAdapter mAdapter;
    private KetAdapter ketAdapter;
    private ArrayList<ModelLampu> daftarLampu;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;
    ToggleButton btn;
    private ModelLampu mlampu;
    private MainViewHolder holder;
    // ambil tanggal
    SimpleDateFormat tanggalNow = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date mDate = new Date();

    ImageButton cekLampu;
    Intent cekLampuIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_view, null);
        holder = new MainViewHolder(view);

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView()
                        .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        cekLampu = findViewById(R.id.cek_lampu);
        cekLampu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekLampuIntent = new Intent(MainActivity.this, cekLampu.class);
                startActivity(cekLampuIntent);
                finish();
            }
        });

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(layoutManager);

        ketRecyclerView = findViewById(R.id.recyclerKetView);
        ketRecyclerView.setHasFixedSize(true);
        ketRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ToggleButton btn = findViewById(R.id.off_button);

        FirebaseApp.initializeApp(this);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseInstance.getReference("data_lampu");
        mDatabaseReference.child("lampu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                daftarLampu = new ArrayList<>();
                for (DataSnapshot mDataSnapshot : dataSnapshot.getChildren()) {
                    ModelLampu lampu = mDataSnapshot.getValue(ModelLampu.class);

                    lampu.setKey(mDataSnapshot.getKey());
                    daftarLampu.add(lampu);
//                    if (lampu.getNilai()=="0"){
//                        btn.setChecked(true);
//                        btn.setBackgroundResource(R.drawable.on1);
//                    }else if (lampu.getNilai()=="1"){
//                        btn.setChecked(false);
//                        btn.setBackgroundResource(R.drawable.off1);
//                    }
                }

                mAdapter = new MainAdapter(MainActivity.this, daftarLampu,mDatabaseReference);
                mRecyclerView.setAdapter(mAdapter);

                ketAdapter = new KetAdapter(MainActivity.this, daftarLampu);
                ketRecyclerView.setAdapter(ketAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(MainActivity.this,
                        databaseError.getDetails() + " " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

//    @Override
//    public void onDataClick(final ModelLampu lampu, int position) {
//
//        btn = findViewById(R.id.off_button);
////        holder.tombol = findViewById(position);
//        Log.d("onDataClick", "Ini posisi = " + position);
//        Log.d("lampuGet", "get lampu Key = " + lampu.getKey());
//        Log.d("lampuGet", "get lampu Nilai = " + lampu.getNilai());
//        Log.d("lampuGet", "get lampu No = " + lampu.getNamalampu());
//        Log.d("lampuGet", "get lampu Kondisi = " + lampu.getKondisi());
//        Log.d("lampuBTN", "button = " + btn);
//
//        Log.d("lampuHolder","holder = " + holder.tombol);
//
//    }

    @Override
    public void onItemClicked(final ModelLampu lampu, int position) {
        boolean cek = holder.tombol.isChecked();

        if(cek){
            lampu.setNilai(1);
            lampu.setKondisi("Mati");
            lampu.setTanggal(tanggalNow.format(mDate));
        }else{
            lampu.setNilai(0);
            lampu.setKondisi("Nyala");
            lampu.setTanggal(tanggalNow.format(mDate));
        }

        Log.d("TAG", "onItemClicked: "+lampu.getKey());
        Log.d("TAG", "onItemClicked: "+lampu.getNilai());
        Log.d("TAG", "onItemClicked: "+lampu.getKondisi());
        Log.d("TAG", "onItemClicked: "+lampu.getTanggal());

        mDatabaseReference.child("lampu").child(lampu.getKey())
                .setValue(lampu).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void mVoid) {
                if(cek){
                    holder.tombol.setChecked(false);
                    Toast.makeText(MainActivity.this, "Lampu " + (position+1) + " berhasil di matikan !", Toast.LENGTH_LONG).show();
                }else{
                    holder.tombol.setChecked(true);
                    Toast.makeText(MainActivity.this, "Lampu " + (position+1) + " berhasil di nyalakan !", Toast.LENGTH_LONG).show();
                }

            }
        }).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if(cek){
                    holder.tombol.setChecked(false);
                }else{
                    holder.tombol.setChecked(true);
                }
            }
        });
    }

    @Override
    public void onDataClick(final ModelLampu lampu, int position) {

    }
}