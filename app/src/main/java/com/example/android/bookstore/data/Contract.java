package com.example.android.bookstore.data;

import android.provider.BaseColumns;

import java.util.ArrayList;

public final class Contract {
    private Contract(){}
    public final static class bookEntry implements BaseColumns{
        public final static String TABLE_NAME ="BookStoreTable";
        public final static String _ID=BaseColumns._ID;
        public final static String COLUMN_TITLE="title";
        public final static String COLUMN_PAGECOUNT="pagecount";///
        public final static String COLUMN_AVERAGERATING="averageRating";//
        public final static String COLUMN_RATINGCOUNT="ratingCount";//
        public final static String COLUMN_DESCRIPTION="description";//
        public final static String COLUMN_PUBLISHDATE="publishDate";//
        public final static  String COLUMN_LANGUAGE="language";//
        public final static  String COLUMN_PREVIEWLINK="previewLink";//
        public final static  String COLUMN_INFOLINK="infoLink";//
        public final static  String COLUMN_ISTEXTAVAILABLE="isTextAvailable";//
        public final static  String COLUMN_ISIMAGEAVAILABLE="isImagesAvailable";//
        public final static  String COLUMN_SMALLTHUMBNAILLINK="smallThumbnailLink";//
        public final static   String COLUMN_THUMBNAILLINK="thumbNailLink";//
        public final static  String COLUMN_IMAGERES="imageres";//

    }
}
