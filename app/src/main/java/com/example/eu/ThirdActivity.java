//For adding new food
package com.example.eu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * @author Anika
 *Added Food Items in Database
 */
public class ThirdActivity extends AppCompatActivity
{

    EditText newName;
    EditText newPrice;
    Button save;
    Food food;
    DatabaseReference ref;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfood);

        newName = findViewById(R.id.newName);
        newPrice = findViewById(R.id.newPrice);
        food = new Food();
        save = findViewById(R.id.edit);

        ref = FirebaseDatabase.getInstance().getReference().child("Food");


        save.setOnClickListener(new View.OnClickListener()
        {
            /**
             *Notification for adding food
             * @param v
             */
            @Override
            public void onClick(View v)
            {
//Inserting Data Into Firebase
                food.setName(newName.getText().toString().trim());
                food.setPrice(newPrice.getText().toString().trim());

                ref.push().setValue(food);
                Toast.makeText(ThirdActivity.this,"Data Inserted Successfully",Toast.LENGTH_LONG).show();

            }
        });
    }
}

