package com.example.shoppinglistapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {
FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    fab=findViewById(R.id.fabAddTask);
    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showAddItemDialog();
        }
    });

    }

    private void showAddItemDialog() {
        // Create and configure the custom dialog
        Dialog dialog = new Dialog(HomeActivity.this);
        dialog.setContentView(R.layout.custom_add);

        // Get references to the EditText fields and button
        EditText etItemName = dialog.findViewById(R.id.etItemName);
        EditText etItemQuantity = dialog.findViewById(R.id.etItemQuantity);
        EditText etItemPrice = dialog.findViewById(R.id.etItemPrice);
        Button btnSaveItem = dialog.findViewById(R.id.btnSaveItem);

        // Save the item when the button is clicked
        btnSaveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = etItemName.getText().toString().trim();
                String itemQuantity = etItemQuantity.getText().toString().trim();
                String itemPrice = etItemPrice.getText().toString().trim();

                // Validate input
                if (itemName.isEmpty() || itemQuantity.isEmpty() || itemPrice.isEmpty()) {
                    Toast.makeText(HomeActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create an Item object
                Item newItem = new Item(itemName, itemQuantity, itemPrice);

                // Get a local reference to Firebase Realtime Database
                DatabaseReference db = FirebaseDatabase.getInstance().getReference();

                // Save the item to the "shoppinglist" node in Realtime Database
                db.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("shoppinglist").push()  // push() creates a unique ID for each item
                        .setValue(newItem)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(HomeActivity.this, "Item added successfully", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();  // Close the dialog
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(HomeActivity.this, "Error adding item: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });

        // Show the dialog
        dialog.show();
    }
}