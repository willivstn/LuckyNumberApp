package com.willi_vstn.luckynumberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Activity2 extends AppCompatActivity {

    TextView welcomeTxt, luckyNumberTxt;
    Button share_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        welcomeTxt = findViewById(R.id.textView2);
        luckyNumberTxt = findViewById(R.id.lucky_number_txt);
        share_btn = findViewById(R.id.share_number_btn);

        //Username
        Intent i = getIntent();
        String userName = i.getStringExtra("name");

        //random number generated
        int random_num = getRandomNum();

        luckyNumberTxt.setText(""+random_num);
        
        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(userName, random_num);
            }
        });

    }

    public int getRandomNum(){

        Random random = new Random();
        int upper_limit = 1000;

        int randomNumber = random.nextInt(upper_limit);
        return randomNumber;

    }
    
    public void shareData(String username, int randomNum){

        //Implicit Intents
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        //Convert the int to string
        String number = String.valueOf(randomNum);

        i.putExtra(Intent.EXTRA_SUBJECT, "LUCKY NUMBER");
        i.putExtra(Intent.EXTRA_TEXT,username+"'s lucky number is: " + number+"!");

        startActivity(Intent.createChooser(i, "Choose a platform"));
        
    }
}