package com.example.oscar.actividad3aad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class insertProfesores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_profesores);

        final EditText inputNombre = (EditText) findViewById(R.id.inputNombre);
        final EditText inputEdad = (EditText) findViewById(R.id.inputEdad);
        final EditText inputCiclo = (EditText) findViewById(R.id.inputCiclo);
        final EditText inputCurso = (EditText) findViewById(R.id.inputCurso);
        final EditText inputDespacho = (EditText) findViewById(R.id.inputDespacho);


        Button btn_Guardar = (Button) findViewById(R.id.btn_Guardar);
        btn_Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBAdapter adaptadorBd = new MyDBAdapter(getApplicationContext());
                adaptadorBd.open();

                adaptadorBd.insertarProfesores(inputNombre.getText().toString(),inputEdad.getText().toString(), inputCiclo.getText().toString(),inputCurso.getText().toString(),inputDespacho.getText().toString());
                Intent i = new Intent();
                setResult(RESULT_OK);
                finish();
            }
        });

    }


}
