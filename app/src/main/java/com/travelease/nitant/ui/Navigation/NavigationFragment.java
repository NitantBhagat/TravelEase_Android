package com.travelease.nitant.ui.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.travelease.nitant.databinding.FragmentNavigationBinding;

public class NavigationFragment extends Fragment {

    private FragmentNavigationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentNavigationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        final TextView textView = binding.textDashboard;
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}