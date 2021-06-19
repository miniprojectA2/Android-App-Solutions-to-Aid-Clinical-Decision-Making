package com.miniproject.clinicaldecisionmakingapp.ui.dashboard;

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
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentLoginBinding;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentUpdatePatientBinding;
import com.miniproject.clinicaldecisionmakingapp.model.Patient;

public class UpdatePatientFragment extends Fragment {

    FragmentUpdatePatientBinding binding;
    DatabaseReference reference;
    FirebaseDatabase database;

    public UpdatePatientFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String patientEmail="";
        if(user != null) {
            patientEmail = user.getEmail();
            System.out.println(user.getUid());
        }
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Patient");


        showAllDetails(patientEmail);

        binding.updatePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String patientName = binding.patientName.getText().toString();
                final String patientEmail = binding.patientEmail.getText().toString();
                final String phone = binding.phone.getText().toString();
                final String age = binding.patientAge.getText().toString();
                final String weight = binding.patientWeight.getText().toString();
                final String sex = binding.patientSex.getSelectedItem().toString();
                final String password = binding.password.getText().toString();

                Patient patient = new Patient();
                patient.setPatientName(patientName);
                patient.setPatientEmail(patientEmail);
                patient.setPatientPhone(phone);
                patient.setPatientAge(age);
                patient.setPatientPassword(password);
                patient.setPatientSex(sex);
                patient.setPatientWeight(weight);
                binding.progressBar1.setVisibility(View.VISIBLE);
                reference.child(user.getUid()).setValue(patient);
                Fragment fragment = new UpdatePatientFragment();
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
        final String[] age = new String[1];
        final String[] pemail = new String[1];
        final String[] phone = new String[1];
        final String[] sex = new String[1];
        final String[] weight = new String[1];
        reference.orderByChild("patientEmail").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datas : snapshot.getChildren()) {
                    name[0] = datas.child("patientName").getValue().toString();
                    age[0] = datas.child("patientAge").getValue().toString();
                    pemail[0] = datas.child("patientEmail").getValue().toString();
                    phone[0] = datas.child("patientPhone").getValue().toString();
                    sex[0] = datas.child("patientSex").getValue().toString();
                    weight[0] = datas.child("patientWeight").getValue().toString();
                }


                binding.patientName.setText(name[0]);
                binding.patientAge.setText(age[0]);
                binding.patientEmail.setText(email);
                binding.patientSex.setPrompt(sex[0]);
                binding.patientWeight.setText(weight[0]);
                binding.phone.setText(phone[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpdatePatientBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        return binding.getRoot();
    }
}