package mx.unitec.novaapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddMovie extends AppCompatActivity {
    EditText etTitulo, etDirector, etAnio, etDescripcion;
    Spinner spGenero;
    Button btnGuardar;

    SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_movie);

        etTitulo = findViewById(R.id.etTitulo);
        etDirector = findViewById(R.id.etDirector);
        etAnio = findViewById(R.id.etAnio);
        etDescripcion = findViewById(R.id.etDescripcion);
        spGenero = findViewById(R.id.spGenero);
        btnGuardar = findViewById(R.id.btnGuardar);

        dbHelper = new SQLiteHelper(this);

        btnGuardar.setOnClickListener(v -> guardarPelicula());
    }

    private void guardarPelicula() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", etTitulo.getText().toString());
        values.put("director", etDirector.getText().toString());
        values.put("anio", Integer.parseInt(etAnio.getText().toString()));
        values.put("genero", spGenero.getSelectedItem().toString());
        values.put("descripcion", etDescripcion.getText().toString());
        values.put("portada", "");  // vacío por ahora
        values.put("usuario_id", 1);  // por defecto

        long result = db.insert("peliculas", null, values);
        Toast.makeText(this, result > 0 ? "Película guardada" : "Error al guardar", Toast.LENGTH_SHORT).show();
    }
}
