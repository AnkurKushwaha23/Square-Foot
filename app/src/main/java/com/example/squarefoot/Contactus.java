package com.example.squarefoot;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Contactus extends AppCompatActivity {
    Button btnMail;
    ImageView imgLink,imgX,imgGithub;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        imgLink = findViewById(R.id.imgLink);
        imgX = findViewById(R.id.imgX);
        imgGithub = findViewById(R.id.imgGithub);
        btnMail = findViewById(R.id.btnMail);
        btnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imail = new Intent(Intent.ACTION_SEND);
                imail.setType("message/rfc822");
                imail.putExtra(Intent.EXTRA_EMAIL,new String[]{"ankursenpai@gmail.com"});
                imail.putExtra(Intent.EXTRA_SUBJECT,"Queries");
                imail.putExtra(Intent.EXTRA_TEXT,"This App is Awesome!");
                startActivity(Intent.createChooser(imail,"Email via"));
                Toast.makeText(Contactus.this, "Thanks for Contacting us", Toast.LENGTH_SHORT).show();
            }
        });
        imgLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appLink = "https://www.linkedin.com/in/ankur-kushwaha-818791248?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app";
                String packageName ="com.linkedin.android";
                String webLink ="https://www.linkedin.com/in/ankur-kushwaha-818791248?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app";
                openLink(appLink,packageName,webLink);
            }
        });
        imgX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appLink = "https://x.com/AnkurKushwaha23?t=K6GrpfTsLnfWb4JQ51pPhQ&s=03";
                String packageName ="com.twitter.android.";
                String webLink ="https://x.com/AnkurKushwaha23?t=K6GrpfTsLnfWb4JQ51pPhQ&s=03";
                openLink(appLink,packageName,webLink);
            }
        });
        imgGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appLink = "https://github.com/AnkurKushwaha23";
                String packageName ="com.github.mobile.";
                String webLink ="https://github.com/AnkurKushwaha23";
                openLink(appLink,packageName,webLink);
            }
        });
    }
    private void openLink(String applink, String packageName, String webLink){
        try {
            Uri uri = Uri.parse(applink);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage(packageName);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException activityNotFoundException){
            Uri uri = Uri.parse(webLink);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}