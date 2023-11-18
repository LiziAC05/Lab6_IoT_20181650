package com.example.lab6_20181650;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class GestorActivity extends AppCompatActivity {
    FirebaseFirestore db;
    FirebaseUser currentUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestor);
        mAuth = FirebaseAuth.getInstance();
        CollectionReference colRef = db.collection("usuarios");
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            Query query = colRef.whereEqualTo("authUID", userId);
            query.get().addOnSuccessListener(queryDocumentSnapshots -> {
                if (!queryDocumentSnapshots.isEmpty()) {
                    DocumentSnapshot document = queryDocumentSnapshots.getDocuments().get(0);
                    String rol = document.getString("rol");
                    Log.d("rol", rol);
                    if (rol.equals("gestor")) {

                    }
                } else {
                    Log.d("mensajeError", "no se encontro el codigo");
                }
            }).addOnFailureListener(e -> {
                // Maneja cualquier error que ocurra al tratar de obtener el documento.
                Log.d("fallo en encontrar el documento", "no se encontro");
            });
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //reload();
        }
    }
}