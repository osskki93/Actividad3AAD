package com.example.oscar.actividad3aad;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBAdapter {

    private static final String DATABASE_NAME = "dbFlorida.db";
    private static final String DATABASE_TABLE_PROFESORES = "profesores";
    private static final String DATABASE_TABLE_ESTUDIANTES = "estudiantes";
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



    private static final String CREATE_TABLE_PROFESORES = "CREATE TABLE " +DATABASE_TABLE_PROFESORES+ " (_id integer primary key autoincrement, nombre text, edad text, ciclo text, curso_tutor text, despacho text);";
    private static final String CREATE_TABLE_ESTUDIANTES =  "CREATE TABLE " +DATABASE_TABLE_ESTUDIANTES+ " (_id integer primary key autoincrement, nombre text, edad text, ciclo text, curso text, nota_media text);";


    private static final String DATABASE_DROP_PROFESORES = "DROP TABLE IF EXISTS "+DATABASE_TABLE_PROFESORES+";";
    private static final String DATABASE_DROP_ESTUDIANTES = "DROP TABLE IF EXISTS "+DATABASE_TABLE_ESTUDIANTES+";";


    private final Context context;
    private MyDbHelper dbHelper;
    private SQLiteDatabase db;


    public MyDBAdapter(Context c) {
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }

    public void insertarProfesores(String nombre, String edad, String ciclo, String curso_tutor, String despacho){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE_PROFESOR, nombre);
        newValues.put(EDAD_PROFESOR,edad);
        newValues.put(CICLO_PROFESOR, ciclo);
        newValues.put(CURSO_TUTOR_PROFESOR, curso_tutor);
        newValues.put(DESPACHO_PROFESOR, despacho);
        db.insert(DATABASE_TABLE_PROFESORES,null,newValues);
    }

    public void insertarEstudiantes(String nombre, String edad, String ciclo, String curso, String nota_media){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE_ESTUDIANTE, nombre);
        newValues.put(EDAD_ESTUDIANTE,edad);
        newValues.put(CICLO_ESTUDIANTE, ciclo);
        newValues.put(CURSO_ESTUDIANTE, curso);
        newValues.put(NOTA_MEDIA_ESTUDIANTE, nota_media);
        db.insert(DATABASE_TABLE_ESTUDIANTES,null,newValues);
    }

    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_PROFESORES);
            db.execSQL(CREATE_TABLE_ESTUDIANTES);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP_PROFESORES);
            db.execSQL(DATABASE_DROP_ESTUDIANTES);
            onCreate(db);
        }

    }
}
