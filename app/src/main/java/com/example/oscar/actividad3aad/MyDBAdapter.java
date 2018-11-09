package com.example.oscar.actividad3aad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class MyDBAdapter {

    private static final String DATABASE_NAME = "dbFlorida.db";
    private static final String DATABASE_TABLE_PROFESORES = "profesores";
    private static final String DATABASE_TABLE_ESTUDIANTES = "estudiantes";
    private static final String DATABASE_TABLE_ASIGNATURAS = "asignaturas";
    private static final int DATABASE_VERSION = 1;

    private static final String NOMBRE_PROFESOR = "nombre";
    private static final String EDAD_PROFESOR = "edad";
    private static final String CICLO_PROFESOR = "ciclo";
    private static final String CURSO_TUTOR_PROFESOR = "curso_tutor";
    private static final String DESPACHO_PROFESOR = "despacho";

    private static final String NOMBRE_ESTUDIANTE = "nombre";
    private static final String EDAD_ESTUDIANTE = "edad";
    private static final String CICLO_ESTUDIANTE = "ciclo";
    private static final String CURSO_ESTUDIANTE = "curso";
    private static final String NOTA_MEDIA_ESTUDIANTE = "nota_media";

    private static final String NOMBRE_ASIGNATURA = "nombre";
    private static final String NUM_ESTUDIANTES_ASIGNATURA = "num_estudiantes";


    private static final String CREATE_TABLE_PROFESORES = "CREATE TABLE " + DATABASE_TABLE_PROFESORES + " (_id integer primary key autoincrement, nombre text, edad text, ciclo text, curso_tutor text, despacho text);";
    private static final String CREATE_TABLE_ESTUDIANTES = "CREATE TABLE " + DATABASE_TABLE_ESTUDIANTES + " (_id integer primary key autoincrement, nombre text, edad text, ciclo text, curso text, nota_media text);";
    private static final String CREATE_TABLE_ASIGNATURAS = "CREATE TABLE " + DATABASE_TABLE_ASIGNATURAS + " (_id integer primary key autoincrement, nombre text, num_estudiantes text);";

    private static final String DATABASE_DROP_PROFESORES = "DROP TABLE IF EXISTS " + DATABASE_TABLE_PROFESORES + ";";
    private static final String DATABASE_DROP_ESTUDIANTES = "DROP TABLE IF EXISTS " + DATABASE_TABLE_ESTUDIANTES + ";";
    private static final String DATABASE_DROP_ASIGNATURAS = "DROP TABLE IF EXISTS " + DATABASE_TABLE_ASIGNATURAS + ";";


    private final Context context;
    private MyDbHelper dbHelper;
    private SQLiteDatabase db;


    public MyDBAdapter(Context c) {
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() {

        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = dbHelper.getReadableDatabase();
        }

    }

    public void insertarProfesores(String nombre, String edad, String ciclo, String curso_tutor, String despacho) {
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE_PROFESOR, nombre);
        newValues.put(EDAD_PROFESOR, edad);
        newValues.put(CICLO_PROFESOR, ciclo);
        newValues.put(CURSO_TUTOR_PROFESOR, curso_tutor);
        newValues.put(DESPACHO_PROFESOR, despacho);
        db.insert(DATABASE_TABLE_PROFESORES, null, newValues);
    }

    public void insertarEstudiantes(String nombre, String edad, String ciclo, String curso, String nota_media) {
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE_ESTUDIANTE, nombre);
        newValues.put(EDAD_ESTUDIANTE, edad);
        newValues.put(CICLO_ESTUDIANTE, ciclo);
        newValues.put(CURSO_ESTUDIANTE, curso);
        newValues.put(NOTA_MEDIA_ESTUDIANTE, nota_media);
        db.insert(DATABASE_TABLE_ESTUDIANTES, null, newValues);
    }


    public void insertarAsignatura (String nombre, String num_estudiantes){

        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE_ASIGNATURA,nombre);
        newValues.put(NUM_ESTUDIANTES_ASIGNATURA, num_estudiantes);
        db.insert(DATABASE_TABLE_ASIGNATURAS,null,newValues);
    }

    public boolean listarAsignatura (TextView resultados) {

        ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
        Asignatura a;
        Cursor cursor = db.query(DATABASE_TABLE_ASIGNATURAS, null, null, null, null, null, null);
        //Cursor cursor = db.rawQuery("select * from " + DATABASE_TABLE_ASIGNATURAS, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                a = new Asignatura(cursor.getString(1), cursor.getString(2));
                asignaturas.add(a);
            } while (cursor.moveToNext());
        }
        if (asignaturas.isEmpty()) {
            resultados.setText("No hay asignatura");
            return false;
        } else {
            Iterator<Asignatura> iterator = asignaturas.iterator();
            while (iterator.hasNext()) {
                a = iterator.next();
                resultados.setText(resultados.getText() + "Nombre: " + a.getNombre() + "\nNumero alumnos: " + a.getNum_estudiantes() + "\n");
                resultados.setText(resultados.getText() + "-------------------------------" + "\n");
            }
            return true;
        }

    }


    public boolean listarEstudiantes (TextView resultados){

        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
        Estudiante e;
        int edadminima = 20;
        int edadmaxima = 25;

        //Cursor cursor = db.query(DATABASE_TABLE_ESTUDIANTES,null,null,null,null,null,EDAD_ESTUDIANTE);
        Cursor cursor = db.rawQuery("select * from " + DATABASE_TABLE_ESTUDIANTES + " where edad BETWEEN '" + edadminima + "' AND '" + edadmaxima + "' ORDER BY edad ASC", null);
        if (cursor != null && cursor.moveToFirst()){
            do {
                e = new Estudiante(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
                estudiantes.add(e);
            } while (cursor.moveToNext());
        }
        if (estudiantes.isEmpty()){
            resultados.setText("No hay estudiantes");
            return false;
        }
        else {
            Iterator<Estudiante> iterator = estudiantes.iterator();
            while (iterator.hasNext()){
                e = iterator.next();
                resultados.setText(resultados.getText()+ "Nombre: " +e.getNombre()+ "\nEdad: "+e.getEdad()+ "\nCiclo: "+e.getCiclo()+ "\nCurso: "+e.getCurso()+ "\nNota media: "+e.getNotaMedia()+ "\n");
                resultados.setText(resultados.getText()+ "-------------------------------" + "\n");
            }
            return true;
        }

    }

    public boolean listarEstudiantesWhere(String columna, String palabra, TextView resultados) {
        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
        Estudiante e;
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE_ESTUDIANTES,null,columna + "=?", new String[]{palabra},null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do {
                e = new Estudiante(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
                estudiantes.add(e);
            } while (cursor.moveToNext());
        }
        if(estudiantes.isEmpty()) {
            resultados.setText("No hay nada que listar");
            return false;
        } else {
            Iterator<Estudiante> iterator = estudiantes.iterator();
            while(iterator.hasNext()) {
                e = iterator.next();
                resultados.setText(resultados.getText() + "Nombre: " + e.getNombre() + "\nEdad: " + e.getEdad() + "\nCiclo: " + e.getCiclo() + "\nCurso: " + e.getCurso() + "º\nNota Media: " + e.getNotaMedia() + "\n");
                resultados.setText(resultados.getText() + "------------------------------------\n");
            }
            return true;
        }
    }

    public boolean numeroAlumnos (String columna, String palabra, TextView resultados){

        ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
        Asignatura a;

        Cursor cursor = db.query(DATABASE_TABLE_ASIGNATURAS,null,columna + "=?", new String[]{palabra},null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do {
                a = new Asignatura(cursor.getString(1), cursor.getString(2));
                asignaturas.add(a);
            } while (cursor.moveToNext());
        }
        if(asignaturas.isEmpty()) {
            resultados.setText("No existe la asignatura");
            return false;
        } else {
            Iterator<Asignatura> iterator = asignaturas.iterator();
            while(iterator.hasNext()) {
                a = iterator.next();
                resultados.setText(resultados.getText() + "Nombre: " + a.getNombre() + "\nNumero alumnos: " + a.getNum_estudiantes() + "\n");
                resultados.setText(resultados.getText() + "-------------------------------" + "\n");
            }
            return true;
        }
    }

    public boolean listarProfesores (TextView resultados){

        ArrayList<Profesor> profesores = new ArrayList<Profesor>();
        Profesor p;
        Cursor cursor = db.query(DATABASE_TABLE_PROFESORES,null,null,null,null,null,null);
        if (cursor != null && cursor.moveToFirst()){
            do {
                p = new Profesor(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
                profesores.add(p);
            } while (cursor.moveToNext());
        }
        if (profesores.isEmpty()){
            resultados.setText("No hay profesores");
            return false;
        }
        else {
            Iterator<Profesor> iterator = profesores.iterator();
            while (iterator.hasNext()){
                p = iterator.next();
                resultados.setText(resultados.getText()+ "Nombre: " +p.getNombre()+ "\nEdad: "+p.getEdad()+ "\nCiclo: "+p.getCiclo()+ "\nCurso tutor: "+p.getCurso_tutor()+ "\nDespacho: "+p.getDespacho()+ "\n");
                resultados.setText(resultados.getText()+ "-------------------------------" + "\n");
            }
            return true;
        }

    }



    public void cambioNombre (String nombreViejo, String nombreNuevo){
        ContentValues cv = new ContentValues();
        cv.put(NOMBRE_ESTUDIANTE,nombreNuevo);
        db.update(DATABASE_TABLE_ESTUDIANTES, cv, "nombre =?", new String[]{nombreViejo});
    }

    public void eliminarAlumnoPorCiclo(String nombre){
        String ciclo = nombre;
        db.delete(DATABASE_TABLE_ESTUDIANTES,"ciclo=?",new String[]{ciclo});
    }

    
    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_PROFESORES);
            db.execSQL(CREATE_TABLE_ESTUDIANTES);
            db.execSQL(CREATE_TABLE_ASIGNATURAS);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP_PROFESORES);
            db.execSQL(DATABASE_DROP_ESTUDIANTES);
            db.execSQL(DATABASE_DROP_ASIGNATURAS);

            onCreate(db);
        }

    }
}
