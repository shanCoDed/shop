package com.at.domain.shop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class shopInsert extends AppCompatActivity {

    EditText sid, sname, scustomer, saddress, scontact;
    sqlite shop_db;
    Button sInsert_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_insert);
        shop_db = new sqlite(this);

        sname = (EditText) findViewById(R.id.sname);
        scustomer = (EditText) findViewById(R.id.escustomer);
        saddress = (EditText) findViewById(R.id.saddress);
        scontact = (EditText) findViewById(R.id.scontact);

        sInsert_btn = (Button) findViewById(R.id.sInsert_btn);


        sInsert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    if (!sname.getText().toString().isEmpty()) {
                        if (!scustomer.getText().toString().isEmpty()) {
                            if (!saddress.getText().toString().isEmpty()) {
                                if (!scontact.getText().toString().isEmpty()) {

                                    String shop_Name = sname.getText().toString();
                                    String shop_Customer = scustomer.getText().toString();
                                    String shop_Address = saddress.getText().toString();
                                    String shop_Contact = scontact.getText().toString();

                                    Shop shop = new Shop(shop_Name, shop_Customer, shop_Address, shop_Contact);
                                    addShop(shop);
                                } else
                                    Toast.makeText(shopInsert.this, "Contact  cannot be Empty!!!", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(shopInsert.this, "Address cannot be Empty!!!", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(shopInsert.this, "Customer Name cannot be Empty!!!", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(shopInsert.this, "Shop Name cannot be Empty!!!", Toast.LENGTH_LONG).show();

                }

                catch (Exception e) {
                    Toast.makeText(shopInsert.this, "Invalid Input!!!", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    public void addShop(Shop shop) {

        if (shop_db.insertShop(shop)) {
            Toast.makeText(shopInsert.this, "Successfully Inserted!!!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(shopInsert.this,shopView.class);
            startActivity(intent);

        } else
            Toast.makeText(shopInsert.this, "Error while Inserting....", Toast.LENGTH_LONG).show();
            sname.setText("");
            scustomer.setText("");
            saddress.setText("");
            scontact.setText("");
    }

}
