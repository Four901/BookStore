package com.example.android.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookStoreActivity bookStoreActivity=new BookStoreActivity();


        final ArrayList<Book> books =new ArrayList<Book>();
        ArrayList<String>arr=new ArrayList<String>();
        arr.add("james anderson");
        books.add(new Book("book",arr,25,4,10,"The book is about anderson","25-12-2019","en","non","non",true,true,"non","non"));
        books.add(new Book("book",arr,25,4,10,"The book is about anderson","25-12-2019","en","non","non",true,true,"non","non"));
        books.add(new Book("book",arr,25,4,10,"The book is about anderson","25-12-2019","en","non","non",true,true,"non","non"));
        books.add(new Book("book",arr,25,4,10,"The book is about anderson","25-12-2019","en","non","non",true,true,"non","non"));
        books.add(new Book("book",arr,25,4,10,"The book is about anderson","25-12-2019","en","non","non",true,true,"non","non"));
        books.add(new Book("book",arr,25,4,10,"The book is about anderson","25-12-2019","en","non","non",true,true,"non","non"));
        books.add(new Book("book",arr,25,4,10,"The book is about anderson","25-12-2019","en","non","non",true,true,"non","non"));
        books.add(new Book("book",arr,25,4,10,"The book is about anderson","25-12-2019","en","non","non",true,true,"non","non"));


        BookAdapter itemsAdapter = new BookAdapter(this,books);
        ListView listView = (ListView) findViewById(R.id.root_id);
        listView.setAdapter(itemsAdapter);

    }
}