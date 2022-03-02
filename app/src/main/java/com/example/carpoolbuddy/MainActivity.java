package com.example.carpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * This Activity is the home page of the app. It allows the user to navigate to other app functions.
 *
 * @author Myriam Lynch
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity
{
    //declare variables
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize variables
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currUser =  mAuth.getCurrentUser();
    }

    /**
     * Method to allow the User to navigate to ProfileActivity
     * @param v, a View object to allow method to be called on button click
     */
    public void goToProfile(View v)
    {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    /**
     * Method to allow User to navigate to VehiclesInfoActivity
     * @param v, a View object to allow method to be called on button click
     */
    public void goToVehicles(View v)
    {
        Intent intent = new Intent(this, VehiclesInfoActivity.class);
        startActivity(intent);
    }

    /**
     * Method to allow User to navigate to AddVehicle Activity
     * @param v, a View object to allow method to be called on button click
     */
    public void addVehicle(View v)
    {
        Intent intent = new Intent(this, AddVehicle.class);
        startActivity(intent);
    }
}