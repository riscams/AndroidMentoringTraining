package com.example.risca.androidmentoringtraining;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by RISCA on 6/1/2017.
 */

public class ListMahasiswaAdapter extends BaseAdapter {

    //data apa (mahasiswaArrayList) yg mau dilempar kemana (context)
    ArrayList<Mahasiswa> mahasiswaArrayList;
    Context context; //nempel di activity

    public ListMahasiswaAdapter(ArrayList<Mahasiswa> mahasiswaArrayList, Context context) {
        this.mahasiswaArrayList = mahasiswaArrayList;
        this.context = context;
    }

    //adapter butuh tau ada berapa list yang mau dibuat
    @Override
    public int getCount() {
        return mahasiswaArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mahasiswaArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_mahasiswa,parent,false);
        }

        Mahasiswa mahasiswa = mahasiswaArrayList.get(position);
        ImageView gambar = (ImageView) convertView.findViewById(R.id.img_gambar);
        TextView nama = (TextView) convertView.findViewById(R.id.textView2);
        TextView nim = (TextView) convertView.findViewById(R.id.textView9);

        nama.setText(mahasiswa.getNama());
        nim.setText(mahasiswa.getNim());

        if (mahasiswa.getJenis_kelamin().equals("Laki-laki")){
            gambar.setImageResource(R.drawable.man);
        }else {
            gambar.setImageResource(R.drawable.woman);
        }
        return convertView;
    }
}
