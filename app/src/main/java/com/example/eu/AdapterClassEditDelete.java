package com.example.eu;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

/**
 *It is generated from edit delete activity
 * @author Anika

 */
public class AdapterClassEditDelete extends RecyclerView.Adapter<AdapterClassEditDelete.MyViewHolder>
{

    ArrayList<Food> list;
    Food food;
    public AdapterClassEditDelete(ArrayList<Food> list)
    {
        this.list =list;
    }


    /**
     *When it got any food name or price it will show us the view
     * @param viewGroup
     * @param i
     * @return the view of edit,delete
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.edit_delete_food,viewGroup,false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull AdapterClassEditDelete.MyViewHolder myViewHolder,int i)
    {
        myViewHolder.name.setText(list.get(i).getName());
        myViewHolder.price.setText(list.get(i).getPrice());

    }



    @Override
    public int getItemCount()
    {
        return list.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView price;
        ImageView edit;
        ImageView delete;

        /**
         * Here we are declining the 4 things to view in cardholder
         * @param itemView
         */
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);

        }
    }
}

