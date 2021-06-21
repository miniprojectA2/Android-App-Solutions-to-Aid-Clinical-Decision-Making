package com.miniproject.clinicaldecisionmakingapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerListener;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.miniproject.clinicaldecisionmakingapp.R;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentEvaluationModelBinding;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentLoginBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EvaluationModelFragment extends Fragment {

    FragmentEvaluationModelBinding binding;
    MultiSpinnerSearch multiSpinnerSearch, multiSpinnerSearch1, multiSpinnerSearch2, multiSpinnerSearch3;
    List<KeyPairBoolData> arrayList = new ArrayList<>();
    List<KeyPairBoolData> arrayList1 = new ArrayList<>();
    List<KeyPairBoolData> arrayList2 = new ArrayList<>();
    List<KeyPairBoolData> arrayList3 = new ArrayList<>();

    public EvaluationModelFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEvaluationModelBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_evaluation_model, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        multiSpinnerSearch = binding.category1;
        multiSpinnerSearch1 = binding.category2;
        multiSpinnerSearch2 = binding.category3;
        multiSpinnerSearch3 = binding.category4;

        multiSpinnerSearch.setSearchEnabled(true);
        multiSpinnerSearch.setSearchHint("Select Your Category");
        multiSpinnerSearch.setEmptyTitle("No Data Found");
        multiSpinnerSearch.setShowSelectAllButton(true);
        multiSpinnerSearch.setClearText("Close & Clear");

        multiSpinnerSearch1.setSearchEnabled(true);
        multiSpinnerSearch1.setSearchHint("Select Your Category");
        multiSpinnerSearch1.setEmptyTitle("No Data Found");
        multiSpinnerSearch1.setShowSelectAllButton(true);
        multiSpinnerSearch1.setClearText("Close & Clear");

        multiSpinnerSearch2.setSearchEnabled(true);
        multiSpinnerSearch2.setSearchHint("Select Your Category");
        multiSpinnerSearch2.setEmptyTitle("No Data Found");
        multiSpinnerSearch2.setShowSelectAllButton(true);
        multiSpinnerSearch2.setClearText("Close & Clear");

        multiSpinnerSearch3.setSearchEnabled(true);
        multiSpinnerSearch3.setSearchHint("Select Your Category");
        multiSpinnerSearch3.setEmptyTitle("No Data Found");
        multiSpinnerSearch3.setShowSelectAllButton(true);
        multiSpinnerSearch3.setClearText("Close & Clear");

        final List<String> category1List = Arrays.asList(getResources().getStringArray(R.array.spinner_category));
        arrayList = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<category1List.size(); i++) {
            KeyPairBoolData keyPairBoolData = new KeyPairBoolData();
            keyPairBoolData.setId(i+1);
            keyPairBoolData.setName(category1List.get(i));
            keyPairBoolData.setSelected(false);
            arrayList.add(keyPairBoolData);
        }

        final List<String> category1List1 = Arrays.asList(getResources().getStringArray(R.array.spinner_category));
        arrayList1 = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<category1List1.size(); i++) {
            KeyPairBoolData keyPairBoolData1 = new KeyPairBoolData();
            keyPairBoolData1.setId(i+1);
            keyPairBoolData1.setName(category1List1.get(i));
            keyPairBoolData1.setSelected(false);
            arrayList1.add(keyPairBoolData1);
        }

        final List<String> category1List2 = Arrays.asList(getResources().getStringArray(R.array.spinner_category));
        arrayList2 = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<category1List2.size(); i++) {
            KeyPairBoolData keyPairBoolData2 = new KeyPairBoolData();
            keyPairBoolData2.setId(i+1);
            keyPairBoolData2.setName(category1List2.get(i));
            keyPairBoolData2.setSelected(false);
            arrayList2.add(keyPairBoolData2);
        }

        final List<String> category1List3 = Arrays.asList(getResources().getStringArray(R.array.spinner_category));
        arrayList3 = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<category1List3.size(); i++) {
            KeyPairBoolData keyPairBoolData3 = new KeyPairBoolData();
            keyPairBoolData3.setId(i+1);
            keyPairBoolData3.setName(category1List3.get(i));
            keyPairBoolData3.setSelected(false);
            arrayList3.add(keyPairBoolData3);
        }

        multiSpinnerSearch.setItems(arrayList, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> selectedItems) {
                for(int i=0; i< selectedItems.size(); i++) {
                    if(selectedItems.get(i).isSelected()) {

                    }
                }
            }
        });

        multiSpinnerSearch1.setItems(arrayList1, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> selectedItems) {
                for(int i=0; i< selectedItems.size(); i++) {
                    if(selectedItems.get(i).isSelected()) {

                    }
                }
            }
        });

        multiSpinnerSearch2.setItems(arrayList2, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> selectedItems) {
                for(int i=0; i< selectedItems.size(); i++) {
                    if(selectedItems.get(i).isSelected()) {

                    }
                }
            }
        });

        multiSpinnerSearch3.setItems(arrayList3, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> selectedItems) {
                for(int i=0; i< selectedItems.size(); i++) {
                    if(selectedItems.get(i).isSelected()) {

                    }
                }
            }
        });
    }
}