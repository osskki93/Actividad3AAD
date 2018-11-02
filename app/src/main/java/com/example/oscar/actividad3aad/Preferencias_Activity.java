package com.example.oscar.actividad3aad;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Preferencias_Activity extends AppCompatActivity {

    public static final String PREFS = "My preferences";


    TextView input_Nom = (TextView) findViewById(R.id.input_Nom);
    TextView input_NomUsuari = (TextView) findViewById(R.id.input_NomUsuari);
    TextView input_Data = (TextView) findViewById(R.id.input_Data);
    TextView input_Sexe = (TextView) findViewById(R.id.input_Sexe);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias_);

        mostrarPreferencias();

        Button btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    public void mostrarPreferencias (){
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);

        String nom = sharedPreferences.getString("Nom","");
        String nomUsuari = sharedPreferences.getString("Nom usuari","");
        String data = sharedPreferences.getString("Data naixement","");
        Boolean mascle = sharedPreferences.getBoolean("Mascle",true);

        input_Nom.setText(nom);
        input_NomUsuari.setText(nomUsuari);
        input_Data.setText(data);

        if (mascle){
            input_Sexe.setText("Mascle");
        }
        else{
            input_Sexe.setText("Femella");
        }


    }
}
