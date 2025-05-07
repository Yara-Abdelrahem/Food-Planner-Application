package com.example.yallameal.HomeYallaMeal.ui.profile;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.yallameal.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Access SharedPreferences using requireContext()
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("user_email", null);

        if (userEmail != null) {
            // Use the email as needed
            Toast.makeText(requireContext(), "Welcome back, " + userEmail, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Please log in to enable this feature", Toast.LENGTH_SHORT).show();
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}