package com.example.eventmanagementproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class dashBoard extends AppCompatActivity {

    private EditText searchBar;
    private LinearLayout btnHome,btnLiveEvent, btnSettings;;
    private ImageView profileIcon, menuIcon;
    Intent intent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        btnHome=(LinearLayout)findViewById(R.id.btnHome);
        searchBar = findViewById(R.id.searchBar);
        //btnHome = findViewById(R.id.textHome);
        btnLiveEvent = findViewById(R.id.btnLiveEventLayout);
        btnSettings = findViewById(R.id.btnSettings);
        profileIcon = findViewById(R.id.profileIcon);

        // Handle search functionality
        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchBar.getText().toString();
                // Perform search logic
            }
        });

        // Set filters for events
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Filter events by "Upcoming"
            }
        });

        btnLiveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Filter events by "Popular"
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Filter events by "Near You"
                intent=new Intent(dashBoard.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        // Profile icon functionality
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Profile activity
                Intent intent = new Intent(dashBoard.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}