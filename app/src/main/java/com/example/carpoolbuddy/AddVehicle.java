package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This activity provides methods to create and add a vehicle to the database
 *
 * @author Myriam Lynch
 * @version 1.0
 */

public class AddVehicle extends AppCompatActivity
{

    //initialize instance variables
    private FirebaseFirestore firestoreRef;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private EditText inputView1;
    private EditText inputView2;
    private EditText inputView3;
    private EditText inputView4;
    private EditText inputView5;
    private EditText inputView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        firestoreRef = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        inputView1 = (EditText) findViewById(R.id.owner);
        inputView2 = (EditText)findViewById(R.id.model);
        inputView3 = (EditText)findViewById(R.id.capacity);
        inputView4 = (EditText)findViewById(R.id.type);
        inputView5 = (EditText)findViewById(R.id.price);
        inputView6 = (EditText)findViewById(R.id.electric);
    }

    //method to check that all information needed is inputed by user
    //should return false if some fields are empty

    /**
     * method to check that all information needed is inputed by user
     * @return boolean indicating if all TextViews on page have been fillled
     */
    public boolean formValid()
    {
        //get input from all EditTexts
        String owner = "";
        String model = "";
        String capacity = "";
        String type = "";
        String price = "";
        String electric = "";
        if(!(inputView1.getText().toString() == null))
            owner = inputView1.getText().toString();
        if(!(inputView2.getText().toString() == null))
            model = inputView2.getText().toString();
        if(!(inputView3.getText().toString() == null))
            capacity = inputView3.getText().toString();
        if(!(inputView4.getText().toString() == null))
            type = inputView4.getText().toString();
        if(!(inputView5.getText().toString() == null))
            price = inputView5.getText().toString();
        if(!(inputView6.getText().toString() == null))
            electric = inputView6.getText().toString();

        //if EditTexts are empty, return false, otherwise return true
        if(!owner.equals("") && !model.equals("") && !type.equals("") && !capacity.equals("")
                && !price.equals("") && !electric.equals(""))
        {
            return true;
        }
        return false;
    }


    /**
     * method that is called by buttonclick
     * creates new vehicle and saves it in firebase
     * @param v, a View object to allow method to be called on button click
     */
    public void addNewVehicle(View v)
    {
        //if editText are all filled, create vehicle
        if(!formValid())
        {
            Toast messageToUser = Toast.makeText(this, "Please try again",
                    Toast.LENGTH_LONG);
            messageToUser.show();
        }
        else
            {
                //get information from EditText needed to create vehicle
                String owner = mUser.getEmail();
                String model = inputView2.getText().toString();
                String capacity = inputView3.getText().toString();
                int cap = Integer.valueOf(capacity);
                String type = inputView4.getText().toString();
                String price = inputView5.getText().toString();
                double p = Double.valueOf(price);
                String uid = UUID.randomUUID().toString();
                ArrayList<String> users = new ArrayList<>();
                String elec = inputView6.getText().toString();
                boolean electric;
                if(elec.equals("yes"))
                    electric = true;
                else
                    electric = false;

                //create vehicle
                Vehicle newVs = new Vehicle(owner, model, cap, uid, true, type, p, users, electric);

                //save vehicle ID in arraylist of vehicles of user
                firestoreRef.collection("users").document(mUser.getEmail())
                        .update("ownedVehicles", FieldValue.arrayUnion(newVs.getVehicleID()))
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            System.out.println("HOORAY");
                        }
                        else
                            {
                                System.out.println("OH NO!");
                                System.out.println(task.getException().toString());
                            }
                    }
                });

                //save vehicle in firebase
                firestoreRef.collection("vehicles").document(newVs.getVehicleID())
                        .set(newVs).addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            System.out.println("HOORAY");
                        }
                        else
                            {
                                // If sign in fails, display a message to the user.
                                System.out.println("OH NO!");
                                System.out.println(task.getException().toString());
                            }
                    }
                });
                Toast messageToUser = Toast.makeText(this, "Success", Toast.LENGTH_LONG);
                messageToUser.show();
            }
    }
}