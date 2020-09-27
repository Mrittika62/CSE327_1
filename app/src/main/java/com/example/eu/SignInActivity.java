package com.example.eu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A SignInActivity take email password as input from user for signin purpose
 * @author Mrittika
 * Screen that displays button of signInBtn,registerBtn,madminBtn
 * Screen that displays progressBar,firebase auth
 */
public class SignInActivity extends AppCompatActivity
{

    private EditText email;
    private EditText password;

    private Button signInBtn;
    private Button registerBtn;
    private Button madminBtn;

    private ProgressBar progressBar;
    private FirebaseAuth auth;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        signInBtn = findViewById(R.id.signInBtn);
        registerBtn = findViewById(R.id.registerBtn);
        madminBtn = findViewById(R.id.adminBtn);

        progressBar = findViewById(R.id.loading_progressBar);


        signInBtn.setOnClickListener(new View.OnClickListener()

        {
            /**
             *Checking the email and password
             * @param v
             */
            @Override
            public void onClick(View v)
            {
                if(isEmpty()) return;
                inProgress(true);
                auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>()
                        {


                            /**
                             *If the email and password are correct then it will go to the SearchActivity
                             * @param authResult
                             */
                            @Override
                            public void onSuccess(AuthResult authResult)
                            {
                                Toast.makeText(SignInActivity.this, "User Signed In", Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(SignInActivity.this, SearchActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();return;
                            }
                        }).addOnFailureListener(new OnFailureListener()
                {


                    /**
                     *Otherwise fail to sign in by firebase checking
                     * it will notify
                     * @param e
                     */
                    @Override
                            public void onFailure(@NonNull Exception e)
                    {
                                inProgress(false);
                                Toast.makeText(SignInActivity.this, "Failed to Signed in"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener()
        {
            /**
             * Save the email and password in firebase as a new user
             * @param v
             */
            @Override
            public void onClick(View v)
            {
                if(isEmpty()) return;
                inProgress(true);

                auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>()
                        {
                            /**
                             *Notification for registration
                             * @param authResult
                             */
                            @Override
                            public void onSuccess(AuthResult authResult)
                            {
                                Toast.makeText(SignInActivity.this, "User registered", Toast.LENGTH_SHORT).show();
                                inProgress(false);

                            }
                        }).addOnFailureListener(new OnFailureListener()
                {


                    /**
                     * Notification for registration failed
                     * @param e
                     */

                    @Override
                            public void onFailure(@NonNull Exception e)
                    {
                                inProgress(false);
                                Toast.makeText(SignInActivity.this, "registration failed"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        madminBtn.setOnClickListener(new View.OnClickListener()
        {
            /**
             *This will take us to Admin Verification portal
             * @param v
             */
            @Override
            public void onClick(View v)
            {
               /* finish();return;*/
                Intent sharedIntent = new Intent(SignInActivity.this, AdminVerification.class);
                startActivity(sharedIntent);
            }
        });
    }


    /**
     * Shows Progress icon
     * @param x
     */

    private void inProgress(boolean x)
    {
        if(x)
        {
            progressBar.setVisibility(View.VISIBLE);
            madminBtn.setEnabled(false);
            signInBtn.setEnabled(false);
            registerBtn.setEnabled(false);
        }
        else
            {
            progressBar.setVisibility(View.GONE);
            madminBtn.setEnabled(true);
            signInBtn.setEnabled(true);
            registerBtn.setEnabled(true);
            }
    }


    /**
     * If one of email or password  is missing it will notify
     * @return
     */


    private boolean isEmpty()
    {
       if(TextUtils.isEmpty(email.getText().toString()))
       {
           email.setError("Required!");
           return true;
       }
        if(TextUtils.isEmpty(password.getText().toString()))
        {
            password.setError("Required!");
            return true;
        }
        return false;
    }

}