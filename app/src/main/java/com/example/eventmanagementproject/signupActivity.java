package com.example.eventmanagementproject;

import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class signupActivity extends AppCompatActivity {

    private TextView login_text;
    private Button emailbutton;
    private Button phonebutton;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);

        phonebutton = findViewById(R.id.continue_with_phone);
        emailbutton = findViewById(R.id.continue_with_email);
        login_text = findViewById(R.id.login_text);

        phonebutton.setOnClickListener(view -> {
            intent = new Intent(signupActivity.this, signupWithPhone.class);
            startActivity(intent);
        });

        emailbutton.setOnClickListener(view -> {
            intent = new Intent(signupActivity.this, signupwithEmail.class);
            startActivity(intent);
        });

        login_text.setOnClickListener(view -> {
            intent = new Intent(signupActivity.this, Login.class);
            startActivity(intent);
        });
    }
}