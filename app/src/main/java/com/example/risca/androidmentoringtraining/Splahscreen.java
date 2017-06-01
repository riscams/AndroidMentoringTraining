package com.example.risca.androidmentoringtraining;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class Splahscreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000; //satuannya micro-second
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splahscreen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splahscreen.this,Home.class); //dari mana ke mana
                Splahscreen.this.startActivity(mainIntent);
                Splahscreen.this.finish();//biar waktu di back, nggak balik lagi ke splash screen
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
