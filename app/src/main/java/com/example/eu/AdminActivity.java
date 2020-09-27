package com.example.eu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Admin verification to admin activity
 * @author Anika
 * Shows 2 buttons by which we can addfood ,edtfood to database
 */
public class AdminActivity extends AppCompatActivity
{

    Button addFood;
    Button edFood;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        addFood = findViewById(R.id.addFood);
        edFood = findViewById(R.id.edFood);
//Third Activity java files basically add new food items
        addFood.setOnClickListener(new View.OnClickListener()
        {


            /**
             * Takes to the new activity name ThirdActivity
             * @param v
             */
            @Override
            public void onClick(View v)
            {
                Intent sharedIntent = new Intent(AdminActivity.this, ThirdActivity.class);
                startActivity(sharedIntent);
            }
        });



        edFood.setOnClickListener(new View.OnClickListener()
        {


            /**
             *Takes to the new activity name EditDeleteActivity
             * @param v
             */
            @Override
            public void onClick(View v)
            {
                Intent sharedIntent = new Intent(AdminActivity.this, EditDeleteActivity.class);
                startActivity(sharedIntent);
            }
        });
    }
}