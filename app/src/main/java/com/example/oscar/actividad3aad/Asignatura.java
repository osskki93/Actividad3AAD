package com.example.oscar.actividad3aad;

public class Asignatura {

    String nombre;
    String num_estudiantes;


    public Asignatura (){

    }

    public Asignatura (String nombre, String num_estudiantes){

        this.nombre = nombre;
        this.num_estudiantes = num_estudiantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNum_estudiantes() {
        return num_estudiantes;
    }

    public void setNum_estudiantes(String num_estudiantes) {
        this.num_estudiantes = num_estudiantes;
    }
}
