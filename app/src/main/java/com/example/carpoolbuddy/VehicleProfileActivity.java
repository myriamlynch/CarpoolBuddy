package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * Activity to display the information of selected vehicle
 *
 * @author Myriam Lynch
 * @version 1.0
 */

public class VehicleProfileActivity extends AppCompatActivity
{
    //declare variables
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private Vehicle vehicleInfo;
    private FirebaseFirestore firestore;
    private Button bookButton;
    private Button openButton;
    private String email;
    private String id;
    private TextView typeText;
    private TextView modelText;
    private TextView capText;
    private TextView priceText;
    private TextView statusText;
    private TextView statusTitleText;
    private TextView electricText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);

        //initialize variables
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();
        bookButton = (Button) findViewById(R.id.button5);
        openButton = (Button) findViewById(R.id.button6);
        typeText = findViewById(R.id.typeText);
        modelText = findViewById(R.id.modelText);
        priceText = findViewById(R.id.priceText);
        capText = findViewById(R.id.capText);
        statusText = findViewById(R.id.statusTextView);
        statusTitleText = findViewById(R.id.textView14);
        electricText = findViewById(R.id.electricText);
        email = user.getEmail();

        //get information from previous page and set update page accordingly
        vehicleInfo = (Vehicle) getIntent().getExtras().get("vehicle");
        id = vehicleInfo.getVehicleID();
        typeText.setText(vehicleInfo.getVehicleType());
        modelText.setText(vehicleInfo.getModel());
        priceText.setText(String.valueOf(vehicleInfo.getBasePrice()));
        capText.setText(String.valueOf(vehicleInfo.getCapacity()));

        //update UI accordingly if user owns vehicle or not
        setUpButton();
    }

    /**
     * Method to set up screen:
     * - only show "book ride" button if not owner
     * - show price depending on if user is Student or Teacher (50% discount)
     */
    public void setUpButton()
    {
        /* only show book ride button if not owner
        show correct price
        teachers and student --> half price
        alumni and parent --> full price */
        firestore.collection("users").whereEqualTo("email", email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot ds : task.getResult().getDocuments())
                {
                    String vehicles = "" + ds.get("ownedVehicles");

                    if(vehicles.contains(vehicleInfo.getVehicleID()))
                    {
                        if(vehicleInfo.isOpen())
                        {
                            openButton.setText("Close");
                            statusText.setText("open");
                        }
                        else
                            {
                                openButton.setText("Open");
                                statusText.setText("closed");
                            }
                        statusText.setVisibility(View.VISIBLE);
                        statusTitleText.setVisibility(View.VISIBLE);
                        openButton.setVisibility(View.VISIBLE);
                    }
                    else
                        {
                            bookButton.setVisibility(View.VISIBLE);
                        }
                    if(ds.get("userType").equals("student") || ds.get("userType").equals("teacher"))
                    {
                        double p = vehicleInfo.getBasePrice()/2;
                        String str = "" + p;
                        priceText.setText(str);
                    }
                    else
                        {
                            double p = vehicleInfo.getBasePrice();
                            String str = "" + p;
                            priceText.setText(str);
                        }
                    if(vehicleInfo.getCapacity() < 1)
                    {
                        bookButton.setVisibility(View.INVISIBLE);
                        capText.setText("FULL");
                    }

                    if(!vehicleInfo.isOpen())
                    {
                        bookButton.setVisibility(View.INVISIBLE);
                        statusText.setText("closed");
                        statusText.setVisibility(View.VISIBLE);
                        statusTitleText.setVisibility(View.VISIBLE);
                    }

                    if(vehicleInfo.isElectric())
                        electricText.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * Method to close ride if ride is open, or open ride if ride is closed
     * Set up buttons accordingly
     * @param v, a View object to allow method to be called on button click
     */
    public void closeRide(View v)
    {
        /* if ride is open, display close button
         else, display open button */
        if(vehicleInfo.isOpen())
        {
            firestore.collection("vehicles").document(vehicleInfo.getVehicleID()).update("open", false );
            statusText.setText("closed");
            vehicleInfo.setOpen(false);
            openButton.setText("Open");
        }
        else
            {
                firestore.collection("vehicles").document(vehicleInfo.getVehicleID()).update("open", true );
                statusText.setText("open");
                vehicleInfo.setOpen(true);
                openButton.setText("Close");

            }
    }

    /**
     * Method to book ride:
     * - decrease capacity by 1
     * - add user ID to arraylist of rider IDs
     * - add points to User depending on if vehicle is electric or not
     * - update firebase
     * @param v, a View object to allow method to be called on button click
     */
    public void bookRide(View v)
    {
        /* reduce capacity by 1
        add user id to arraylist of riderIDs
        add points depending on if vehicle is electric */
        firestore.collection("vehicles").document(vehicleInfo.getVehicleID()).update("ridersUIDs", FieldValue.arrayUnion(email));

        vehicleInfo.setCapacity(vehicleInfo.getCapacity() -1);
        firestore.collection("vehicles").document(vehicleInfo.getVehicleID()).update("capacity", vehicleInfo.getCapacity());
        capText.setText(String.valueOf(vehicleInfo.getCapacity()));


        firestore.collection("users").whereEqualTo("email", email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot ds : task.getResult().getDocuments())
                {
                    String str = "" + ds.get("points");
                    int points = Integer.valueOf(str);

                    if(vehicleInfo.isElectric())
                    {
                        firestore.collection("users").document(email).update("points", points + 10);
                    }
                    else
                        {
                            firestore.collection("users").document(email).update("points", points + 1);
                        }

                }
            }
        });

        if(vehicleInfo.getCapacity() < 1)
        {
            bookButton.setVisibility(View.INVISIBLE);
            capText.setText("FULL");
        }
    }

}