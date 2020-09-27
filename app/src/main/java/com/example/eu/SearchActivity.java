package com.example.eu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 *Next activity where we can search the food
 * @author Mrittika
 */
public class SearchActivity extends AppCompatActivity
{

    DatabaseReference ref;
    ArrayList<Food> list;
    RecyclerView recyclerView;
    SearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ref = FirebaseDatabase.getInstance().getReference().child("Food");
        recyclerView = findViewById(R.id.rv);
        searchView = findViewById(R.id.searchView);
    }


    /**
     * Fetching data and run the process through the recycler view
     */
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

                        /**
                         * Call the AdapterClass
                         */
                        AdapterClass adapterClass = new AdapterClass(list);
                        recyclerView.setAdapter(adapterClass);
                    }
                }


                /**
                 *If dont get any data from firebase then it will show nothing
                 * @param databaseError
                 */

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


                /**
                 *Calling the search function
                 * @param s
                 * @return if there any food item or not
                 */

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
     *Name or price searching with any case letter
     * User can search both by food name and price (Upper case and lower case not vary)
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
            //Search by price
            if(object.getPrice().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
        }
        AdapterClass adapterClass = new AdapterClass(myList);
        recyclerView.setAdapter(adapterClass);
    }

}



