package com.example.android.bookstore;


import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.android.bookstore.data.Contract.bookEntry;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.bookstore.data.Contract;
import com.example.android.bookstore.data.mdbHelper;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookStoreActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final int BOOK_LOADER_ID = 1;

    ArrayList<Book> books = new ArrayList<>();

    ArrayList<Book>downloadedBooks=new ArrayList<>();
    BookAdapter mAdapter;
    ListView bookListView;
    TextView mEmptyState;
    ProgressBar mProgressBar;
    mdbHelper mdbhelper;

    public static final String LOG_TAG = BookStoreActivity.class.getName();

    private static final String BOOK_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=10";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);

        mdbhelper=new mdbHelper(this);

        // DownloadData downloadData = new DownloadData();
        //downloadData.execute(USGS_REQUEST_URL);

        mEmptyState = (TextView) findViewById(R.id.empty_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Checking the internet connection before initializing the loader
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);

        } else {
            mProgressBar.setVisibility(View.GONE);
            mEmptyState.setText(R.string.no_internet_connection);
        }

        bookListView = (ListView) findViewById(R.id.list);

        bookListView.setEmptyView(mEmptyState);

        Log.d("BookStoreActivity", "Length is" + String.valueOf(books.size()));

        mAdapter = new BookAdapter(this, books);
        bookListView.setAdapter(mAdapter);
        Log.d("BookStoreActivity", "cheele: "+bookListView.toString());
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Book bookToView = books.get(i);

                Intent intent = new Intent(BookStoreActivity.this, BookShowActivity.class);
                intent.putExtra("Book", bookToView);
                startActivity(intent);
//                String urli = books.get(i).getInfoLink();
//                URL url = createUrl(urli);
//
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(url)));
//                startActivity(intent);
            }
        });
        // Create a new {@link ArrayAdapter} of books




    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        return new BookLoader(this, BOOK_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        mAdapter.clear();
        if (books == null) {

            return;
        }

        mAdapter.addAll(books);
        System.out.println(books.size());
        mProgressBar.setVisibility(View.GONE);
        mEmptyState.setText(R.string.no_book_found);

    }

    private static URL createUrl(String stringUrl) {

        URL url = null;

        if (stringUrl == null) {
            return url;
        }
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    private void showDownloadedItems()
    {

        SQLiteDatabase db=mdbhelper.getReadableDatabase();
        String [] Projection={
                bookEntry.COLUMN_TITLE,
                bookEntry.COLUMN_DESCRIPTION,
                bookEntry.COLUMN_AVERAGERATING,
                bookEntry.COLUMN_RATINGCOUNT,
                bookEntry.COLUMN_PAGECOUNT,
                bookEntry.COLUMN_PUBLISHDATE,
                bookEntry.COLUMN_LANGUAGE,
                bookEntry.COLUMN_IMAGERES,
                bookEntry.COLUMN_ISTEXTAVAILABLE,
                bookEntry.COLUMN_ISIMAGEAVAILABLE,
                bookEntry.COLUMN_INFOLINK,
                bookEntry.COLUMN_PREVIEWLINK,
                bookEntry.COLUMN_THUMBNAILLINK,
                bookEntry.COLUMN_SMALLTHUMBNAILLINK};

        Cursor cursor =db.query(bookEntry.TABLE_NAME,Projection,null,null,null,null, null);
        try {

            mEmptyState = (TextView) findViewById(R.id.empty_view);
            mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
            if (cursor.getCount()==0) {
                mProgressBar.setVisibility(View.GONE);
                mEmptyState.setText("No Downloads");
                 return ;
            }
            bookListView = (ListView) findViewById(R.id.list);

            bookListView.setEmptyView(mEmptyState);


            int columnTitleIndex=cursor.getColumnIndex(bookEntry.COLUMN_TITLE);
            int columnDescriptionIndex=cursor.getColumnIndex(bookEntry.COLUMN_DESCRIPTION);
            int columnAverageRatingIndex=cursor.getColumnIndex(bookEntry.COLUMN_AVERAGERATING);
            int columnRatingCountIndex=cursor.getColumnIndex(bookEntry.COLUMN_RATINGCOUNT);
            int columnPageCountIndex=cursor.getColumnIndex(bookEntry.COLUMN_PAGECOUNT);
            int columnLanguageIndex=cursor.getColumnIndex(bookEntry.COLUMN_LANGUAGE);
            int columnPublishDateIndex=cursor.getColumnIndex(bookEntry.COLUMN_PUBLISHDATE);
            int columnImageResIndex=cursor.getColumnIndex(bookEntry.COLUMN_IMAGERES);
            int columnIsTextAvailableIndex=cursor.getColumnIndex(bookEntry.COLUMN_ISTEXTAVAILABLE);
            int columnIsImageAvailableIndex=cursor.getColumnIndex(bookEntry.COLUMN_ISIMAGEAVAILABLE);
            int columnInfoLinkIndex=cursor.getColumnIndex(bookEntry.COLUMN_INFOLINK);
            int columnPreviewLinkIndex=cursor.getColumnIndex(bookEntry.COLUMN_PREVIEWLINK);
            int columnThumbNailLinkIndex=cursor.getColumnIndex(bookEntry.COLUMN_THUMBNAILLINK);
            int columnSmallThumbNailLink=cursor.getColumnIndex(bookEntry.COLUMN_SMALLTHUMBNAILLINK);

            while(cursor.moveToNext())
            {
              String title=cursor.getString(columnTitleIndex);
              String description=cursor.getString(columnDescriptionIndex);
              Double averageRating=cursor.getDouble(columnAverageRatingIndex);
              int ratingCount=cursor.getInt(columnRatingCountIndex);
              int pageCount=cursor.getInt(columnPageCountIndex);
              String langugage=cursor.getString(columnLanguageIndex);
              String publishDate=cursor.getString(columnPublishDateIndex);
              int imageRes=cursor.getInt(columnImageResIndex);
              int isTextAval=cursor.getInt(columnIsTextAvailableIndex);
              int isImageAval=cursor.getInt(columnIsImageAvailableIndex);
              String infoLink=cursor.getString(columnInfoLinkIndex);
              String previewLink=cursor.getString(columnPreviewLinkIndex);
              String thumbNailLink=cursor.getString(columnThumbNailLinkIndex);
              String smallThumbNailLink=cursor.getString(columnSmallThumbNailLink);

              downloadedBooks.add(new Book(title,null,pageCount,averageRating,ratingCount,description,publishDate,langugage,previewLink,infoLink,isTextAval==1?true:false,isImageAval==1?true:false,thumbNailLink,smallThumbNailLink));

            }

            if(downloadedBooks==null)
            {
                return ;
            }
            Log.d("BookStoreActivity", "showDownloadedItems1: "+downloadedBooks.size());
            mAdapter = new BookAdapter(this, downloadedBooks);
            Log.d("BookStoreActivity", "showDownloadedItems2: "+downloadedBooks.size());
            bookListView.setAdapter(mAdapter);
            Log.d("BookStoreActivity", "showDownloadedItems2: "+bookListView.toString());

            bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Book bookToView = downloadedBooks.get(i);

                    Intent intent = new Intent(BookStoreActivity.this, BookShowActivity.class);
                    intent.putExtra("Book", bookToView);
                    startActivity(intent);
//                String urli = books.get(i).getInfoLink();
//                URL url = createUrl(urli);
//
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(url)));
//                startActivity(intent);
                }
            });

        }
        finally {
            cursor.close();
        }


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        {
            switch (item.getItemId()) {
                case R.id.action_list_all_downloads:
                    showDownloadedItems();
                    
                    return true;

                case R.id.action_list_all_liked:
                    return true;

            }
            return super.onOptionsItemSelected(item);
        }


//    private class DownloadData extends AsyncTask<String, Void, List<Earthquake> > {
//
//        @Override
//        protected List<Earthquake> doInBackground(String... urls) {
//
//            List<Earthquake> earthquakes = new ArrayList<>();
//            if(urls.length < 1 || urls[0] == null) {
//                return null;
//            }
//
//            earthquakes = QueryUtils.fetchEarthquakeData(urls[0]);
//            return earthquakes;
//        }
//
//        @Override
//        protected void onPostExecute(List<Earthquake> earthquakesList) {
//

//            mAdapter.clear();
//            if(earthquakesList != null && !earthquakesList.isEmpty() ) {
//                mAdapter.addAll(earthquakesList);
//            }
//        }
//    }

    }
}
