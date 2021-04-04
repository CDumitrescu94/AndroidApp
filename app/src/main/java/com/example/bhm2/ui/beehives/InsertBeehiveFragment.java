package com.example.bhm2.ui.beehives;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bhm2.BeehivesOpenHelper;
import com.example.bhm2.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class InsertBeehiveFragment extends Fragment {

    private InsertBeehiveViewModel insertBeehiveViewModel;
    private Button saveBtn;
    private EditText beehiveNumber;
    private EditText beehivePopulation;
    private BeehivesOpenHelper beehivesOpenHelper;
    boolean edit;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        insertBeehiveViewModel =
                ViewModelProviders.of(this).get(InsertBeehiveViewModel.class);
        View root = inflater.inflate(R.layout.fragment_insert_beehive, container, false);
        super.onCreate(savedInstanceState);

        beehivesOpenHelper = new BeehivesOpenHelper(container.getContext());
        saveBtn = root.findViewById(R.id.save_btn);
        beehiveNumber = root.findViewById(R.id.beehive_number);
        beehivePopulation = root.findViewById(R.id.beehive_population);

        if (getArguments() != null){
            beehiveNumber.setText(getArguments().getString("number"));
            edit = true;
        }


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit){
                    beehivesOpenHelper.UpdateBeehive(getArguments().getString("id"), Integer.parseInt(beehiveNumber.getText().toString()), beehivePopulation.getText().toString());
                }
                beehivesOpenHelper.InsertBeehive(Integer.parseInt(beehiveNumber.getText().toString()), beehivePopulation.getText().toString());
                BeehivesFragment beehivesFragment = new BeehivesFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, beehivesFragment).commit();
            }
        });
        return root;
    }
}
