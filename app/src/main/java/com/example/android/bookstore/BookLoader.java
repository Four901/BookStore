package com.example.android.bookstore;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.List;



public class BookLoader extends AsyncTaskLoader<List<Book>> {

    private String mUrl;

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<Book> loadInBackground() {
        if(mUrl == null) {
            return null;
        }

        return QueryUtils.fetchEarthquakeData(mUrl);
    }

}
