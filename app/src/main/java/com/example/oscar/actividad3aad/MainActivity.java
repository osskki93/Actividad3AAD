package com.example.oscar.actividad3aad;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS = "My preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn_guardar = (Button) findViewById(R.id.btn_Guardar);
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comprobarCampos()){
                    SharedPreferences sharedPreferences = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    EditText innput_Nom = (EditText) findViewById(R.id.inputNom);
                    EditText innput_NomUsuari = (EditText) findViewById(R.id.inputNomUsuari);
                    EditText innput_Data = (EditText) findViewById(R.id.inputData);
                    RadioButton innput_Mascle = (RadioButton) findViewById(R.id.input_Mascle);
                    RadioButton input_Femella = (RadioButton) findViewById(R.id.input_Femella);

                    editor.putString("Nom",innput_Nom.getText().toString());
                    editor.putString("Nom usuari", innput_NomUsuari.getText().toString());
                    editor.putString("Data naixement", innput_Data.getText().toString());
                    editor.putBoolean("Mascle", innput_Mascle.isChecked());
                    editor.commit();
                }
            }
        });

        final Button btn_Recuperar = (Button) findViewById(R.id.btn_Recuperar);
        btn_Recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Preferencias_Activity.class);
                startActivityForResult(i, 0);
            }
        });

        final Button btn_insertarEstudiante = (Button) findViewById(R.id.btn_insertarEstudiante);
        btn_insertarEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), insertarEstudiantes.class);
                startActivityForResult(i, 1);
            }
        });

        final Button btn_insertarProfesor = (Button) findViewById(R.id.btn_insertarProfesor);
        btn_insertarProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), insertProfesores.class);
                startActivityForResult(i, 2);
            }
        });

        final Button btn_insertarAsignatura = (Button) findViewById(R.id.btn_insertarAsignatura);
        btn_insertarAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), InsertarAsignatura.class);
                startActivityForResult(i, 4);
            }
        });

        final Button btn_Consultas = (Button) findViewById(R.id.btn_Consultas);
        btn_Consultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Consultas.class);
                startActivityForResult(i, 3);
            }
        });

        final Button btn_actualizar = (Button) findViewById(R.id.btn_actualizar);
        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),ActualizarDatos.class);
                startActivity(i);
            }
        });

        Button btn_borrar = (Button)findViewById(R.id.btn_borrar);
        btn_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),BorrarDatos.class);
                startActivity(i);
            }
        });
    }

    private boolean comprobarCampos(){

        boolean relleno = false;

        EditText innput_Nom = (EditText) findViewById(R.id.inputNom);
        EditText innput_NomUsuari = (EditText) findViewById(R.id.inputNomUsuari);
        EditText innput_Data = (EditText) findViewById(R.id.inputData);
        RadioButton innput_Mascle = (RadioButton) findViewById(R.id.input_Mascle);
        RadioButton input_Femella = (RadioButton) findViewById(R.id.input_Femella);

        if(innput_Nom.getText().toString() != ""){
            if(innput_NomUsuari.getText().toString() != ""){
                if(innput_Data.getText().toString() != ""){
                    if(innput_Mascle.isChecked() || input_Femella.isChecked()){
                        relleno= true;
                    }
                }
            }
        }

        return relleno;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        switch (requestCode){
            case 1:
                if (resultCode == -1){
                    Toast toast = Toast.makeText(getApplicationContext(), "Estudiante añadido", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;

            case 2:
                if (resultCode == -1) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Profesor añadido", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;

        }

    }

}
