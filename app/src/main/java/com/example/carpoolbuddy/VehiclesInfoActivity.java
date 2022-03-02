package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * Activity to display recyler view of all open vehicles
 *
 * @author Myriam Lynch
 * @version 1.0
 */

public class VehiclesInfoActivity extends AppCompatActivity
{
    //declare variables
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private FirebaseUser currUser;
    private RecyclerView recView;
    private ArrayList<String> types;
    private ArrayList<String> models;
    private ArrayList<String> capacities;
    private ArrayList<String> prices;
    private ArrayList<String> ids;
    private ArrayList<String> status;
    private ArrayList<String> electric;
    private VehicleAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_info);

        //initialize variables
        mAuth = FirebaseAuth.getInstance();
        currUser =  mAuth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
        recView = findViewById(R.id.recView);
        types = new ArrayList<String>();
        models = new ArrayList<String>();
        capacities = new ArrayList<String>();
        prices = new ArrayList<String>();
        ids = new ArrayList<String>();
        status = new ArrayList<String>();
        electric = new ArrayList<String>();

        //send information to recyclerview
        myAdapter = new VehicleAdapter(this, types, models, capacities, prices,
                ids, status, electric);
        recView.setAdapter(myAdapter );
        recView.setLayoutManager(new LinearLayoutManager(this));

        getAndPopulateData();
    }

    /**
     * Method to update recycler view with new information
     */
    public void getAndPopulateData()
    {
        types = new ArrayList<String>();
        models = new ArrayList<String>();
        capacities = new ArrayList<String>();
        prices = new ArrayList<String>();
        electric = new ArrayList<String>();

        //get information from firebase
        firestore.collection("vehicles").get().addOnCompleteListener
                (new OnCompleteListener<QuerySnapshot>()
                {
           @Override
           public void onComplete(@NonNull Task<QuerySnapshot> task) {
               for (DocumentSnapshot ds : task.getResult().getDocuments())
               {
                    String result = "" + ds;
                    int tIntStart = result.indexOf("vehicleType");
                    int tIntEnd = result.indexOf("version");
                    int mIntStart = result.indexOf("model");
                    int mIntEnd = result.indexOf("open");
                    int cIntStart = result.indexOf("capacity");
                    int cIntEnd = result.indexOf("electric");
                    int pIntStart = result.indexOf("basePrice");
                    int pIntEnd = result.indexOf("capacity");
                    int iIntStart = result.indexOf("vehicleID");
                    int iIntEnd = result.indexOf("vehicleType");
                    int sIntStart = result.indexOf("open");
                    int sIntEnd = result.indexOf("owner");
                    int eIntStart = result.indexOf("electric");
                    int eIntEnd = result.indexOf("model");

                    String type = result.substring(tIntStart + 13, tIntEnd - 5);
                    String model = result.substring(mIntStart + 7, mIntEnd - 4);
                    String capacity = result.substring(cIntStart + 10, cIntEnd - 4);
                    String price = result.substring(pIntStart + 11, pIntEnd - 4);
                    String id = result.substring(iIntStart + 11, iIntEnd - 4);
                    String s = result.substring(sIntStart+ 6, sIntEnd - 4);
                    String e = result.substring(eIntStart + 10, eIntEnd - 4);

                    //if the vehicle is open, save information in arraylist for recyclerview
                    if(s.equals("true"))
                    {
                        status.add(s);
                        types.add(type);
                        models.add(model);
                        capacities.add(capacity);
                        prices.add(price);
                        ids.add(id);
                        electric.add(e);
                    }

                    //update recyclerview
                    myAdapter.setData(types, models, capacities, prices, ids, status, electric);
                    myAdapter.notifyDataSetChanged();
               }
           }
       });
    }

    /**
     * Method to allow user to navigate to AddVehicle activity
     * @param v, a View object to allow method to be called on button click
     */
    public void goToAddVehicle(View v)
    {
        Intent intent = new Intent(this, AddVehicle.class);
        startActivity(intent);
    }
}