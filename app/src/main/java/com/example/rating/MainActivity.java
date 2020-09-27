package com.example.rating;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * The rating part of this application
 * @author Samiha Samsi
 * @verion 1.0
 * After successful payment and delivery this screen will pop up
 */

public class MainActivity extends AppCompatActivity {
    /**
     * TextView for users to see
     */
    TextView rateCount, showRating;
    /**
     * review object
     */
    EditText review;
    Button submit;
    /**
     * ratingBar object
     */
    RatingBar ratingBar;
    /**
     *@see "float"
     */

    float rateValue;
    /**
     * @see "String"
     */
    String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rateCount = findViewById(R.id.rateCount);
        ratingBar = findViewById(R.id.ratingBar);
        review = findViewById(R.id.review);
        submit = findViewById(R.id.submitBtn);
        showRating = findViewById(R.id.showRating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            /**
             * This method gets called while using bars to rate the food.
             */
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rateValue = ratingBar.getRating();
                if (rateValue<=1 && rateValue>0)
                    rateCount.setText("Bad "+ rateValue + "/5");
                else if(rateValue<=2 && rateValue>1)
                    rateCount.setText("OK "+ rateValue + "/5");
                else if(rateValue<=3 && rateValue>2)
                    rateCount.setText("Good "+ rateValue + "/5");
                else if(rateValue<=4 && rateValue>3)
                    rateCount.setText("Very Good "+ rateValue + "/5");
                else if(rateValue<=5 && rateValue>4)
                    rateCount.setText("Best "+ rateValue + "/5");

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * This method gets called the rating that customer has rated.
             */
            public void onClick(View v) {
                temp = rateCount.getText().toString();
                showRating.setText("Your Rating: \n"+temp+"\n" +review.getText());
                review.setText("");
                ratingBar.setRating(0);
                rateCount.setText("");
            }
        });

    }
}