package com.example.android.bookstore.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.bookstore.data.Contract.bookEntry;
public class mdbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG=mdbHelper.class.getSimpleName();
    private static final String DATABASE_NAME="BookStoreDATABASE";
    private static final int DATABASE_VERSION=1;

    public mdbHelper(Context context){super(context,DATABASE_NAME,null,DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String TABLE_CREATE_STATEMENT="CREATE TABLE "+ bookEntry.TABLE_NAME + "(" + bookEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                bookEntry.COLUMN_TITLE + " TEXT NOT NULL, " + bookEntry.COLUMN_AVERAGERATING+" TEXT NOT NULL, "+ bookEntry.COLUMN_RATINGCOUNT +" TEXT NOT NULL, " + bookEntry.COLUMN_DESCRIPTION +" TEXT NOT NULL, " + bookEntry.COLUMN_PAGECOUNT + " TEXT NOT NULL , " + bookEntry.COLUMN_LANGUAGE +" TEXT NOT NULL, "  + bookEntry.COLUMN_PUBLISHDATE +" TEXT NOT NULL, " +bookEntry.COLUMN_IMAGERES +" TEXT NOT NULL, "
                +bookEntry.COLUMN_ISIMAGEAVAILABLE+" TEXT NOT NULL, "+bookEntry.COLUMN_ISTEXTAVAILABLE+" TEXT NOT NULL, "+
                bookEntry.COLUMN_INFOLINK +" TEXT NOT NULL, "+bookEntry.COLUMN_PREVIEWLINK+" TEXT NOT NULL, "+bookEntry.COLUMN_SMALLTHUMBNAILLINK+" TEXT NOT NULL, "+bookEntry.COLUMN_THUMBNAILLINK+" TEXT NOT NULL);";

        sqLiteDatabase.execSQL(TABLE_CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
