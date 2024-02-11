package com.example.librarymanagement;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.librarymanagement.Adapter.ViewFeedBackAdapter;
import com.example.librarymanagement.Database.UserViewModel;
import com.example.librarymanagement.databinding.ActivityViewFeedbackBinding;

public class ViewFeedBackActivity extends AppCompatActivity
{
    ActivityViewFeedbackBinding binding;
    UserViewModel userViewModel;
    ViewFeedBackAdapter viewFeedBackAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityViewFeedbackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.btnBack.setOnClickListener(v -> finish());

        viewFeedBackAdapter = new ViewFeedBackAdapter();
        binding.recyclerviewViewFeedback.setLayoutManager(new LinearLayoutManager(this));
        userViewModel.getAllFeedBack().observe(this, feedbackModels -> {
            if (feedbackModels != null && feedbackModels.size() > 0)
            {
                viewFeedBackAdapter.feedBackList(feedbackModels);
                binding.recyclerviewViewFeedback.setAdapter(viewFeedBackAdapter);
                binding.emptyString.setVisibility(View.GONE);
            }
            else
            {
                binding.emptyString.setVisibility(View.VISIBLE);
            }
        });
    }
}
