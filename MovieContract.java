package id.ac.umn.movie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class MovieContract {
    //TODO Ganti package name sesuai package kalian
    
    public static final String SQL_CREATE_MOVIES = String.format(
            "CREATE TABLE %s(%s, %s, %s, %s)",
            MovieEntry.TABLE_NAME,
            String.format("%s INTEGER PRIMARY KEY AUTOINCREMENT", MovieEntry._ID),
            String.format("%s VARCHAR(40)", MovieEntry.COLUMN_NAME_TITLE),
            String.format("%s VARCHAR(40)", MovieEntry.COLUMN_NAME_GENRE),
            String.format("%s INTEGER", MovieEntry.COLUMN_NAME_YEAR)
    );
    public static final String SQL_DELETE_MOVIES = String.format(
            "DROP TABLE IF EXISTS %s",
            MovieEntry.TABLE_NAME
    );
	
	
    public static final String SQL_INSERT_MOVIES = String.format(
            "INSERT INTO %s (%s, %s, %s)" +
                    " VALUES ('Frozen','Fantasy',2013)," +
                            "('The Greatest Showman','Romance',2017)," +
                            "('Black Panther','Science Fiction',2018);",
            MovieEntry.TABLE_NAME,
            MovieEntry.COLUMN_NAME_TITLE,
            MovieEntry.COLUMN_NAME_GENRE,
            MovieEntry.COLUMN_NAME_YEAR

    );

    private MovieContract(){}

    public static class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_GENRE = "genre";
    }

    public static class MovieDbHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "movies.db";

        public MovieDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MOVIES);
            db.execSQL(SQL_INSERT_MOVIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL(SQL_DELETE_MOVIES);
            onCreate(db);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //super.onDowngrade(db, oldVersion, newVersion);
            onUpgrade(db, oldVersion, newVersion);
        }
    }
}
