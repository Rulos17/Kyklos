package com.example.kyklos;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText etLugar, etRegion, etCampaña, etOrigen  ;
    View btnSiguiente;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etLugar = findViewById(R.id.et_lugar);
        etRegion = findViewById(R.id.et_region);
        etCampaña = findViewById(R.id.et_campaña);
        etOrigen = findViewById(R.id.et_origen);
        btnSiguiente = findViewById(R.id.btn_Siguiente);

        btnSiguiente.setOnClickListener(view -> {
            Intent intent = new Intent(this, SiguienteActivity.class);

            intent.putExtra("lugar", etLugar.getText().toString());
            intent.putExtra("region", etLugar.getText().toString());
            intent.putExtra("campaña", etCampaña.getText().toString());
            intent.putExtra("origen", etOrigen.getText().toString());
            startActivity(intent);
        });
    }
}
