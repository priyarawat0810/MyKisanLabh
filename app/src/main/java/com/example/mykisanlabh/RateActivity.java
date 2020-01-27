package com.example.mykisanlabh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class RateActivity extends AppCompatActivity {
    RatingBar ratingBar;
    Button ratesubmit;

    protected void init(){
        ratingBar = findViewById(R.id.ratingBar);
        ratesubmit = findViewById(R.id.submitrate);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        init();
        ratesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rating = String.valueOf(ratingBar.getRating());
                Toast.makeText(getApplicationContext(),rating, Toast.LENGTH_LONG).show();
            }
        });
    }
}
