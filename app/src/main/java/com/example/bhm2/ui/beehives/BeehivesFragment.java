package com.example.bhm2.ui.beehives;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bhm2.BeehiveInfo;
import com.example.bhm2.BeehivesOpenHelper;
import com.example.bhm2.R;
import com.example.bhm2.SpeechRecognitionService;

public class BeehivesFragment extends Fragment {

    private BeehivesViewModel beehivesViewModel;
    ListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        beehivesViewModel =
                ViewModelProviders.of(this).get(BeehivesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_beehives, container, false);
        Button insertBH = root.findViewById(R.id.insert_beehive);
        final ListView beehivesList = root.findViewById(R.id.beehives_list);

        final BeehivesOpenHelper beehivesOpenHelper = new BeehivesOpenHelper(container.getContext());

        adapter = new ArrayAdapter<>(container.getContext(), android.R.layout.simple_list_item_1, beehivesOpenHelper.GetBeehives());
        beehivesList.setAdapter(adapter);
        insertBH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertBeehiveFragment insertBeehiveFragment = new InsertBeehiveFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, insertBeehiveFragment).commit();
            }
        });
        beehivesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String number = adapterView.getItemAtPosition(i).toString();
                Log.d("TAG", number);
                BeehiveInfo beehiveInfo = beehivesOpenHelper.getBeehiveInfo(Integer.parseInt(number));

                Bundle bundle = new Bundle();
                bundle.putString("id", beehiveInfo.getId());
                bundle.putString("number", beehiveInfo.getNumber());
                bundle.putString("population", beehiveInfo.getPopulation());

                InsertBeehiveFragment insertBeehiveFragment = new InsertBeehiveFragment();
                insertBeehiveFragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, insertBeehiveFragment).commit();
            }
        });
        SpeechRecognitionService sc = new SpeechRecognitionService(root.getContext());
        sc.StartListening();
        return root;
    }
}