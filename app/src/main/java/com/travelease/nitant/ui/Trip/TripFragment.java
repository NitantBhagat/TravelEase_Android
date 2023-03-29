package com.travelease.nitant.ui.Trip;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.travelease.nitant.AddActivity;
import com.travelease.nitant.R;
import com.travelease.nitant.database.TripItemDBHelper;
import com.travelease.nitant.databinding.FragmentTripBinding;

import java.util.ArrayList;
import java.util.List;

public class TripFragment extends Fragment implements View.OnClickListener {

    private FragmentTripBinding binding;

    ImageView ivAdd;
    RecyclerView rvTrip;
    List<TripItemModel> list= new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTripBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rvTrip= root.findViewById(R.id.rv_tripfrag_items);
        ivAdd= root.findViewById(R.id.iv_tripfrag_add);
//
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvTrip.setLayoutManager(linearLayoutManager);
//        list.add(new TripItemModel(1,"Manali","With School Friends"));
//        list.add(new TripItemModel(2,"Ladakh","Bike ride"));
//        list.add(new TripItemModel(3,"Family","Road trip to Abu"));
//        list.add(new TripItemModel(4,"Goa","Cousins trip"));
//        list.add(new TripItemModel(5,"Kedarkhantha","Amazing trek"));
//        list.add(new TripItemModel(6,"Somnath","Mandir and Seawalk"));
//        list.add(new TripItemModel(7,"Saputara","Hill Station"));
        TripItemDBHelper tripItemDBHelper = new TripItemDBHelper(getContext());
        list = tripItemDBHelper.Show();
        TripFragAdapter tripFragAdapter = new TripFragAdapter(list,getContext());
        rvTrip.setAdapter(tripFragAdapter);
        ivAdd.setOnClickListener(this);
//
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), AddActivity.class);
        startActivity(intent);
    }
}