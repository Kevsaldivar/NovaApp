package mx.unitec.novaapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movieplus.db";
    private static final int DATABASE_VERSION = 2; // Incrementar la versión

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla de usuarios
        db.execSQL("CREATE TABLE usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "usuario TEXT NOT NULL, " +
                "password TEXT NOT NULL)");

        // Crear tabla de categorías
        db.execSQL("CREATE TABLE categorias (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL)");

        // Crear tabla de películas
        db.execSQL("CREATE TABLE peliculas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT NOT NULL, " +
                "director TEXT, " +
                "anio INTEGER, " +
                "descripcion TEXT, " +
                "portada TEXT, " +
                "categoria_id INTEGER, " +
                "FOREIGN KEY(categoria_id) REFERENCES categorias(id))");

        // Crear tabla de favoritos
        db.execSQL("CREATE TABLE favoritos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "usuario_id INTEGER, " +
                "pelicula_id INTEGER, " +
                "FOREIGN KEY(usuario_id) REFERENCES usuarios(id), " +
                "FOREIGN KEY(pelicula_id) REFERENCES peliculas(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar tablas existentes
        db.execSQL("DROP TABLE IF EXISTS favoritos");
        db.execSQL("DROP TABLE IF EXISTS peliculas");
        db.execSQL("DROP TABLE IF EXISTS categorias");
        db.execSQL("DROP TABLE IF EXISTS usuarios");

        // Crear tablas nuevamente
        onCreate(db);
    }
}

