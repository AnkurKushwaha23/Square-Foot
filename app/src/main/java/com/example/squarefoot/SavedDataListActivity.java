package com.example.squarefoot;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SavedDataListActivity extends AppCompatActivity {
    public static final String TABLE_NAME = "billing_data";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_LENGTH = "length";
    public static final String COLUMN_WIDTH = "width";
    public static final String COLUMN_UNIT_PRICE = "unit_price";
    public static final String COLUMN_RESULT = "result";
    private List<CustomItem> dataList;
    private CustomAdapter customAdapter;
    private DatabaseHelper databaseHelper;
    ImageView imgEmpty;
    TextView txtEmpty;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_data_list);
        imgEmpty=findViewById(R.id.imageViewEmpty);
        txtEmpty=findViewById(R.id.textviewEmpty);
        Toolbar toolbar = findViewById(R.id.toolSave);
        setSupportActionBar(toolbar);

        dataList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);

        // Set your adapter
        customAdapter = new CustomAdapter(databaseHelper,SavedDataListActivity.this,this, dataList);
        recyclerView.setAdapter(customAdapter);
        getAllItem();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            recreate();
        }
    }

    @SuppressLint({"Range", "NotifyDataSetChanged"})
    public void getAllItem() {
        dataList.clear(); // Clear the dataList before adding items
        SQLiteDatabase db = this.databaseHelper.getReadableDatabase();
        Cursor cur = null;
        db.beginTransaction();
        try {
            cur = db.query(TABLE_NAME, null, null, null, null, null, null, null);
            if (cur.getCount() == 0) {
                imgEmpty.setVisibility(View.VISIBLE);
                txtEmpty.setVisibility(View.VISIBLE);
            } else {
                while (cur.moveToNext()) {
                    CustomItem item = new CustomItem();
                    item.setId(cur.getInt(cur.getColumnIndex(COLUMN_ID)));
                    item.setDate(cur.getString(cur.getColumnIndex(COLUMN_DATE)));
                    item.setLength(cur.getString(cur.getColumnIndex(COLUMN_LENGTH)));
                    item.setWidth(cur.getString(cur.getColumnIndex(COLUMN_WIDTH)));
                    item.setPpu(cur.getString(cur.getColumnIndex(COLUMN_UNIT_PRICE)));
                    item.setResult(cur.getString(cur.getColumnIndex(COLUMN_RESULT)));
                    dataList.add(item); // Add items to dataList
                }
            }
        } finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
            customAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.d_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.deleteAll) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SavedDataListActivity.this);
            builder.setMessage("Are you sure you want to Delete All Item?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            deleteALL();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked "No," so dismiss the dialog
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }
    private void deleteALL(){
        Toast.makeText(this, "All Item Deleted", Toast.LENGTH_SHORT).show();
        databaseHelper.deleteAllData();
        startActivity(new Intent(this,SavedDataListActivity.class));
        finish();
    }
}
