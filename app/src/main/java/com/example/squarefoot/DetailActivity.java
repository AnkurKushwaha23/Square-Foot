package com.example.squarefoot;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private TextView detailDate, detailLength, detailWidth, detailPrice, detailResult;
    private Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar ab = getSupportActionBar();

        if (ab !=null) {
            ab.setTitle("Detail");
        }
        // Initialize views
        detailDate = findViewById(R.id.detailDate);
        detailLength = findViewById(R.id.detailLength);
        detailWidth = findViewById(R.id.detailWidth);
        detailPrice = findViewById(R.id.detailPrice);
        detailResult = findViewById(R.id.detailResult);
        btnDelete = findViewById(R.id.btnDelete);

        // Retrieve data from Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            long iD = extras.getInt("id");
            String date = extras.getString("date");
            String length = extras.getString("length");
            String width = extras.getString("width");
            String price = extras.getString("ppu");
            String result = extras.getString("result");

            // Set data to TextViews
            detailDate.setText(date);
            detailLength.setText(length);
            detailWidth.setText(width);
            detailPrice.setText(price);
            detailResult.setText(result);
        }
        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                builder.setMessage("Are you sure you want to Delete?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteData();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
    public void deleteData(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Delete data from the database
            DatabaseHelper dbHelper = new DatabaseHelper(DetailActivity.this);
            String result = extras.getString("result");
            long iD = extras.getInt("id");
            dbHelper.deleteDataFromDatabase(iD);
            finish();
        }
    }
}
