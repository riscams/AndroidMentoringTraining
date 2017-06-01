package com.example.risca.androidmentoringtraining;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailMahasiswa extends AppCompatActivity {

    ImageView fotoprofil, btn_message, btn_call;
    TextView namadisplay, nimdisplay;
    ArrayList<Mahasiswa> mahasiswaArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mahasiswa);

        btn_message = (ImageView) findViewById(R.id.btn_message);
        btn_call = (ImageView) findViewById(R.id.btn_call);
        fotoprofil = (ImageView) findViewById(R.id.fotoprofil);
        namadisplay = (TextView) findViewById(R.id.namadisplay);
        nimdisplay = (TextView) findViewById(R.id.nimdisplay);

        btn_message.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){

            }
        } );

        btn_call.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick (View v){

            }
        });

        Intent intent = getIntent();
        String nama = intent.getStringExtra("nama");
        String nim = intent.getStringExtra("nim");
        String jeniskelamin = intent.getStringExtra("jeniskelamin");
        String notelp = intent.getStringExtra("notelp");

        namadisplay.setText(nama);
        nimdisplay.setText(nim);

        if (jeniskelamin == "Laki-laki"){
            fotoprofil.setImageResource(R.drawable.man);
        }else {
            fotoprofil.setImageResource(R.drawable.woman);
        }

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+getIntent().getExtras().getString("notelp","082240888")));
                if (ActivityCompat.checkSelfPermission(DetailMahasiswa.this.getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
            }
                startActivity(intent);
        }
    });

        
}
}
