package id.ac.umn.movie;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    //TODO Ganti package name sesuai package kalian

    EditText txtTitle;
    EditText txtYear;
    Spinner spinnerGenre;

    int selectedSpinner = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final Spinner spinner = (Spinner) findViewById(R.id.spinnerGenre);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.,
                android.R.layout.simple_spinner_item
        );



        Button addButton = (Button) findViewById(R.id.btnAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText txtTitle = findViewById(R.id.txtTitle);
                final EditText txtYear = findViewById(R.id.txtYear);
                String genre = spinner.getSelectedItem().toString();

                MovieContract.MovieDbHelper MovDbHelper = new MovieContract.MovieDbHelper(AddActivity.this);
                SQLiteDatabase db = MovDbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(
                        MovieContract.MovieEntry.COLUMN_NAME_TITLE,txtTitle.getText().toString();
                );
                values.put(
                        MovieContract.MovieEntry.COLUMN_NAME_YEAR,txtYear.getText().toString();
                );
                values.put(
                        MovieContract.MovieEntry.COLUMN_NAME_GENRE,genre
                );

                long newRowid = db.insert(
                        MovieContract.MovieEntry.TABLE_NAME,
                        null,
                        values
                );

                startActivity(new Intent(AddActivity.this, ListActivity.class));
                finish();
            }
        });
    }
}
