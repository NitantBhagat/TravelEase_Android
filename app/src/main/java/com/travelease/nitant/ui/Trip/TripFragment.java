package com.travelease.nitant.ui.Trip;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.travelease.nitant.R;
import com.travelease.nitant.databinding.FragmentTripBinding;

import java.util.ArrayList;
import java.util.List;

public class TripFragment extends Fragment {

    private FragmentTripBinding binding;
    RecyclerView rvTrip;
    //TEMPORARY DATA IN ITEMS//Starts
    int[] id={1,2,3,4,5,6};
    String[] title={"Manali","Ladakh","Family","Goa","Kedarkantha","Somnath"};
    String[] detail={"with school friends","biike ride","road trip to abu","Cousins trip","amazing Trek","Mandir and seawalk"};
    //TEMPORARY DATA IN ITEMS//CONTINUE
    List<TripItemModel> list= new ArrayList<>();
    //TEMPORARY DATA IN ITEMS//End

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTripBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rvTrip= root.findViewById(R.id.rv_tripfrag_items);
//
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvTrip.setLayoutManager(linearLayoutManager);
        list.add(new TripItemModel(1,"Manali","With School Friends"));
        list.add(new TripItemModel(2,"Ladakh","Bike ride"));
        list.add(new TripItemModel(3,"Family","Road trip to Abu"));
        list.add(new TripItemModel(4,"Goa","Cousins trip"));
        list.add(new TripItemModel(5,"Kedarkhantha","Amazing trek"));
        list.add(new TripItemModel(6,"Somnath","Mandir and Seawalk"));
        list.add(new TripItemModel(7,"Saputara","Hill Station"));
        TripFragAdapter tripFragAdapter = new TripFragAdapter(list,getContext());
        rvTrip.setAdapter(tripFragAdapter);

//
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}