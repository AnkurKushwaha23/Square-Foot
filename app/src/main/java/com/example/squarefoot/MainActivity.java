package com.example.squarefoot;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private EditText editTextLength;
    private EditText editTextWidth;
    private EditText editTextUnitPrice;
    private Button buttonCalculate;
    private TextView textViewResult;
    private Button buttonClear;
    private Button buttonSave;
    private Button buttonHistory;
    double numericValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolMain);
        setSupportActionBar(toolbar);
        dbHelper = new DatabaseHelper(this);

        // Initialize the UI elements in the onCreate method
        editTextLength = findViewById(R.id.editTextLength);
        editTextWidth = findViewById(R.id.editTextWidth);
        editTextUnitPrice = findViewById(R.id.editTextUnitPrice);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
        buttonClear = findViewById(R.id.buttonClear);
        buttonSave = findViewById(R.id.saveDoc);
        buttonHistory = findViewById(R.id.buttonViewSavedData);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform calculation
                try {
                    double length = Double.parseDouble(editTextLength.getText().toString());
                    double width = Double.parseDouble(editTextWidth.getText().toString());
                    double unitPrice = Double.parseDouble(editTextUnitPrice.getText().toString());


                    double totalCost = length * width * unitPrice;
                    textViewResult.setText("Total : ₹" + totalCost);
                    String inputString = String.valueOf(totalCost);
                    String numericPart = inputString.replaceAll("[^\\d.]", ""); // Removes non-numeric characters
                    numericValue = Double.parseDouble(numericPart);

                } catch (NumberFormatException e) {
//                    textViewResult.setText("Please enter valid measurement.");
                    showToast("Please enter valid measurement.");
                }
            }
        });
        // Clear Button Click Listener
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFeild();
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the result is calculated
                if (textViewResult.getText().toString().equals("Total :")) {
                    showToast("Please calculate the result before saving.");
//                    Toast.makeText(MainActivity.this, "Please calculate the result before saving.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Get current date and time
                String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

                String length = editTextLength.getText().toString();
                String width = editTextWidth.getText().toString();
                String unitPrice = editTextUnitPrice.getText().toString();

                String result = String.valueOf(numericValue);

//                // Save the data in SQLite
                dbHelper.saveData(currentDate, length, width, unitPrice, result);

                showToast("Data Saved.");
                clearFeild();
            }
        });
        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the SavedDataListActivity
                Intent intent = new Intent(MainActivity.this, SavedDataListActivity.class);
                startActivity(intent);
            }
        });
    }
    public  void showToast(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,findViewById(R.id.custom_toast));
        TextView text = layout.findViewById(R.id.textToast);
        text.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
    public void alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish(); 
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.m_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.contactSupport){
            startActivity(new Intent(this, Contactus.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void clearFeild(){
        // Clear all text fields
        editTextLength.getText().clear();
        editTextWidth.getText().clear();
        editTextUnitPrice.getText().clear();
        textViewResult.setText("Total :");
    }
    @Override
    public void onBackPressed() {
        alert();
    }
}
