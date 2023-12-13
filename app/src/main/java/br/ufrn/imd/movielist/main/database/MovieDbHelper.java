package br.ufrn.imd.movielist.main.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.ufrn.imd.movielist.main.database.MovieContract.MovieData;


public class MovieDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Movies.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MovieData.TABLE_NAME + " (" +
                    MovieData._ID + " INTEGER PRIMARY KEY," +
                    MovieData.COLUMN_NAME_ID_API + " INTEGER," +
                    MovieData.COLUMN_NAME_NAME + " TEXT," +
                    MovieData.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    MovieData.COLUMN_NAME_RELEASE_DATE + " INTEGER," +
                    MovieData.COLUMN_NAME_RATE + " INTEGER," +
                    MovieData.COLUMN_NAME_REVIEW + " TEXT," +
                    MovieData.COLUMN_NAME_IMAGE_URL + " TEXT)";

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }
}
