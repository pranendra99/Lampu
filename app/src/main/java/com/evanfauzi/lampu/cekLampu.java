package com.evanfauzi.lampu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.Toast;


import com.evanfauzi.lampu.adapter.CekAdapter;
import com.evanfauzi.lampu.model.ModelLampu;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import me.bastanfar.semicirclearcprogressbar.*;

import static com.evanfauzi.lampu.MainActivity.setWindowFlag;

//import me.bastanfar.semicirclearcprogressbar.SemiCircleArcProgressBar;

public class cekLampu extends AppCompatActivity implements CekAdapter.FirebaseDataListener {
    SemiCircleArcProgressBar arus;
    SemiCircleArcProgressBar tegangan;
    SemiCircleArcProgressBar daya;
    TextView txtArus;
    TextView txtTegangan;
    TextView txtDaya;
//    TextView txtInfo1;
//    TextView txtInfo2;
//    TextView txtInfo3;
//    TextView txtInfo4;
//    ImageView imgInfo1;
//    ImageView imgInfo2;
//    ImageView imgInfo3;
//    ImageView imgInfo4;
    Intent back;
    private RecyclerView cekRecyclerView;
    private CekAdapter cekAdapter;
    private ArrayList<ModelLampu> daftarLampu;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference cek1;
    private DatabaseReference cek2;
    private DatabaseReference cek3;
    private DatabaseReference cek4;
    private DatabaseReference cek5;
    private DatabaseReference cek6;
    private DatabaseReference cek7;
    private DatabaseReference cek8;
    private DatabaseReference cek9;
    private DatabaseReference cek10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_lampu);

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

        arus = findViewById(R.id.progressBar_Arus);
        tegangan = findViewById(R.id.progressBar_Tegangan);
        daya = findViewById(R.id.progressBar_Daya);
        txtArus = findViewById(R.id.textArus);
        txtTegangan = findViewById(R.id.textTegangan);
        txtDaya = findViewById(R.id.textDaya);
//        txtInfo1 = findViewById(R.id.txtInfo1);
//        txtInfo2 = findViewById(R.id.txtInfo2);
//        txtInfo3 = findViewById(R.id.txtInfo3);
//        txtInfo4 = findViewById(R.id.txtInfo4);
//        imgInfo1 = findViewById(R.id.imgInfo1);
//        imgInfo2 = findViewById(R.id.imgInfo2);
//        imgInfo3 = findViewById(R.id.imgInfo3);
//        imgInfo4 = findViewById(R.id.imgInfo4);

        cekRecyclerView = findViewById(R.id.recyclerCekView);
        cekRecyclerView.setHasFixedSize(true);
        cekRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDatabase fireBase = FirebaseDatabase.getInstance();
        DatabaseReference arusRef = fireBase.getReference("arus");
        DatabaseReference teganganRef = fireBase.getReference("tegangan");
        DatabaseReference dayaRef = fireBase.getReference("daya");
//        DatabaseReference cekRef1 = fireBase.getReference("cek1");
//        DatabaseReference cekRef2 = fireBase.getReference("cek2");
//        DatabaseReference cekRef3 = fireBase.getReference("cek3");
//        DatabaseReference cekRef4 = fireBase.getReference("cek4");
        cek1 = fireBase.getReference("cek1");
        cek2 = fireBase.getReference("cek2");
        cek3 = fireBase.getReference("cek3");
        cek4 = fireBase.getReference("cek4");
        cek5 = fireBase.getReference("cek5");
        cek6 = fireBase.getReference("cek6");
        cek7 = fireBase.getReference("cek7");
        cek8 = fireBase.getReference("cek8");
        cek9 = fireBase.getReference("cek9");
        cek10 = fireBase.getReference("cek10");
        mDatabaseReference = fireBase.getReference("data_lampu").child("lampu");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                daftarLampu = new ArrayList<>();
                for (DataSnapshot mDataSnapshot : dataSnapshot.getChildren()){
                    ModelLampu lampu = mDataSnapshot.getValue(ModelLampu.class);
                    lampu.setKey(mDataSnapshot.getKey());
                    daftarLampu.add(lampu);
                }
                cekAdapter = new CekAdapter(cekLampu.this, daftarLampu, mDatabaseReference, cek1, cek2, cek3, cek4, cek5, cek6, cek7, cek8, cek9, cek10);
                cekRecyclerView.setAdapter(cekAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(cekLampu.this,
                        error.getDetails() + " " + error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        arusRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                int bacaArus = dataSnapshot.getValue(int.class);
                arus.setPercent(bacaArus);
                txtArus.setText(bacaArus + " A");
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {

            }
        });

        teganganRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                int bacaTegangan = dataSnapshot.getValue(int.class);
                tegangan.setPercent(bacaTegangan);
                txtTegangan.setText(bacaTegangan + " V");
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {

            }
        });

        dayaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                int bacaDaya = dataSnapshot.getValue(int.class);
                daya.setPercent(bacaDaya);
                txtDaya.setText(bacaDaya + " W");
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {

            }
        });

//        cekRef1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
//                int cek1 = dataSnapshot.getValue(int.class);
//                if(cek1==100){
//                    imgInfo1.setBackgroundResource(R.drawable.sukses);
//                    txtInfo1.setText("1. Lampu 1 berhasil dinyalakan");
//                }else if(cek1==101){
//                    imgInfo1.setBackgroundResource(R.drawable.gagal);
//                    txtInfo1.setText("1. Lampu 1 gagal dinyalakan, silahkan cek lampunya.");
//                }else if(cek1==111){
//                    imgInfo1.setBackgroundResource(R.drawable.gagal);
//                    txtInfo1.setText("1. Lampu 1 berhasil dimatikan");
//                }else if(cek1==110){
//                    imgInfo1.setBackgroundResource(R.drawable.gagal);
//                    txtInfo1.setText("1. Lampu 1 gagal dimatikan, silahkan cek kabel lampunya");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {
//
//            }
//        });
//
//        cekRef2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
//                int cek2 = dataSnapshot.getValue(int.class);
//                if(cek2==100){
//                    imgInfo2.setBackgroundResource(R.drawable.sukses);
//                    txtInfo2.setText("2. Lampu 2 berhasil dinyalakan");
//                }else if(cek2==101){
//                    imgInfo2.setBackgroundResource(R.drawable.gagal);
//                    txtInfo2.setText("2. Lampu 2 gagal dinyalakan, silahkan cek lampunya.");
//                }else if(cek2==111){
//                    imgInfo2.setBackgroundResource(R.drawable.gagal);
//                    txtInfo2.setText("2. Lampu 2 berhasil dimatikan");
//                }else if(cek2==110){
//                    imgInfo2.setBackgroundResource(R.drawable.gagal);
//                    txtInfo2.setText("2. Lampu 2 gagal dimatikan, silahkan cek kabel lampunya");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {
//
//            }
//        });
//
//        cekRef3.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
//                int cek3 = dataSnapshot.getValue(int.class);
//                if(cek3==100){
//                    imgInfo3.setBackgroundResource(R.drawable.sukses);
//                    txtInfo3.setText("3. Lampu 3 berhasil dinyalakan");
//                }else if(cek3==101){
//                    imgInfo3.setBackgroundResource(R.drawable.gagal);
//                    txtInfo3.setText("3. Lampu 3 gagal dinyalakan, silahkan cek lampunya.");
//                }else if(cek3==111){
//                    imgInfo3.setBackgroundResource(R.drawable.gagal);
//                    txtInfo3.setText("3. Lampu 3 berhasil dimatikan");
//                }else if(cek3==110){
//                    imgInfo3.setBackgroundResource(R.drawable.gagal);
//                    txtInfo3.setText("3. Lampu 3 gagal dimatikan, silahkan cek kabel lampunya");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {
//
//            }
//        });
//
//        cekRef4.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
//                int cek4 = dataSnapshot.getValue(int.class);
//                if(cek4==100){
//                    imgInfo4.setBackgroundResource(R.drawable.sukses);
//                    txtInfo4.setText("4. Lampu 4 berhasil dinyalakan");
//                }else if(cek4==101){
//                    imgInfo4.setBackgroundResource(R.drawable.gagal);
//                    txtInfo4.setText("4. Lampu 4 gagal dinyalakan, silahkan cek lampunya.");
//                }else if(cek4==111){
//                    imgInfo4.setBackgroundResource(R.drawable.gagal);
//                    txtInfo4.setText("4. Lampu 4 berhasil dimatikan");
//                }else if(cek4==110){
//                    imgInfo4.setBackgroundResource(R.drawable.gagal);
//                    txtInfo4.setText("4. Lampu 4 gagal dimatikan, silahkan cek kabel lampunya");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {
//
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back = new Intent(cekLampu.this,MainActivity.class);
        startActivity(back);
        finish();
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

    @Override
    public void onDataClick(final ModelLampu lampu, int position) {

    }
}