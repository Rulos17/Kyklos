package com.example.kyklos;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
    EditText etFecha, etLugar, etRegion, etCampaña, etOrigen, etCelularesKg, etCelularesCantidad, etTabletsKg, etTablesCantidad, etCpusKg, etCpusCantidad, etNotebooksCantidad, etNotebooksKg, etOtrosKg ;
    View btnEnviar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.et_fecha);
        etFecha = findViewById(R.id.et_fecha);
        etLugar = findViewById(R.id.et_lugar);
        etRegion = findViewById(R.id.et_region);
        etCampaña = findViewById(R.id.et_campaña);
        etOrigen = findViewById(R.id.et_origen);
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
        String Fecha = etFecha.getText().toString();
        String Lugar = etLugar.getText().toString();
        String Region = etRegion.getText().toString();
        String Campaña = etCampaña.getText().toString();
        String Origen = etOrigen.getText().toString();
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
                        "            \""+ Fecha+ "\",\n" +
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
