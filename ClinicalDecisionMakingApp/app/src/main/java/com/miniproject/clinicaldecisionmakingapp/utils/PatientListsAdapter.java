package com.miniproject.clinicaldecisionmakingapp.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.miniproject.clinicaldecisionmakingapp.R;
import com.miniproject.clinicaldecisionmakingapp.model.DoctorPatients;
import com.miniproject.clinicaldecisionmakingapp.ui.dashboard.UpdateDoctorFragment;
import com.miniproject.clinicaldecisionmakingapp.ui.doctors.CrudPatientFragment;

import java.util.ArrayList;

public class PatientListsAdapter extends RecyclerView.Adapter<PatientListsAdapter.ViewHolder> {

    private ArrayList<DoctorPatients> doctorPatientsArrayList;
    private Context context;

    public PatientListsAdapter(ArrayList<DoctorPatients> doctorPatientsArrayList, Context context) {
        this.doctorPatientsArrayList = doctorPatientsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PatientListsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.patient_lists, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PatientListsAdapter.ViewHolder holder, int position) {
        // setting data to our text views from our modal class.
        DoctorPatients doctorPatients = doctorPatientsArrayList.get(position);
        holder.patientDetail.setText(doctorPatients.getPatientName());
        holder.patientAge.setText(doctorPatients.getPatientAge());
        holder.patientSex.setText(doctorPatients.getPatientSex());
        holder.patientWeight.setText(doctorPatients.getPatientWeight());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list.
        return doctorPatientsArrayList.size();
    }

    //    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View listItemView = convertView;
//        if(listItemView == null) {
//            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.patient_lists, parent, false);
//
//        }
//
//        DoctorPatients doctorPatients = getItem(position);
//
//        TextView patientDetail = listItemView.findViewById(R.id.patientDetail);
//        TextView patientAge = listItemView.findViewById(R.id.patientAge);
//        TextView patientWeight = listItemView.findViewById(R.id.patientWeight);
//        TextView patientSex = listItemView.findViewById(R.id.patientSex);
//        patientDetail.setText(doctorPatients.getPatientName());
//        patientAge.setText("Age: " + doctorPatients.getPatientAge());
//        patientWeight.setText("Weight: " + doctorPatients.getPatientWeight());
//        patientSex.setText("Sex: " + doctorPatients.getPatientSex());
//
//        return listItemView;
//    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView patientDetail;
        private final TextView patientAge;
        private final TextView patientWeight;
        private final TextView patientSex;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            patientDetail = itemView.findViewById(R.id.patientDetail);
            patientAge = itemView.findViewById(R.id.patientAge);
            patientWeight = itemView.findViewById(R.id.patientWeight);
            patientSex = itemView.findViewById(R.id.patientSex);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DoctorPatients doctorPatients = doctorPatientsArrayList.get(getAdapterPosition());

                    CrudPatientFragment crudPatientFragment = new CrudPatientFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("patients", doctorPatients);
                    crudPatientFragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, crudPatientFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
            });
        }
    }
}
