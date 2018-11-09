package com.example.oscar.actividad3aad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BorrarDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_datos);

        final EditText cicloBorrarET = (EditText)findViewById(R.id.cicloBorrarET);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBAdapter adaptadorBD = new MyDBAdapter(getApplicationContext());
                adaptadorBD.open();
                String nombre = cicloBorrarET.getText().toString();
                adaptadorBD.eliminarAlumnoPorCiclo(nombre);


                Intent i = new Intent();
                setResult(RESULT_OK);
                finish();

            }
        });
    }
}
