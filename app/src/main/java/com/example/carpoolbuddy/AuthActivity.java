package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Activity that allows user to login or signup
 *
 * @author Myriam Lynch
 * @version 1.0
 */

public class AuthActivity extends AppCompatActivity
{
    //declare variables
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private EditText emailField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        //initialize variables
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        emailField = findViewById(R.id.editTextEmail);
        passwordField = findViewById(R.id.editTextPassword);
    }

    @Override
    /**
     * Method which checks if user is signed in and updates UI accordingly
     */
    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            updateUI(currentUser);
        }
    }

    /**
     * Method to sign in user using user input
     * @param v, a View object to allow method to be called on button click
     */
    public void signIn(View v)
    {
        //get input from user
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        //sign in using input
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LOG IN", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }
                        else
                            {
                                // If sign in fails, display a message to the user.
                                Log.w("LOG IN", "signInWithEmail:failure",
                                        task.getException());
                                updateUI(null);
                            }
                    }
                });
    }

    /**
     * Method to allow user to sign up using user input
     * @param v, a View object to allow method to be called on button click
     */
    public void singUp(View v)
    {
        //get input from user
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        //create new user
        User newUser = new User(email);

        //save user in firebase
        firestore.collection("/users").document(email).set(newUser)
                .addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                //if sign up is succesful, print message
                if (task.isSuccessful())
                {
                    System.out.println("HOORAY");
                }
                //if signup fails, print error message
                else
                    {
                        System.out.println("OH NO!");
                        System.out.println(task.getException().toString());
                    }
            }
        });

        Context c = this;

        //sign in user using firebase
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast messageToUser = Toast.makeText(c , "Success", Toast.LENGTH_LONG);
                    messageToUser.show();
                    Log.d("SIGN UP", "Succesfully signed up");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }
                else
                    {
                        Toast messageToUser = Toast.makeText(c, "Please try again",
                                Toast.LENGTH_LONG);
                        messageToUser.show();
                        Log.w("SIGN UP", "createUserWithEmail:failure",
                                task.getException());
                        updateUI(null);
                    }
            }
        });
    }

    /**
     * Method to update UI once user has signed in
     * @param currUser, a User object indicating with User to sign in
     */
    public void updateUI(FirebaseUser currUser)
    {
        if(currUser!=null)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}