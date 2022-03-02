package com.example.carpoolbuddy;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

/**
 * View Holder for Recycler View in VehiclesInfoActivity
 *
 * @author Myriam Lynch
 * @version 1.0
 */

public class VehicleViewHolder extends RecyclerView.ViewHolder
{
    protected TextView typeText;
    protected TextView modelText;
    protected TextView priceText;
    protected TextView capText;

    public VehicleViewHolder(@NonNull View itemView)
    {
        super(itemView);

        typeText = itemView.findViewById(R.id.typeTextView);
        modelText = itemView.findViewById(R.id.modelTextView);
        priceText = itemView.findViewById(R.id.priceTextView);
        capText = itemView.findViewById(R.id.capacityTextView);
    }

    public ConstraintLayout getLayout()
    {
        return itemView.findViewById(R.id.rowLayout);
    }
}
