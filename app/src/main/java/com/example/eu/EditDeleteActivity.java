package com.example.eu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Came from Admin activity to find the item we want to edit or delete
 * @author Anika
 */
public class EditDeleteActivity extends AppCompatActivity
{

    DatabaseReference ref;
    ArrayList<Food> list;
    RecyclerView recyclerView;
    SearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete);

        ref = FirebaseDatabase.getInstance().getReference().child("Food");
        recyclerView = findViewById(R.id.rv2);
        searchView = findViewById(R.id.searchView);

    }



    @Override
    protected void onStart()
    {
        super.onStart();

        if(ref != null)
        {
            ref.addValueEventListener(new ValueEventListener()
            {



                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    if(dataSnapshot.exists())
                    {
                        list = new ArrayList<>();
                        for(DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            list.add(ds.getValue(Food.class));
                        }
                        AdapterClassEditDelete adapterClassEditDelete = new AdapterClassEditDelete(list);
                        recyclerView.setAdapter(adapterClassEditDelete);
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError)
                {
                }
            });
        }

        if (searchView !=null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
            {

                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }



                @Override
                public boolean onQueryTextChange(String s)
                {
                    Search(s);
                    return true;
                }
            });
        }
    }


    /**
     *We can search by price or food name
     * @param str
     */
    private void Search(String str)
    {
        ArrayList<Food> myList = new ArrayList<>();
        for(Food object:list)
        {
            //Search by Food Name
            if(object.getName().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
            //Search by Price
            if(object.getPrice().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
        }

// Adapter class Edit Delete is basically new adapter class in where I have added two new buttons (edit and delete)
// which can be viewed by the user
        AdapterClassEditDelete adapterClassEditDelete = new AdapterClassEditDelete(myList);
        recyclerView.setAdapter(adapterClassEditDelete);

    }


}
