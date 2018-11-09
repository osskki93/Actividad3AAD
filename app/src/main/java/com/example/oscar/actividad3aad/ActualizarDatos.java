package com.example.oscar.actividad3aad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActualizarDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_datos);

        final EditText nombreViejoET = (EditText)findViewById(R.id.nombreViejoET);
        final EditText nombreNuevoET = (EditText)findViewById(R.id.nombreNuevoET);

        Button btn_actualizar = (Button) findViewById(R.id.btn_actualizar);
        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBAdapter adaptadorBD = new MyDBAdapter(getApplicationContext());
                adaptadorBD.open();
                adaptadorBD.cambioNombre(nombreViejoET.getText().toString(), nombreNuevoET.getText().toString());

                Intent i = new Intent();
                setResult(RESULT_OK);
                finish();

            }
        });

    }
}
