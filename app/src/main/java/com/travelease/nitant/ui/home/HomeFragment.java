package com.travelease.nitant.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.travelease.nitant.APIClient;
import com.travelease.nitant.APIInterface;

import com.travelease.nitant.CityModel;
import com.travelease.nitant.R;
import com.travelease.nitant.ResultCity;
import com.travelease.nitant.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {


    // Add the different city in this array
    String[] City = {"Select a City","Ahmedabad"};
    private FragmentHomeBinding binding;

    Spinner spinnercity;

    List<CityModel> arlocations ;

    APIInterface apiInterface;

    RecyclerView rvlocation;
    String cityS;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ArrayAdapter<String> adapterCity = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, City);
        spinnercity= root.findViewById(R.id.spi_fraghome_city);
        rvlocation = root.findViewById(R.id.rv_homefrag_location);

        rvlocation.setLayoutManager(new LinearLayoutManager(getContext()));

        spinnercity.setAdapter(adapterCity);

        apiInterface = APIClient.getAvik().create(APIInterface.class);

        spinnercity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cityS = spinnercity.getSelectedItem().toString();
                if(!(i==0))
                {
//                Toast.makeText(getActivity(),""+spinnercity.getSelectedItem().toString() +" "+ i , Toast.LENGTH_SHORT).show();
                    Call<ResultCity> call=  apiInterface.getCityLocation(cityS);
                    call.enqueue(new Callback<ResultCity>() {
                        @Override
                        public void onResponse(Call<ResultCity> call, Response<ResultCity> response) {
                            arlocations=response.body().getCity();
                            HomeFragAdapter homeFragAdapter = new HomeFragAdapter((ArrayList<CityModel>) arlocations,getActivity());
                            rvlocation.setAdapter(homeFragAdapter);
                        }

                        @Override
                        public void onFailure(Call<ResultCity> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


//        final TextView textView = binding.textHome;
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}