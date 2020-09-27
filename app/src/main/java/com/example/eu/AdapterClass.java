package com.example.eu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Activity generated from SearchActivity
 * @author Mrittika, Anika
 * view,image fetch data from firebase,and shows separate data in separate cart view
 */
public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder>
{

    ArrayList<Food> list;
    public AdapterClass(ArrayList<Food> list)
    {
        this.list =list;
    }


    /**
     *Inserting to cardholder
     * @param viewGroup
     * @param i
     * @return View of the card holder
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_holder,viewGroup,false);
        return new MyViewHolder(view);
    }


    /**
     * Data fetching to View from firebase
     * @param myViewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull AdapterClass.MyViewHolder myViewHolder, int i)
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
        ImageView addCart;


        /**
         *Here we are declining the three things to view in cardholder
         * @param itemView
         */
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.newName);
            price = itemView.findViewById(R.id.newPrice);
            addCart = itemView.findViewById(R.id.addCart);

        }
    }
}
