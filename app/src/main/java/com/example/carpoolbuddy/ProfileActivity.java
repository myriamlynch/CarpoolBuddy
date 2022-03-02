package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * Activity that allows the user to view and edit their profile.
 *
 * @author Myriam Lynch
 * @version 1.9
 */

public class ProfileActivity extends AppCompatActivity
{
    //declare variables
    private FirebaseFirestore firestore;
    private FirebaseAuth mAuth;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView pointsTextView;
    private EditText emailInput;
    private EditText nameInput;
    private EditText idInput;
    private EditText typeInput;
    private String id;
    private String name;
    private String type;
    private String p;
    private User currentUser;

    @Override
    /**
     * This method should:
     * - initialize variables
     * - set text on screen (if fields in profile are not yet set, should display "not set"
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //initialize variables
        textView1 = findViewById(R.id.email);
        textView2 = findViewById(R.id.name);
        textView3 = findViewById(R.id.ID);
        textView4 = findViewById(R.id.uType);
        pointsTextView = findViewById(R.id.points);
        emailInput = (EditText) findViewById(R.id.owner2);
        nameInput = (EditText) findViewById(R.id.owner4);
        idInput = (EditText) findViewById(R.id.owner5);
        typeInput = (EditText) findViewById(R.id.owner6);
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currUser = mAuth.getCurrentUser();
        String email = currUser.getEmail();

        //update profile and text on screen with information
        textView1.setText(email);
        id = "error";
        name = "error";
        type = "error";

        firestore.collection("users").whereEqualTo("email", email).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot ds : task.getResult().getDocuments())
                {
                    //get information from profile
                    if(ds.get("uid") == null)
                        id = "error";
                    else
                        id = ""+ ds.get("uid");
                    if(ds.get("userType") == null)
                        type = "error";
                    else
                        type = ""+ ds.get("userType");
                    if(ds.get("name") == null)
                        name = "error";
                    else
                        name = ""+ ds.get("name");
                    if(ds.get("points") == null)
                        p = "error";
                    else
                        p = "" + ds.get("points");

                    //update profile
                    if(id.equals("error"))
                        textView3.setText("not set");
                    else
                        textView3.setText(id);
                    if(name.equals("error"))
                        textView2.setText("not set");
                    else
                        textView2.setText(name);
                    if(type.equals("error"))
                        textView4.setText("not set");
                    else
                        textView4.setText(type);
                    if(p.equals("error"))
                        pointsTextView.setText("error");
                    else
                        pointsTextView.setText(p);
                }
            }
        });
    }

    /**
     * Method to get input from user and update profile and text on screen
     * @param vi, a View object to allow method to be called on button click
     */
    public void updateInfo(View vi)
    {
        //get input from user
        String email = emailInput.getText().toString();
        String name = nameInput.getText().toString();
        String type = typeInput.getText().toString();
        String id = idInput.getText().toString();

        //if all EditTexts are complete, update database
        if(!email.isEmpty() && !name.isEmpty() && !type.isEmpty() && !id.isEmpty())
        {
            FirebaseUser currUser = mAuth.getCurrentUser();

            //update database with new information
            firestore.collection("users").document(currUser.getEmail())
                    .update("name", name);
            firestore.collection("users").document(currUser.getEmail())
                    .update("email", email);
            firestore.collection("users").document(currUser.getEmail())
                    .update("userType", type);
            firestore.collection("users").document(currUser.getEmail())
                    .update("uid", id);

            //display new profile information
            textView3.setText(id);
            textView4.setText(type);
            textView2.setText(name);

            Toast messageToUser = Toast.makeText(this, "Success", Toast.LENGTH_LONG);
            messageToUser.show();
        }
        else
            {
                //if all EditTexts are not complete, display error message
                Toast messageToUser = Toast.makeText(this, "Please try again",
                        Toast.LENGTH_LONG);
                messageToUser.show();
            }
    }

    /**
     * Method to allow user to sign out and update UI
     * @param v, a View object to allow method to be called on button click
     */
    public void signOut(View v)
    {
        mAuth.signOut();
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }

    /**
     * Method to allow user to navigate to VehiclesInfoActivity
     * @param v, a View object to allow method to be called on button click
     */
    public void seeVehicles(View v)
    {
        Intent intent = new Intent(this, VehiclesInfoActivity.class);
        startActivity(intent);
    }

    /**
     * Method to allow user to navigate to AddVehicles Activity
     * @param v, a View object to allow method to be called on button click
     */
    public void addVehicle(View v)
    {
        Intent intent = new Intent(this, AddVehicle.class);
        startActivity(intent);
    }
}