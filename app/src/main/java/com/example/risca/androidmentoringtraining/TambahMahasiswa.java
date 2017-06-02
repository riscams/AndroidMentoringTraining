package com.example.risca.androidmentoringtraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
        namalengkap = (EditText) findViewById(R.id.namalengkap);
        nim = (EditText) findViewById(R.id.nim);
        alamat = (EditText) findViewById(R.id.alamat);
        telp = (EditText) findViewById(R.id.telp);
        btn_laki = (RadioButton) findViewById(R.id.btn_laki);
        btn_perempuan = (RadioButton) findViewById(R.id.btn_perempuan);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nim.getText().toString().length() < 10) {
                    Toast.makeText(TambahMahasiswa.this, "NIM minimal 10 digit", Toast.LENGTH_SHORT).show();
                } else {
                    getAllMahasiswa();
                }

            }
        });
    }

    public void getAllMahasiswa() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://api-android.herokuapp.com/mahasiswa",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //kalau berhasil, isi response adalah list mahasiswa
                        Toast.makeText(TambahMahasiswa.this, response, Toast.LENGTH_SHORT).show();
                        try {
                            //yang terluar itu json objek
                            JSONObject result = new JSONObject(response); //respon diubah jadi result
                            if (result.optString("status", "null").equals("success")) {
                                Toast.makeText(TambahMahasiswa.this, "Berhasil!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(TambahMahasiswa.this, Home.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(TambahMahasiswa.this, "Maaf, belum beruntung", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(TambahMahasiswa.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TambahMahasiswa.this, "Gagal", Toast.LENGTH_SHORT).show();
                    }
                }) {
            //code untuk kasus POST (perlu inputan). Berfungsi hanya jika method GET diinisialisasi objek diganti jadi POST
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //tempat menerima inputan
                params.put("nama", namalengkap.getText().toString());//ambil dari tempat isi nama
                params.put("nim", nim.getText().toString());
                if (btn_laki.isChecked()) {
                    params.put("jenisKelamin", btn_laki.getText().toString());
                } else {
                    params.put("jenisKelamin", btn_perempuan.getText().toString());
                }

                params.put("alamat", alamat.getText().toString());
                params.put("noTelpon", telp.getText().toString());

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
