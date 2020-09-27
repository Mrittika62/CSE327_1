package com.example.eu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * @author Mrittika,Anika,Samsi
 *next activity with user name and password admit to edittext
 */
public class AdminVerification extends AppCompatActivity
{

    private EditText musername;
    private EditText mpassword;

    private Button adminSignInBtn;
    private ProgressBar adminProgressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_verification);

        musername = findViewById(R.id.aUsername);
        mpassword = findViewById(R.id.aPassword);

        adminSignInBtn = findViewById(R.id.adminSignInBtn);

        adminProgressBar = findViewById(R.id.admin_loading_progressBar);


        adminSignInBtn.setOnClickListener(new View.OnClickListener()
        {
            /**
             *checking the user name or password
             * shows notification bar
             * @param v
             */
            @Override
            public void onClick(View v)
            {
                if(isEmpty()) return;
                inProgress(true);
                if(musername.getText().toString().equals("as") && mpassword.getText().toString().equals("as"))
                {
                    Intent sharedIntent = new Intent(AdminVerification.this, AdminActivity.class);
                    startActivity(sharedIntent);
                }
                else
                    {
                    inProgress(false);
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
            }
        });

    }



    private void inProgress(boolean x)
    {
        if(x)
        {
            adminProgressBar.setVisibility(View.VISIBLE);
            adminSignInBtn.setEnabled(false);

        }
        else
            {
            adminProgressBar.setVisibility(View.GONE);
            adminSignInBtn.setEnabled(true);
            }
    }


    /**
     *Shows Notification bar if the name or password not given
     */

    private boolean isEmpty()
    {
        if(TextUtils.isEmpty(musername.getText().toString()))
        {
            musername.setError("Required!");
            return true;
        }
        if(TextUtils.isEmpty(mpassword.getText().toString()))
        {
            mpassword.setError("Required!");
            return true;
        }
        return false;
    }
}