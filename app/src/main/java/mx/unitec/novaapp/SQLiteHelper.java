package mx.unitec.novaapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movieplus.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "usuario TEXT NOT NULL, " +
                "password TEXT NOT NULL)");
        db.execSQL("CREATE TABLE peliculas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT NOT NULL, " +
                "director TEXT, " +
                "anio INTEGER, " +
                "genero TEXT, " +
                "descripcion TEXT, " +
                "portada TEXT, " +
                "usuario_id INTEGER, " +
                "FOREIGN KEY(usuario_id) REFERENCES usuarios(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS peliculas");
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }
}
