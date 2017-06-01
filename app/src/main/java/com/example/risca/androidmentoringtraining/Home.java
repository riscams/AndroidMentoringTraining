package com.example.risca.androidmentoringtraining;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    ListView listmahasiswa;
    FloatingActionButton btn_floating;
    ArrayList<Mahasiswa> mahasiswaArrayList;
    ListMahasiswaAdapter adapter;
    //seharusnya ada floating button, tapi belum dibuat
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_floating = (FloatingActionButton) findViewById(R.id.btn_floating);
        listmahasiswa = (ListView) findViewById(R.id.listmahasiswa);
        mahasiswaArrayList = new ArrayList<>();
        adapter = new ListMahasiswaAdapter(mahasiswaArrayList,this);//context ditulis this aja, karena udah menyatu
        listmahasiswa.setAdapter(adapter);
        addMahasiswa();


        btn_floating.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(Home.this,"",Toast.LENGTH_SHORT);
                Intent intent = new Intent(Home.this,TambahMahasiswa.class);
                startActivity(intent);
            }
        });


        listmahasiswa.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Mahasiswa mahasiswa = mahasiswaArrayList.get(position);
                Toast.makeText(Home.this,mahasiswa.getNama(), Toast.LENGTH_SHORT);
                Intent intent = new Intent(Home.this,DetailMahasiswa.class);
                intent.putExtra("nama",mahasiswa.getNama());
                intent.putExtra("nim",mahasiswa.getNim());
                intent.putExtra("jeniskelamin",mahasiswa.getJenis_kelamin());
                intent.putExtra("notelp",mahasiswa.getNo_telp());
                startActivity(intent);
            }
        });
        adapter.notifyDataSetChanged();
    }
    public void addMahasiswa(){
        mahasiswaArrayList.add(new Mahasiswa("Risca","1301144104","Bandung","Perempuan","082240204878"));
        mahasiswaArrayList.add(new Mahasiswa("Mukti","1301144108","Bandung","Laki-laki","082240204877"));
        mahasiswaArrayList.add(new Mahasiswa("Susanti","1301144109","Bandung","Perempuan","082240204879"));
        mahasiswaArrayList.add(new Mahasiswa("Dia","1301144100","Bandung","Perempuan","082240204877"));
        mahasiswaArrayList.add(new Mahasiswa("Siapa","1301144105","Bandung","Laki-laki","082240204079"));
        mahasiswaArrayList.add(new Mahasiswa("Namanya","1301144107","Bandung","Laki-laki","082240274879"));
    }

}
