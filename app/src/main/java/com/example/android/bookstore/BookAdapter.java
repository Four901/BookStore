package com.example.android.bookstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book>
{

    public BookAdapter(Context context, List<Book> objects) {
        super(context, 0, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        TextView titleView=(TextView)listItemView.findViewById(R.id.titleId);
        TextView avgRatingidView=(TextView)listItemView.findViewById(R.id.avgRatingId);
        ImageView imageView=(ImageView)listItemView.findViewById(R.id.imageId);
        TextView ratingcountidView=(TextView)listItemView.findViewById(R.id.ratingCountID);
        TextView publishDateView=(TextView)listItemView.findViewById(R.id.pblshDateID);
        TextView downloadCheckerView=(TextView)listItemView.findViewById(R.id.downloadCheckId);
        titleView.setText(getItem(position).getTitle());
        publishDateView.setText(getItem(position).getPublishDate());
        imageView.setImageResource(getItem(position).getImageres());
        avgRatingidView.setText("Average Rating -"+toString(getItem(position).getAverageRating()));
        ratingcountidView.setText("Total Rating Count-"+toString(getItem(position).getRatingCount()));

        if(getItem(position).getIsDownloaded())
        {
            downloadCheckerView.setText("Delete It");
        }
        else
        {
            downloadCheckerView.setText("Download It");
        }
        return listItemView;
    }

    private String toString(double averageRating)
    {
     return String.valueOf(averageRating);
    }
}
