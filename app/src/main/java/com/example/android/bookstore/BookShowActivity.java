package com.example.android.bookstore;


import com.example.android.bookstore.data.Contract.bookEntry;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.bookstore.data.mdbHelper;

import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.net.URL;

public class BookShowActivity extends AppCompatActivity {

    Book bookToShow;

    //in this one we are going to add or delete the books from the database on our preference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_view);
        Log.d("BookStoreActivity", "fuckYouAll");
         Intent intent=getIntent();
//

            bookToShow= (Book) intent.getSerializableExtra("Book");
//            if(bookToShow!=null)
//            {
//                Log.d("BookStoreActivity", "showtous "+bookToShow.getTitle());
//                return;
//            }
            TextView titleView = (TextView) findViewById(R.id.titleId);
            //Log.d("BookStoreActivity", "tino"+bookToShow.getTitle());
            titleView.setText(bookToShow.getTitle());

            ImageView imageView = (ImageView) findViewById(R.id.imageId);
            imageView.setImageResource(bookToShow.getImageres());
//
            TextView averageRatingView = (TextView) findViewById(R.id.avgRatingId);
            averageRatingView.setText("AverageRating:-" + String.valueOf(bookToShow.getAverageRating()));
//
            TextView ratingCountView = (TextView) findViewById(R.id.countRatingId);
            ratingCountView.setText("Rating Count:-" + String.valueOf(bookToShow.getRatingCount()));

            TextView pageCountView = (TextView) findViewById(R.id.pageCountId);
            pageCountView.setText("No Of Pages:-" + String.valueOf(bookToShow.getPageCount()));

            TextView descrtiptionView = (TextView) findViewById(R.id.desctiptionId);
            descrtiptionView.setText("Description:- " + bookToShow.getDescription());
//
            TextView pblshView = (TextView) findViewById(R.id.pblshDateId);
            pblshView.setText("Publish Date:-" + bookToShow.getPublishDate());

            TextView languageView = (TextView) findViewById(R.id.languageId);
            languageView.setText("Language:-" + bookToShow.getLanguage());
//
            TextView isTextAvalView = (TextView) findViewById(R.id.isTextId);
            if (bookToShow.getIsTextAvailable() == true) {
                isTextAvalView.setText("InText -" + " Yes");
            } else {
                isTextAvalView.setText("InText -" + " No");
            }

            TextView isImageAvalView = (TextView) findViewById(R.id.isImageId);
            if (bookToShow.getIsImageAvailable() == true) {
                isImageAvalView.setText("InImage -" + " Yes");
            } else {
                isImageAvalView.setText("InImage -" + " No");
            }

            TextView InfoLinkView = (TextView) findViewById(R.id.toCheckInfoId);
            InfoLinkView.setText("Click for More Info");
            InfoLinkView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    URL url = createUrl(bookToShow.getInfoLink());
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(url)));
                    startActivity(intent);

                }
            });

            TextView previewLinkView = (TextView) findViewById(R.id.toCheckPreviewId);
            previewLinkView.setText("Click for Preview");
            previewLinkView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    URL url = createUrl(bookToShow.getPreviewLink());
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(url)));
                    startActivity(intent);
                }
            });

            TextView seeSmallThumbnailView = (TextView) findViewById(R.id.seeSmallThumbNailId);
            seeSmallThumbnailView.setText("Click for SmallThumbnail");
            seeSmallThumbnailView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    URL url = createUrl(bookToShow.getSmallThumbnailLink());
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(url)));
                    startActivity(intent);
                }
            });

            TextView seeThumbnailView = (TextView) findViewById(R.id.seeThumbnailId);
            seeThumbnailView.setText("Click for Thumbnail");
            seeThumbnailView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    URL url = createUrl(bookToShow.getThumbNailLink());
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(url)));
                    startActivity(intent);
                }
            });
            TextView downloadView=(TextView)findViewById(R.id.downloadId);
        Log.d("BookStoreActivity", "download"+bookToShow.getIsDownloaded());
           mdbHelper mdbhelper=new mdbHelper(this);

           if(bookToShow.getIsDownloaded())
           {
               downloadView.setText("Delete It");
               downloadView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       bookToShow.setUnDownlaoded();
                      // SQLiteDatabase db=mdbhelper.getWritableDatabase();
                       //now we need to delete this from the database
                      // String DatabaseDeleteQuery="DELETE FROM "+ bookEntry.TABLE_NAME+" WHERE "+ bookEntry.COLUMN_TITLE=bookToShow.getTitle() + "AND " + bookEntry.COLUMN_LANGUAGE=bookToShow.getLanguage() + "AND" + bookEntry.COLUMN_THUMBNAILLINK= bookToShow.getThumbNailLink()+ "AND " + bookEntry.COLUMN_INFOLINK = bookToShow.getInfoLink()+"AND"+bookEntry.COLUMN_IMAGERES=bookToShow.getImageres()+"AND "+bookEntry.COLUMN_SMALLTHUMBNAILLINK=bookToShow.getSmallThumbnailLink() +" AND "+bookEntry.COLUMN_THUMBNAILLINK=bookToShow.getThumbNailLink()
                          //     +" AND "+bookEntry.COLUMN_PUBLISHDATE=bookToShow.getPublishDate()+" AND "+bookEntry.COLUMN_DESCRIPTION=bookToShow.getDescription() +" AND "+bookEntry.COLUMN_PAGECOUNT=bookToShow.getPageCount() ;
                        return ;
                   }
               });
           }
           else
           {
               downloadView.setText("Download It");
               downloadView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       bookToShow.setDownloaded();
                       Log.d("BookStoreActivity", "download"+bookToShow.getIsDownloaded());
                       //now we need to insert this book into database
                       SQLiteDatabase db=mdbhelper.getWritableDatabase();

                       ContentValues value=new ContentValues();
                       value.put(bookEntry.COLUMN_TITLE,bookToShow.getTitle());
                       value.put(bookEntry.COLUMN_DESCRIPTION,bookToShow.getDescription());
                       value.put(bookEntry.COLUMN_AVERAGERATING,bookToShow.getAverageRating());
                       value.put(bookEntry.COLUMN_RATINGCOUNT,bookToShow.getRatingCount());
                       value.put(bookEntry.COLUMN_PAGECOUNT,bookToShow.getPageCount());
                       value.put(bookEntry.COLUMN_IMAGERES,bookToShow.getImageres());
                       value.put(bookEntry.COLUMN_PUBLISHDATE,bookToShow.getPublishDate());
                       value.put(bookEntry.COLUMN_ISIMAGEAVAILABLE,bookToShow.getIsImageAvailable());
                       value.put(bookEntry.COLUMN_ISTEXTAVAILABLE,bookToShow.getIsTextAvailable());
                       value.put(bookEntry.COLUMN_INFOLINK,bookToShow.getInfoLink());
                       value.put(bookEntry.COLUMN_LANGUAGE,bookToShow.getLanguage());
                       value.put(bookEntry.COLUMN_PREVIEWLINK,bookToShow.getPreviewLink());
                       value.put(bookEntry.COLUMN_THUMBNAILLINK,bookToShow.getThumbNailLink());
                       value.put(bookEntry.COLUMN_SMALLTHUMBNAILLINK,bookToShow.getSmallThumbnailLink());
                       db.insert(bookEntry.TABLE_NAME,null,value);

                   }
               });
           }


    }
    private static URL createUrl(String stringUrl) {

        URL url = null;

        if(stringUrl == null) {
            return url;
        }
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
