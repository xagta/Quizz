package com.example.bulldozer.revisonds_quizz.utils;

import android.provider.BaseColumns;

/**
 * Created by xagta on 29/10/2017.
 */

public final class ScoreContract {

    public static class ScoreEntery implements BaseColumns{

        public static final String TABLE_NAME="Quizz" ;
        public static final String COLUMN_NAME_Name = "name";
        public static final String COLUMN_NAME_score = "score";
        public static final String COLUMN_NAME_total = "total";


        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + ScoreEntery.TABLE_NAME + " (" +
                        ScoreEntery._ID + " INTEGER PRIMARY KEY," +
                        ScoreEntery.COLUMN_NAME_Name + " TEXT," +
                        ScoreEntery.COLUMN_NAME_score + " TEXT,"+
                        ScoreEntery.COLUMN_NAME_total + " TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + ScoreEntery.TABLE_NAME;

    }
}
