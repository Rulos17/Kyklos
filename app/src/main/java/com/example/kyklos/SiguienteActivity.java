package com.example.kyklos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SiguienteActivity extends AppCompatActivity {
    EditText etCelularesKg, etCelularesCantidad, etTabletsKg, etTablesCantidad, etCpusKg, etCpusCantidad, etNotebooksCantidad, etNotebooksKg, etOtrosKg;
    View btnEnviar;

    String Fecha, Lugar, Region,Campaña, Origen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_siguiente);

        Lugar = intent.getStringExtra("lugar");
        Region = intent.getStringExtra("region");
        Campaña = intent.getStringExtra("campaña");
        Origen = intent.getStringExtra("Origen");
        etCelularesCantidad = findViewById(R.id.et_celularesCantidad);
        etCelularesKg = findViewById(R.id.et_celularesKg);
        etTablesCantidad = findViewById(R.id.et_tabletsCantidad);
        etTabletsKg = findViewById(R.id.et_tabletsKg);
        etCpusCantidad = findViewById(R.id.et_cpuCantidad);
        etCpusKg = findViewById(R.id.et_cpusKg);
        etNotebooksCantidad = findViewById(R.id.et_notebooksCantidad);
        etNotebooksKg = findViewById(R.id.et_notebooksKg);
        etOtrosKg = findViewById(R.id.et_otrosKg);
        btnEnviar = findViewById(R.id.btn_enviar);
        btnEnviar.setOnClickListener(v -> enviarDatos());

    }

    private void enviarDatos() {
        ProgressDialog progressDialog = ProgressDialog.show(this,
                "Agregando datos",
                "Espere por favor",
                true,
                false);



        String CelularesCantidad = etCelularesCantidad.getText().toString();
        String CelularesKg = etCelularesKg.getText().toString();
        String TabletsCantidad = etTablesCantidad.getText().toString();
        String TabletsKg = etTabletsKg.getText().toString();
        String CpusCantidad = etCpusCantidad.getText().toString();
        String CpusKg = etCpusKg.getText().toString();
        String NotebooksCantidad = etNotebooksCantidad.getText().toString();
        String NotebooksKg = etNotebooksKg.getText().toString();
        String OtrosKg = etOtrosKg.getText().toString();

        AsyncTask.execute(()->{
            try{
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://script.google.com/macros/s/AKfycbxrfKBJjDUM2MNCQ31r6vvG4GJltzdAi-SkP6yZDcimJ4XvHfzkaNArQGQVaG99wV_OtQ/")
                        .build();

                IGoogleSheets iGoogleSheets = retrofit.create(IGoogleSheets.class);
                String jsonRequest ="{\n" +
                        "    \"spreadsheet_id\":\"13X6RioPLtO_XkA_ViuL_ez2RJP4s6UURclCKnLJPKY0\",\n" +
                        "    \"sheet\":\"pesajes\",\n" +
                        "    \"rows\":[\n" +
                        "        [\n" +
                        "            \"\",\n" +
                        "            \"\",\n" +
                        "            \"\",\n" +
                        "            \"\",\n" +
                        "            \""+Fecha+ "\",\n" +
                        "            \""+Lugar+"\",\n" +
                        "            \""+Region+"\",\n" +
                        "            \""+Campaña+"\",\n" +
                        "            \""+Origen+"\",\n" +
                        "            \""+CelularesCantidad+"\",\n" +
                        "            \""+CelularesKg+"\",\n" +
                        "            \""+TabletsCantidad+"\",\n" +
                        "            \""+TabletsKg+"\",\n" +
                        "            \""+CpusCantidad+"\",\n" +
                        "            \""+CpusKg+"\",\n" +
                        "            \""+NotebooksCantidad+"\",\n" +
                        "            \""+NotebooksKg+"\",\n" +
                        "            \""+OtrosKg+"\"\n" +
                        "        ]\n" +
                        "    ]\n" +
                        "}";
                Call<String> call = iGoogleSheets.getStringRequestBody(jsonRequest);
                Response<String> response = call.execute();
                int code = response.code();
                progressDialog.dismiss();

                if(code ==200){
                    Toast toast = Toast.makeText(this, "Este es mi mensaje", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        });
    }
}