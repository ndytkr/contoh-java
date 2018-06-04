package id.ac.umn.movie;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {

    //TODO Ganti package name sesuai package kalian

    EditText txtTitle;
    EditText txtYear;
    Spinner spinnerGenre;

    int selectedSpinner = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        SharedPreferences preferences = getSharedPreferences("PREFERENCES", MODE_PRIVATE);


        final Spinner spinner = (Spinner) findViewById(R.id.);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.,
                android.R.layout.simple_spinner_item
        );
        arrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(arrayAdapter.getPosition());
        spinner.setOnItemSelectedListener(this);

        Button updButton = (Button) findViewById(R.id.updBtn);
        updButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText titleEdit = (EditText) findViewById(R.id.txtTitleEdit);
                final EditText yearEdit = (EditText) findViewById(R.id.txtYearEdit);
                final String SpinnerGenre = spinner.getSelectedItem().toString();

                MovieContract.MovieDbHelper MovDbHelper = new MovieContract.MovieDbHelper(UpdateActivity.this);
                SQLiteDatabase db = MovDbHelper.getWritableDatabase();

                String[] Movie = {
                        MovieContract.MovieEntry.COLUMN_NAME_TITLE,
                        MovieContract.MovieEntry.COLUMN_NAME_YEAR,
                        MovieContract.MovieEntry.COLUMN_NAME_GENRE,
                };

                Cursor cursor = db.query(
                        MovieContract.MovieEntry.TABLE_NAME,
                        Movie,
                        null,
                        null,
                        null,
                        null,
                        null
                );

                cursor.moveToFirst();

                for(int i=0; i<cursor.getCount(); i++,cursor.moveToNext()) {

                    String title = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_NAME_TITLE));

                    if (title.equals()) {
                        ContentValues values = new ContentValues();
                        values.put(
                                MovieContract.MovieEntry.COLUMN_NAME_TITLE,
                                titleEdit.getText().toString()
                        );
                        values.put(
                                MovieContract.MovieEntry.COLUMN_NAME_YEAR,
                                yearEdit.getText().toString();
                        );
                        values.put(
                                MovieContract.MovieEntry.COLUMN_NAME_GENRE,
                                SpinnerGenre
                        );

                    }
                }
            }
        });
    }
}
