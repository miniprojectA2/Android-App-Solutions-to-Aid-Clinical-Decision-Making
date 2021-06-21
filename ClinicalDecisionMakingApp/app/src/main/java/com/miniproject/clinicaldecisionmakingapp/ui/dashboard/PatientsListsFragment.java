package com.miniproject.clinicaldecisionmakingapp.ui.dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.miniproject.clinicaldecisionmakingapp.R;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentAddPatientBinding;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentPatientsListsBinding;
import com.miniproject.clinicaldecisionmakingapp.model.DoctorPatients;
import com.miniproject.clinicaldecisionmakingapp.services.FirebaseHelper;
import com.miniproject.clinicaldecisionmakingapp.utils.PatientListsAdapter;

import java.util.ArrayList;
import java.util.List;

public class PatientsListsFragment extends Fragment {

    FragmentPatientsListsBinding binding;

    private RecyclerView patientLists;
    private ArrayList<DoctorPatients> doctorPatientsArrayList;
    private FirebaseFirestore firestore;
    private PatientListsAdapter patientListsAdapter;

    public PatientsListsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPatientsListsBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        patientLists = binding.patientLists;
        String doctorEmail = "";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        doctorEmail = user.getEmail();

        doctorPatientsArrayList = new ArrayList<>();

        firestore = FirebaseFirestore.getInstance();
        patientLists.hasFixedSize();
        patientLists.setLayoutManager(new LinearLayoutManager(getContext()));


        patientListsAdapter = new PatientListsAdapter(doctorPatientsArrayList, getContext());
        patientLists.setAdapter(patientListsAdapter);

        firestore.collection(doctorEmail).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot documentSnapshot : list) {
                                DoctorPatients doctorPatients = documentSnapshot.toObject(DoctorPatients.class);
                                doctorPatients.setId(documentSnapshot.getId());
                                doctorPatientsArrayList.add(doctorPatients);
                            }
                            patientListsAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Fail to load data..", Toast.LENGTH_SHORT).show();
            }
        });

//        loadDatainListView(doctorEmail);

//        database = FirebaseDatabase.getInstance();
//        reference = database.getReference();
//        firebaseHelper = new FirebaseHelper(reference);
//
//        adapter = new ArrayAdapter<String>(
//                getContext(), android.R.layout.simple_list_item_1,
//                firebaseHelper.retrieve(user.getEmail()));
//        patientLists.setAdapter(adapter);


//        binding.addPatient.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.actions.setVisibility(View.GONE);
//                binding.actions1.setVisibility(View.VISIBLE);
//                String email = user.getEmail();
//
//                final String patientName = binding.patientName.getText().toString();
//                final String patientEmail = binding.patientEmail.getText().toString();
//                final String phone = binding.phone.getText().toString();
//                final String age = binding.patientAge.getText().toString();
//                final String weight = binding.patientWeight.getText().toString();
//                final String sex = binding.patientSex.getSelectedItem().toString();
//
//                DoctorPatients doctorPatients = new DoctorPatients();
//                doctorPatients.setDoctorName(email);
//                doctorPatients.setPatientName(patientName);
//                doctorPatients.setPatientEmail(patientEmail);
//                doctorPatients.setPatientPhone(phone);
//                doctorPatients.setPatientAge(age);
//                doctorPatients.setPatientSex(sex);
//                doctorPatients.setPatientWeight(weight);
//
//                if(firebaseHelper.save(doctorPatients)) {
//                    adapter = new ArrayAdapter<String>(
//                            getActivity(), android.R.layout.simple_list_item_1, firebaseHelper.retrieve(email));
//                    patientLists.setAdapter(adapter);
//                } else {
//                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

//    private void loadDatainListView(String email) {
//        firestore.collection(email).get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        if(!queryDocumentSnapshots.isEmpty()) {
//                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                            for(DocumentSnapshot documentSnapshot : list) {
//                                DoctorPatients doctorPatients = documentSnapshot.toObject(DoctorPatients.class);
//                                doctorPatientsArrayList.add(doctorPatients);
//                            }
//                            PatientListsAdapter patientListsAdapter  = new PatientListsAdapter(getContext(), doctorPatientsArrayList);
//                            patientLists.setAdapter(patientListsAdapter);
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getContext(), "Fail to load data..", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}