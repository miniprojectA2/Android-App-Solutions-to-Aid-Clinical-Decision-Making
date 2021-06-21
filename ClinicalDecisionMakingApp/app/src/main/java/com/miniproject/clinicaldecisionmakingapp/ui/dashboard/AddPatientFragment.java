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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.miniproject.clinicaldecisionmakingapp.R;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentAddPatientBinding;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentLoginBinding;
import com.miniproject.clinicaldecisionmakingapp.model.DoctorPatients;
import com.miniproject.clinicaldecisionmakingapp.services.FirebaseHelper;

import java.util.ArrayList;


public class AddPatientFragment extends Fragment {

    FragmentAddPatientBinding binding;

    FirebaseHelper firebaseHelper;
    ArrayAdapter<String> adapter;
    ListView patientLists;

    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseFirestore firestore;

    public AddPatientFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddPatientBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();

        firestore = FirebaseFirestore.getInstance();

        binding.addPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String patientName = binding.patientName.getText().toString();
                final String patientEmail = binding.patientEmail.getText().toString();
                final String phone = binding.phone.getText().toString();
                final String age = binding.patientAge.getText().toString();
                final String weight = binding.patientWeight.getText().toString();
                final String sex = binding.patientSex.getSelectedItem().toString();

                DoctorPatients doctorPatients = new DoctorPatients();
                doctorPatients.setDoctorName(email);
                doctorPatients.setPatientName(patientName);
                doctorPatients.setPatientEmail(patientEmail);
                doctorPatients.setPatientPhone(phone);
                doctorPatients.setPatientAge(age);
                doctorPatients.setPatientSex(sex);
                doctorPatients.setPatientWeight(weight);

                if(patientName.isEmpty()) {
                    binding.patientName.setError("Please enter your name");
                    binding.patientName.requestFocus();
                    binding.progressBar1.setVisibility(View.GONE);
                } else if(patientEmail.isEmpty()) {
                    binding.patientEmail.setError("Please enter your email");
                    binding.patientEmail.requestFocus();
                    binding.progressBar1.setVisibility(View.GONE);
                } else if(phone.isEmpty()) {
                    binding.phone.setError("Please enter contact");
                    binding.phone.requestFocus();
                    binding.progressBar1.setVisibility(View.GONE);
                } else if(age.isEmpty()) {
                    binding.patientAge.setError("Please enter age");
                    binding.patientAge.requestFocus();
                    binding.progressBar1.setVisibility(View.GONE);
                } else if(weight.isEmpty()) {
                    binding.patientWeight.setError("Please enter your weight");
                    binding.patientWeight.requestFocus();
                    binding.progressBar1.setVisibility(View.GONE);
                } else if(sex.equals("--SEX--")) {
                    binding.patientWeight.setError("Please select your sex");
                    binding.patientSex.requestFocus();
                    binding.progressBar1.setVisibility(View.GONE);
                } else if(patientName.isEmpty() && patientEmail.isEmpty() && phone.isEmpty() && age.isEmpty() && weight.isEmpty()) {
                    Toast.makeText(getContext(), "Fields are Empty!", Toast.LENGTH_SHORT).show();
                    binding.progressBar1.setVisibility(View.GONE);
                } else if(!patientName.isEmpty() && !patientEmail.isEmpty() && !phone.isEmpty() && !age.isEmpty() && !weight.isEmpty()) {
                    CollectionReference patientsLists = firestore.collection(email);
                    patientsLists.add(doctorPatients).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getContext(), "Patient Added", Toast.LENGTH_SHORT).show();
                            binding.progressBar1.setVisibility(View.GONE);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                            binding.progressBar1.setVisibility(View.GONE);
                            Fragment fragment = new AddPatientFragment();
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }
                    });
                }

            }
        });


    }

}