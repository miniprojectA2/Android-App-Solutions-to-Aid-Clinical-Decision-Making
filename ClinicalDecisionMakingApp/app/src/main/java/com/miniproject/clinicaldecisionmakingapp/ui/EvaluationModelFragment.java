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
    MultiSpinnerSearch multiSpinnerSearch, multiSpinnerSearch1, multiSpinnerSearch2, multiSpinnerSearch3, multiSpinnerSearch4, multiSpinnerSearch5, multiSpinnerSearch6, multiSpinnerSearch7, multiSpinnerSearch8, multiSpinnerSearch9, multiSpinnerSearch10, multiSpinnerSearch11, multiSpinnerSearch12;

    List<KeyPairBoolData> arrayList = new ArrayList<>();
    List<KeyPairBoolData> arrayList1 = new ArrayList<>();
    List<KeyPairBoolData> arrayList2 = new ArrayList<>();
    List<KeyPairBoolData> arrayList3 = new ArrayList<>();
    List<KeyPairBoolData> arrayList4 = new ArrayList<>();
    List<KeyPairBoolData> arrayList5 = new ArrayList<>();
    List<KeyPairBoolData> arrayList6 = new ArrayList<>();
    List<KeyPairBoolData> arrayList7 = new ArrayList<>();
    List<KeyPairBoolData> arrayList8 = new ArrayList<>();
    List<KeyPairBoolData> arrayList9 = new ArrayList<>();
    List<KeyPairBoolData> arrayList10 = new ArrayList<>();
    List<KeyPairBoolData> arrayList11 = new ArrayList<>();
    List<KeyPairBoolData> arrayList12 = new ArrayList<>();

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
        multiSpinnerSearch4 = binding.category5;
        multiSpinnerSearch5 = binding.category6;
        multiSpinnerSearch6 = binding.category7;
        multiSpinnerSearch7 = binding.category8;
        multiSpinnerSearch8 = binding.category9;
        multiSpinnerSearch9 = binding.category10;
        multiSpinnerSearch10 = binding.category11;
        multiSpinnerSearch11 = binding.category12;
        multiSpinnerSearch12 = binding.category13;

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

        multiSpinnerSearch4.setSearchEnabled(true);
        multiSpinnerSearch4.setSearchHint("Select Your Category");
        multiSpinnerSearch4.setEmptyTitle("No Data Found");
        multiSpinnerSearch4.setShowSelectAllButton(true);
        multiSpinnerSearch4.setClearText("Close & Clear");

        multiSpinnerSearch5.setSearchEnabled(true);
        multiSpinnerSearch5.setSearchHint("Select Your Category");
        multiSpinnerSearch5.setEmptyTitle("No Data Found");
        multiSpinnerSearch5.setShowSelectAllButton(true);
        multiSpinnerSearch5.setClearText("Close & Clear");

        multiSpinnerSearch6.setSearchEnabled(true);
        multiSpinnerSearch6.setSearchHint("Select Your Category");
        multiSpinnerSearch6.setEmptyTitle("No Data Found");
        multiSpinnerSearch6.setShowSelectAllButton(true);
        multiSpinnerSearch6.setClearText("Close & Clear");

        multiSpinnerSearch7.setSearchEnabled(true);
        multiSpinnerSearch7.setSearchHint("Select Your Category");
        multiSpinnerSearch7.setEmptyTitle("No Data Found");
        multiSpinnerSearch7.setShowSelectAllButton(true);
        multiSpinnerSearch7.setClearText("Close & Clear");

        multiSpinnerSearch8.setSearchEnabled(true);
        multiSpinnerSearch8.setSearchHint("Select Your Category");
        multiSpinnerSearch8.setEmptyTitle("No Data Found");
        multiSpinnerSearch8.setShowSelectAllButton(true);
        multiSpinnerSearch8.setClearText("Close & Clear");

        multiSpinnerSearch9.setSearchEnabled(true);
        multiSpinnerSearch9.setSearchHint("Select Your Category");
        multiSpinnerSearch9.setEmptyTitle("No Data Found");
        multiSpinnerSearch9.setShowSelectAllButton(true);
        multiSpinnerSearch9.setClearText("Close & Clear");

        multiSpinnerSearch10.setSearchEnabled(true);
        multiSpinnerSearch10.setSearchHint("Select Your Category");
        multiSpinnerSearch10.setEmptyTitle("No Data Found");
        multiSpinnerSearch10.setShowSelectAllButton(true);
        multiSpinnerSearch10.setClearText("Close & Clear");

        multiSpinnerSearch11.setSearchEnabled(true);
        multiSpinnerSearch11.setSearchHint("Select Your Category");
        multiSpinnerSearch11.setEmptyTitle("No Data Found");
        multiSpinnerSearch11.setShowSelectAllButton(true);
        multiSpinnerSearch11.setClearText("Close & Clear");

        multiSpinnerSearch12.setSearchEnabled(true);
        multiSpinnerSearch12.setSearchHint("Select Your Category");
        multiSpinnerSearch12.setEmptyTitle("No Data Found");
        multiSpinnerSearch12.setShowSelectAllButton(true);
        multiSpinnerSearch12.setClearText("Close & Clear");

        final List<String> category1List = Arrays.asList(getResources().getStringArray(R.array.spinner_category));
        arrayList = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<category1List.size(); i++) {
            KeyPairBoolData keyPairBoolData = new KeyPairBoolData();
            keyPairBoolData.setId(i+1);
            keyPairBoolData.setName(category1List.get(i));
            keyPairBoolData.setSelected(false);
            arrayList.add(keyPairBoolData);
        }

        final List<String> category1List1 = Arrays.asList(getResources().getStringArray(R.array.spinner_category1));
        arrayList1 = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<category1List1.size(); i++) {
            KeyPairBoolData keyPairBoolData1 = new KeyPairBoolData();
            keyPairBoolData1.setId(i+1);
            keyPairBoolData1.setName(category1List1.get(i));
            keyPairBoolData1.setSelected(false);
            arrayList1.add(keyPairBoolData1);
        }

        final List<String> category1List2 = Arrays.asList(getResources().getStringArray(R.array.spinner_category2));
        arrayList2 = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<category1List2.size(); i++) {
            KeyPairBoolData keyPairBoolData2 = new KeyPairBoolData();
            keyPairBoolData2.setId(i+1);
            keyPairBoolData2.setName(category1List2.get(i));
            keyPairBoolData2.setSelected(false);
            arrayList2.add(keyPairBoolData2);
        }

        final List<String> category1List3 = Arrays.asList(getResources().getStringArray(R.array.spinner_category3));
        arrayList3 = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<category1List3.size(); i++) {
            KeyPairBoolData keyPairBoolData3 = new KeyPairBoolData();
            keyPairBoolData3.setId(i+1);
            keyPairBoolData3.setName(category1List3.get(i));
            keyPairBoolData3.setSelected(false);
            arrayList3.add(keyPairBoolData3);
        }

        final List<String> category1List4 = Arrays.asList(getResources().getStringArray(R.array.spinner_category4));
        arrayList4 = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<category1List4.size(); i++) {
            KeyPairBoolData keyPairBoolData3 = new KeyPairBoolData();
            keyPairBoolData3.setId(i+1);
            keyPairBoolData3.setName(category1List4.get(i));
            keyPairBoolData3.setSelected(false);
            arrayList4.add(keyPairBoolData3);
        }

        final List<String> category1List5 = Arrays.asList(getResources().getStringArray(R.array.spinner_category5));
        arrayList5 = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<category1List5.size(); i++) {
            KeyPairBoolData keyPairBoolData3 = new KeyPairBoolData();
            keyPairBoolData3.setId(i+1);
            keyPairBoolData3.setName(category1List5.get(i));
            keyPairBoolData3.setSelected(false);
            arrayList5.add(keyPairBoolData3);
        }

        final List<String> category1List6 = Arrays.asList(getResources().getStringArray(R.array.spinner_category6));
        arrayList6 = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<category1List6.size(); i++) {
            KeyPairBoolData keyPairBoolData3 = new KeyPairBoolData();
            keyPairBoolData3.setId(i+1);
            keyPairBoolData3.setName(category1List6.get(i));
            keyPairBoolData3.setSelected(false);
            arrayList6.add(keyPairBoolData3);
        }

        final List<String> category1List7 = Arrays.asList(getResources().getStringArray(R.array.spinner_category7));
        arrayList7 = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<category1List7.size(); i++) {
            KeyPairBoolData keyPairBoolData3 = new KeyPairBoolData();
            keyPairBoolData3.setId(i+1);
            keyPairBoolData3.setName(category1List7.get(i));
            keyPairBoolData3.setSelected(false);
            arrayList7.add(keyPairBoolData3);
        }

        final List<String> category1List8 = Arrays.asList(getResources().getStringArray(R.array.spinner_category8));
        arrayList8 = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<category1List8.size(); i++) {
            KeyPairBoolData keyPairBoolData3 = new KeyPairBoolData();
            keyPairBoolData3.setId(i+1);
            keyPairBoolData3.setName(category1List8.get(i));
            keyPairBoolData3.setSelected(false);
            arrayList8.add(keyPairBoolData3);
        }

        final List<String> category1List9 = Arrays.asList(getResources().getStringArray(R.array.spinner_category9));
        arrayList8 = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<category1List9.size(); i++) {
            KeyPairBoolData keyPairBoolData3 = new KeyPairBoolData();
            keyPairBoolData3.setId(i+1);
            keyPairBoolData3.setName(category1List9.get(i));
            keyPairBoolData3.setSelected(false);
            arrayList9.add(keyPairBoolData3);
        }

        final List<String> category1List10 = Arrays.asList(getResources().getStringArray(R.array.spinner_category10));
        arrayList8 = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<category1List10.size(); i++) {
            KeyPairBoolData keyPairBoolData3 = new KeyPairBoolData();
            keyPairBoolData3.setId(i+1);
            keyPairBoolData3.setName(category1List10.get(i));
            keyPairBoolData3.setSelected(false);
            arrayList10.add(keyPairBoolData3);
        }

        final List<String> category1List11 = Arrays.asList(getResources().getStringArray(R.array.spinner_category11));
        arrayList8 = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<category1List11.size(); i++) {
            KeyPairBoolData keyPairBoolData3 = new KeyPairBoolData();
            keyPairBoolData3.setId(i+1);
            keyPairBoolData3.setName(category1List11.get(i));
            keyPairBoolData3.setSelected(false);
            arrayList11.add(keyPairBoolData3);
        }

        final List<String> category1List12 = Arrays.asList(getResources().getStringArray(R.array.spinner_category12));
        arrayList8 = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<category1List12.size(); i++) {
            KeyPairBoolData keyPairBoolData3 = new KeyPairBoolData();
            keyPairBoolData3.setId(i+1);
            keyPairBoolData3.setName(category1List12.get(i));
            keyPairBoolData3.setSelected(false);
            arrayList12.add(keyPairBoolData3);
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

        multiSpinnerSearch4.setItems(arrayList4, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> selectedItems) {
                for(int i=0; i< selectedItems.size(); i++) {
                    if(selectedItems.get(i).isSelected()) {

                    }
                }
            }
        });

        multiSpinnerSearch5.setItems(arrayList5, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> selectedItems) {
                for(int i=0; i< selectedItems.size(); i++) {
                    if(selectedItems.get(i).isSelected()) {

                    }
                }
            }
        });

        multiSpinnerSearch6.setItems(arrayList6, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> selectedItems) {
                for(int i=0; i< selectedItems.size(); i++) {
                    if(selectedItems.get(i).isSelected()) {

                    }
                }
            }
        });

        multiSpinnerSearch7.setItems(arrayList7, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> selectedItems) {
                for(int i=0; i< selectedItems.size(); i++) {
                    if(selectedItems.get(i).isSelected()) {

                    }
                }
            }
        });

        multiSpinnerSearch8.setItems(arrayList8, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> selectedItems) {
                for(int i=0; i< selectedItems.size(); i++) {
                    if(selectedItems.get(i).isSelected()) {

                    }
                }
            }
        });

        multiSpinnerSearch9.setItems(arrayList9, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> selectedItems) {
                for(int i=0; i< selectedItems.size(); i++) {
                    if(selectedItems.get(i).isSelected()) {

                    }
                }
            }
        });

        multiSpinnerSearch10.setItems(arrayList10, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> selectedItems) {
                for(int i=0; i< selectedItems.size(); i++) {
                    if(selectedItems.get(i).isSelected()) {

                    }
                }
            }
        });

        multiSpinnerSearch11.setItems(arrayList11, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> selectedItems) {
                for(int i=0; i< selectedItems.size(); i++) {
                    if(selectedItems.get(i).isSelected()) {

                    }
                }
            }
        });

        multiSpinnerSearch12.setItems(arrayList12, new MultiSpinnerListener() {
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