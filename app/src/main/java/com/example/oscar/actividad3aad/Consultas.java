package com.example.oscar.actividad3aad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Consultas extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        final TextView vacio = (TextView) findViewById(R.id.vacio);

        Button btn_listarEstudiantes = (Button)findViewById(R.id.btn_listarEstudiantes);
        btn_listarEstudiantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaciar(vacio);
                MyDBAdapter adaptadorBD = new MyDBAdapter(getApplicationContext());
                adaptadorBD.open();
                adaptadorBD.listarEstudiantes(vacio);
            }
        });


        final Button btn_listarPorCiclo = (Button) findViewById(R.id.btn_listarPorCiclo);
        btn_listarPorCiclo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtBusqueda = (EditText) findViewById(R.id.txtBusqueda);
                vaciar(vacio);
                MyDBAdapter adaptadorBD = new MyDBAdapter(getApplicationContext());
                adaptadorBD.open();
                adaptadorBD.listarEstudiantesWhere("ciclo", txtBusqueda.getText().toString(), vacio);
            }
        });

        final Button btn_listarPorCurso = (Button) findViewById(R.id.btn_listarPorCurso);
        btn_listarPorCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtBusqueda = (EditText) findViewById(R.id.txtBusqueda);
                vaciar(vacio);
                MyDBAdapter adaptadorBD = new MyDBAdapter(getApplicationContext());
                adaptadorBD.open();
                adaptadorBD.listarEstudiantesWhere("curso", txtBusqueda.getText().toString(), vacio);
            }
        });

        Button btn_listarProfesores = (Button)findViewById(R.id.btn_listarProfesores);
        btn_listarProfesores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaciar(vacio);
                MyDBAdapter adaptadorBD = new MyDBAdapter(getApplicationContext());
                adaptadorBD.open();
                adaptadorBD.listarProfesores(vacio);
            }
        });

        Button btn_listarAsignaturas = (Button)findViewById(R.id.btn_listarAsignaturas);
        btn_listarAsignaturas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaciar(vacio);
                MyDBAdapter adaptadorBD = new MyDBAdapter(getApplicationContext());
                adaptadorBD.open();
                adaptadorBD.listarAsignatura(vacio);
            }
        });

        final Button btn_listarAsignaturasPorEstudiantes = (Button) findViewById(R.id.btn_listarAsignaturasPorEstudiantes);
        btn_listarAsignaturasPorEstudiantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtBusqueda = (EditText) findViewById(R.id.txtBusqueda);
                vaciar(vacio);
                MyDBAdapter adaptadorBD = new MyDBAdapter(getApplicationContext());
                adaptadorBD.open();
                adaptadorBD.numeroAlumnos("nombre", txtBusqueda.getText().toString(), vacio);
            }
        });





    }


    public void vaciar(TextView vacio){
        vacio.setText("");
    }



}
