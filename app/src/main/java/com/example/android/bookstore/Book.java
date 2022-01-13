package com.example.android.bookstore;

import java.io.Serializable;
import java.util.ArrayList;

public class Book  implements Serializable {
    String title;//
    ArrayList<String>authors;//
    int pageCount;///
    double averageRating;//
    int ratingCount;//
    String description;//
    String publishDate;//
    String language;//
    String previewLink;//
    String infoLink;//
    boolean isTextAvailable;//
    boolean isImagesAvailable;//
    String smallThumbnailLink;//
    String thumbNailLink;//
    int imageres;//
    boolean isDownloaded;

     public Book(String title,ArrayList<String>authors,int pageCount,double averageRating,int ratingCount,String description,String publishDate,String language,String previewLink,String infoLink,boolean isTextAvailable,boolean isImagesAvailable,String smallThumbnailLink,String thumbNailLink )
     {
       this.title=title;
       this.authors=authors;
       this.pageCount=pageCount;
       this.averageRating=averageRating;
       this.ratingCount=ratingCount;
       this.description=description;
       this.publishDate=publishDate;
       this.language=language;
       this.previewLink=previewLink;
       this.infoLink=infoLink;
       this.isTextAvailable=isTextAvailable;
       this.isImagesAvailable=isImagesAvailable;
       this.smallThumbnailLink=smallThumbnailLink;
       this.thumbNailLink=thumbNailLink;
       this.imageres=R.drawable.photo;
       this.isDownloaded=false;
     }

    public String getTitle() {
        return title;
    }
    public void setDownloaded(){
         this.isDownloaded=true;
    }
    public void setUnDownlaoded(){
         this.isDownloaded=false;
    }
    public int getImageres() {
        return imageres;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public int getPageCount() {
        return pageCount;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getDescription() {
        return description;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public String getLanguage() {
        return language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public String getSmallThumbnailLink() {
        return smallThumbnailLink;
    }

    public String getThumbNailLink() {
        return thumbNailLink;
    }
    public boolean getIsTextAvailable()
    {
        return isTextAvailable;
    }
    public boolean getIsImageAvailable()
    {
        return isImagesAvailable;
    }
    public boolean getIsDownloaded(){return isDownloaded;}
}
