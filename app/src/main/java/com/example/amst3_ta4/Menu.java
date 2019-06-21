package com.example.amst3_ta4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class Menu extends AppCompatActivity {
    String token = "";

    //commit de prueba
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");
    }

    public void Salir(View v){
        this.finish();
        System.exit(0);
    }

    public void revisarSensores(View v){
        Intent redes_sensores = new Intent(getBaseContext(), Redes_Sensores.class);
        redes_sensores.putExtra("token", token);
        startActivity(redes_sensores);
    }
}