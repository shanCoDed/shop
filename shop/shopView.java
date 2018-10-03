package com.at.domain.shop;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class shopView extends AppCompatActivity {
    ListView listview;
    ArrayList<Shop> shopArrayList;
    Shop shop;
    Cursor cursor;
    sqlite shop_db;

    Button sAdd_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_view);

        shop_db = new sqlite(this);

        shopArrayList= new ArrayList<>();
        listview = (ListView) findViewById(R.id.shoplist);
        cursor = shop_db.getshopdata();
        int numRows = cursor.getCount();

        if (numRows == 0)
            Toast.makeText(shopView.this, "No Shops to Display!", Toast.LENGTH_LONG).show();
        else {
            while (cursor.moveToNext()) {
                shop = new Shop(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                shop.setShopid(cursor.getInt(0));
                shopArrayList.add(shop);
            }

            ArrayAdapter custom_obj = new custom(this, shopArrayList);
            listview.setAdapter(custom_obj);

        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View vie, int i, long l) {
                Shop shop  = (Shop) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(shopView.this,shopEdit.class);
                intent.putExtra("shopdetails", (Parcelable) shop);
                startActivity(intent);
            }

        });

        sAdd_btn = (Button) findViewById(R.id.sAdd_btn);

        sAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopView.this,shopInsert.class);
                startActivity(intent);
            }
        });
    }

}