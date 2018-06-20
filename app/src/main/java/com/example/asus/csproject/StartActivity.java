package com.example.asus.csproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asus.csproject.translation.Language;

public class StartActivity extends AppCompatActivity {

    private Button buttonEng, buttonPl, buttonGer;
    private Language language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getUIElements();
        setListeners();
    }

    private void getUIElements(){

        buttonEng = findViewById(R.id.buttonEnglish);
        buttonPl = findViewById(R.id.buttonPolish);
        buttonGer = findViewById(R.id.buttonGerman);
    }

    private void setListeners(){
        buttonEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language = Language.ENGLISH;
                startMainActivity();
            }
        });

        buttonPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language = Language.POLISH;
                startMainActivity();
            }
        });

        buttonGer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language = Language.GERMAN;
                startMainActivity();
            }
        });
    }


    private void startMainActivity(){
        if(language != null){
            Intent i = new Intent(StartActivity.this, MainActivity.class);
            i.putExtra("language", language.toString());
            startActivity(i);
        }
    }
}
