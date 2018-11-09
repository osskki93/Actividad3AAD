package com.example.oscar.actividad3aad;

public class Profesor {

    String nombre;
    String edad;
    String ciclo;
    String curso_tutor;
    String despacho;


    public Profesor() {

    }

    public Profesor (String nombre, String edad, String ciclo, String curso_tutor, String despacho){

        this.nombre = nombre;
        this.edad = edad;
        this.ciclo = ciclo;
        this.curso_tutor = curso_tutor;
        this.despacho = despacho;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getCurso_tutor() {
        return curso_tutor;
    }

    public void setCurso_tutor(String curso_tutor) {
        this.curso_tutor = curso_tutor;
    }

    public String getDespacho() {
        return despacho;
    }

    public void setDespacho(String despacho) {
        this.despacho = despacho;
    }
}
