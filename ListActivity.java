package id.ac.umn.movie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class ListActivity extends AppCompatActivity {

    //TODO Ganti package name sesuai package kalian

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, AddActivity.class));
            }
        });


        final String name = getIntent().getStringExtra("NAME");

        SharedPreferences preferences = getSharedPreferences("PREFERENCES",MODE_PRIVATE);
        final String name = preferences.getString("NAME","");
        TextView txtWelcome = (TextView) findViewById(R.id.txtWelcome);
        txtWelcome.setText("Welcome, " + name );

        populateList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        populateList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateList();
    }

    public void populateList(){

        MovieContract.MovieDbHelper movieDbHelper = new MovieContract.MovieDbHelper(this);
        SQLiteDatabase db = movieDbHelper.getReadableDatabase();

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

        final ArrayList<String> list = new ArrayList<String>();
        final List<Movie> listMovie = new ArrayList<Movie>();


        final ListView listMovie = (ListView) findViewById(R.id.listMovie);

        for (int i = 0; i<cursor.getCount(); i++, cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_NAME_TITLE));
            String year = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_NAME_YEAR));
            String genre = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_NAME_GENRE));
        }

        list.add(title + " - " + year + "(" + genre + ")");
        listMovie.add(new Movie(title,year,genre));

        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_expandable_list_item_1, list);
        listMovie.setAdapter(adapter);


        listMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String s = String.valueOf(parent.getItemAtPosition(position));

                Movie selectedMovie = listMovie.get(position);
                String idRow = String.valueOf(selectedMovie.getId());

                SharedPreferences.Editor prefEdit
                        = getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit();
                prefEdit.putString("ID", idRow);
                prefEdit.putString("S", s);
                prefEdit.commit();

                Intent i = new Intent(ListActivity.this, UpdateActivity.class);

                startActivity(i);
            }
        });
    }
}

