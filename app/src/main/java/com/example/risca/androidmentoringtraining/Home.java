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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        getAllMahasiswa();


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
//        mahasiswaArrayList.add(new Mahasiswa("Risca","1301144104","Bandung","P","082240204878"));
//        mahasiswaArrayList.add(new Mahasiswa("Mukti","1301144108","Bandung","L","082240204877"));
//        mahasiswaArrayList.add(new Mahasiswa("Susanti","1301144109","Bandung","P","082240204879"));
//        mahasiswaArrayList.add(new Mahasiswa("Dia","1301144100","Bandung","P","082240204877"));
//        mahasiswaArrayList.add(new Mahasiswa("Siapa","1301144105","Bandung","L","082240204079"));
//        mahasiswaArrayList.add(new Mahasiswa("Namanya","1301144107","Bandung","L","082240274879"));
    }

    public void getAllMahasiswa(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://api-android.herokuapp.com/mahasiswa",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //kalau berhasil, isi response adalah list mahasiswa
                        //Toast.makeText(Home.this,response,Toast.LENGTH_SHORT).show();
                        try {
                            //yang terluar itu json objek
                            JSONObject result = new JSONObject(response); //respon diubah jadi result
                            if (result.optString("status","null").equals("success")){
                                JSONArray data = result.getJSONArray("data");//array di json data, ditaruh di data
                                for (int i = 0; i < data.length() ; i++) {
                                    JSONObject object = data.getJSONObject(i);
//                                    Toast.makeText(Home.this, object.optString("jenisKelamin","null"), Toast.LENGTH_SHORT).show();
                                    Mahasiswa mahasiswa = new Mahasiswa(object.optString("nama","null"),object.optString("nim","null"),object.optString("jenisKelamin","null"),object.optString("alamat","null"),object.optString("noTelpon","null"));
                                    mahasiswaArrayList.add(mahasiswa);
                                }
                                adapter.notifyDataSetChanged();
                            }else {
                                Toast.makeText(Home.this, "Status Gagal", Toast.LENGTH_SHORT).show();
                            }


                        }catch (Exception e){
                            Toast.makeText(Home.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Home.this,"Gagal",Toast.LENGTH_SHORT).show();
                    }
                }){
            //code untuk kasus POST (perlu inputan). Berfungsi hanya jika method GET diinisialisasi objek diganti jadi POST
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //tempat menerima inputan
                params.put("username","riscams");
                params.put("password","abcdefg");
                return params;
            }
        }

                ;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
