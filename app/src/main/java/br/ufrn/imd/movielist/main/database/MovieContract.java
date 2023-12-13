package br.ufrn.imd.movielist.main.database;

import android.provider.BaseColumns;

public class MovieContract {
    private MovieContract() {};

    public static class MovieData implements BaseColumns {
        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_NAME_ID_API = "id_api";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_RELEASE_DATE = "release_date";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_IMAGE_URL = "image_url";
        public static final String COLUMN_NAME_RATE = "rate";
        public static final String COLUMN_NAME_REVIEW = "review";

    }
}
