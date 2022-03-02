package com.example.carpoolbuddy;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Adapter for recycler view in VehiclesInfoActivity
 * User should be able to click on Recycler view and be brought to corresponding Vehicle profile
 *
 * @author Myriam Lynch
 * @version 1.0
 */

public class VehicleAdapter extends RecyclerView.Adapter<VehicleViewHolder>
{
    //declare variables
    private ArrayList<String> type;
    private ArrayList<String> model;
    private ArrayList<String> cap;
    private ArrayList<String> price;
    private ArrayList<String> id;
    private ArrayList<String> status;
    private ArrayList<String> electric;
    private Context context;

    public VehicleAdapter(Context c, ArrayList<String> data1, ArrayList<String> data2,
                          ArrayList<String> data3, ArrayList<String> data4, ArrayList<String> data5,
                          ArrayList<String> data6, ArrayList<String> data7)
    {
        type = data1;
        model = data2;
        cap = data3;
        price = data4;
        id = data5;
        context= c;
        status = data6;
        electric = data7;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cis_row_view, parent, false);
        VehicleViewHolder holder = new VehicleViewHolder(myView);
        return holder;
    }

    @Override
    /**
     * Method to populate recycler view with information
     * @param VehicleViewHolder object to be used to set text, integer to loop through array of data
     */
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position)
    {
        //populate recylerview with information
        holder.typeText.setText(type.get(position));
        holder.modelText.setText(model.get(position));
        holder.capText.setText(cap.get(position) + " seats left");
        holder.priceText.setText(price.get(position) + "$");
        int i = position;
        boolean e;
        if(electric.get(position).equals("true"))
            e = true;
        else
            e = false;

        //when user clicks on view holder, navigate to corresponding vehicles profile
        holder.getLayout().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VehicleProfileActivity.class);
                Vehicle v = new Vehicle(id.get(i), model.get(i), type.get(i),
                        Integer.valueOf(cap.get(i)), Double.valueOf(price.get(i)), status.get(i), e);
                System.out.println(v);
                intent.putExtra("vehicle", v);
                context.startActivity(intent);
            }
        });
    }

    @Override
    /**
     * Method to return the number of items in recycler view
     * @return number of rows in recycler view
     */
    public int getItemCount()
    {
        return type.size();
    }

    /**
     * Method to update data if data changes
     * @param 7 array list of strings to update current arraylists
     */
    public void setData(ArrayList<String> data1, ArrayList<String> data2,
                        ArrayList<String> data3, ArrayList<String> data4,
                        ArrayList<String> data5, ArrayList<String> data6, ArrayList<String> data7)
    {
        type = data1;
        model = data2;
        cap = data3;
        price = data4;
        id = data5;
        status = data6;
        electric = data7;
    }
}
