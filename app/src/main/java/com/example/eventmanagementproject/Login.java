package com.example.eventmanagementproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText emailUsernameInput, passwordInput;
    private Button loginButton;
    private TextView forgotPassword;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailUsernameInput = findViewById(R.id.editTextEmailUsername);
        passwordInput = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);
        forgotPassword=findViewById(R.id.forgot_password);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyLogin();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

    }

    private void verifyLogin() {
        String input = emailUsernameInput.getText().toString().trim();
        String enteredPassword = passwordInput.getText().toString().trim();

        if (TextUtils.isEmpty(input) || TextUtils.isEmpty(enteredPassword)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Search for email or username in Firebase
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean isUserFound = false;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HelperClass user = snapshot.getValue(HelperClass.class);

                    if (user != null) {
                        // Check if input matches email or username
                        if ((user.getEmail().equals(input) || user.getName().equals(input)) &&
                                user.getPassword().equals(enteredPassword)) {
                            isUserFound = true;
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                            // Redirect to the dashboard
                            Intent intent = new Intent(Login.this, dashBoard.class);
                            startActivity(intent);
                            finish();
                            break;
                        }
                    }
                }

                if (!isUserFound) {
                    Toast.makeText(Login.this, "Invalid email/username or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("LoginActivity", "Database error: " + databaseError.getMessage());
                Toast.makeText(Login.this, "Login failed: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
