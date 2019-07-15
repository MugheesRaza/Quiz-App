package com.example.mughees.onlinequizapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mughees on 24-Mar-2018.
 */

public class viewrecord extends AppCompatActivity {

    MyDataBaseHelper2 myDB2;
    ListView listView;
    ArrayList<String> theList;
    Cursor data;
    ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_record_list);

        listView = (ListView)findViewById(R.id.listView);
       myDB2 = new MyDataBaseHelper2(this);

        theList  = new ArrayList<>();
      data = myDB2.getListContents();


        if (data.getCount()== 0 ){
            Toast.makeText(viewrecord.this,"Your data base is empty",Toast.LENGTH_SHORT).show();
        }
        else {

            while (data.moveToNext()){
                theList.add(data.getString(1));
                listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }
    }
}
