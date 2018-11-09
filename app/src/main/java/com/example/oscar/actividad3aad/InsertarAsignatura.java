package com.example.oscar.actividad3aad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertarAsignatura extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_asignatura);

        final EditText inputNombre = (EditText) findViewById(R.id.inputNombre);
        final EditText inputNumAlumnos = (EditText) findViewById(R.id.inputNumAlumnos);

        Button btn_Guardar = (Button) findViewById(R.id.btn_Guardar);
        btn_Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBAdapter adaptadorBd = new MyDBAdapter(getApplicationContext());
                adaptadorBd.open();

                adaptadorBd.insertarAsignatura(inputNombre.getText().toString(),inputNumAlumnos.getText().toString());
                Intent i = new Intent();
                setResult(RESULT_OK);
                finish();
            }
        });


    }
}
