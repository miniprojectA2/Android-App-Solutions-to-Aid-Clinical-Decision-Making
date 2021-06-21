package com.miniproject.clinicaldecisionmakingapp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.miniproject.clinicaldecisionmakingapp.R;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentPatientDashboardBinding;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentRegisterBinding;
import com.miniproject.clinicaldecisionmakingapp.ui.EvaluationModelFragment;

public class PatientDashboard extends Fragment {

    FragmentPatientDashboardBinding binding;
    DatabaseReference reference;
    FirebaseDatabase database;


    public PatientDashboard() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPatientDashboardBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_patient_dashboard, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String patientEmail="";
        if(user != null) {
            patientEmail = user.getEmail();
        }
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Patient");

        showAllDetails(patientEmail);

        binding.upatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new UpdatePatientFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        binding.symptompage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new EvaluationModelFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
    public void showAllDetails(String email) {
        final String[] name = new String[1];
//        final String[] age = new String[1];
        final String[] pemail = new String[1];
//        final String[] phone = new String[1];
//        final String[] sex = new String[1];
//        final String[] weight = new String[1];
        reference.orderByChild("patientEmail").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datas : snapshot.getChildren()) {
                    name[0] = datas.child("patientName").getValue().toString();
//                    age[0] = datas.child("patientAge").getValue().toString();
                    pemail[0] = datas.child("patientEmail").getValue().toString();
//                    phone[0] = datas.child("patientPhone").getValue().toString();
//                    sex[0] = datas.child("patientSex").getValue().toString();
//                    weight[0] = datas.child("patientWeight").getValue().toString();
                }

                updateHeader(name[0]);
                updateNavigationBar();

                binding.pName.setText(name[0]);
//                binding.pAge.setText("Age: " + age[0]);
//                binding.pEmail.setText("Email: " + email);
//                binding.pSex.setText("Sex: " + sex[0]);
//                binding.pWeight.setText("Weight: " + weight[0]);
//                binding.pPhone.setText("Phone: " + phone[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateHeader(String email) {
        NavigationView navigationView = (NavigationView)getActivity().findViewById(R.id.nav_view);
        View navHeader = navigationView.getHeaderView(0);
        TextView username = (TextView) navHeader.findViewById(R.id.username);
        username.setText(email);
    }


    public void updateNavigationBar() {
        NavigationView navigationView = (NavigationView)getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().findItem(R.id.nav_register).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_home).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_update).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_dashboard).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);

    }
}