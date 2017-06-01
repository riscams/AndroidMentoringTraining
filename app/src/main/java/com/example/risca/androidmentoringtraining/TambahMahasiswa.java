package com.example.risca.androidmentoringtraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class TambahMahasiswa extends AppCompatActivity {

    EditText namalengkap, nim, alamat, telp;
    RadioButton btn_perempuan, btn_laki;
    Button btn_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_mahasiswa);

        //bagaimana agar button dapat diklik
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TambahMahasiswa.this, "Tersimpan", Toast.LENGTH_SHORT).show();
        }
    });
    }
}
