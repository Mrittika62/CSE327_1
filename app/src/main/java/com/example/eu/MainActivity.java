package com.example.eu;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * The MainActivity for the Application
 * @author Mrittika
 * This is the first screen the user sees
 */
public class MainActivity extends AppCompatActivity
{


    /**
     * Button for user to getStarted
     */
    Button getStarted;

   //default function
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getStarted = findViewById(R.id.getStarted);


        getStarted.setOnClickListener(new View.OnClickListener()
        {
            /**
             * Opens the next activity
             * @see SignInActivity
             * @param v
             */
           @Override
           public void onClick(View v)
           {
               Intent sharedIntent = new Intent(MainActivity.this, SignInActivity.class);
               startActivity(sharedIntent);
           }
       });
    }
}
