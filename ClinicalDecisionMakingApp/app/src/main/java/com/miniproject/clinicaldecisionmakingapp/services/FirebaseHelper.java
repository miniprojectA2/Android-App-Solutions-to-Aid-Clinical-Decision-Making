package com.miniproject.clinicaldecisionmakingapp.services;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.miniproject.clinicaldecisionmakingapp.model.DoctorPatients;

import java.util.ArrayList;

public class FirebaseHelper {

    DatabaseReference reference;
    ArrayList<String> patientLists = new ArrayList<>();
    Boolean saved = null;

    public FirebaseHelper(DatabaseReference reference) {
        this.reference = reference;
    }

    public Boolean save(DoctorPatients doctorPatients) {
        if(doctorPatients == null) {
            saved=false;
        }else {
            try {
                reference.child("DoctorPatients").push().setValue(doctorPatients);
                saved=true;

            }catch (DatabaseException e) {
                e.printStackTrace();
                saved=false;
            }
        }

        return saved;
    }

    public ArrayList<String> retrieve(String doctorName) {
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot, doctorName);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot, doctorName);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return patientLists;
    }

    private void fetchData(DataSnapshot dataSnapshot, String doctorName) {
        patientLists.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            String name= ds.getValue(DoctorPatients.class).getPatientName();
            if(doctorName == doctorName)
            patientLists.add(name);
        }
    }
}