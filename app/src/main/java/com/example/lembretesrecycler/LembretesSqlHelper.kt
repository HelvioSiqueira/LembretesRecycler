package com.example.lembretesrecycler

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class LembretesSqlHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE $TABLE_HOTEL(" +
                    "$COLUMN_TITULO TEXT PRIMARY KEY," +
                    "$COLUMN_TEXT TEXT," +
                    "$COLUMN_PRIORIDADE TEXT," +
                    "$COLUMN_DATA)")
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}