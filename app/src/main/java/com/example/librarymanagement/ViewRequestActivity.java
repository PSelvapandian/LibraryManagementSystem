package com.example.librarymanagement;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.librarymanagement.Adapter.ViewRequestedBookAdapter;
import com.example.librarymanagement.Database.UserViewModel;
import com.example.librarymanagement.databinding.ActivityViewRequestBinding;


public class ViewRequestActivity extends AppCompatActivity
{
    ActivityViewRequestBinding binding;
    UserViewModel userViewModel;
    ViewRequestedBookAdapter viewRequestedBookAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityViewRequestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.btnBack.setOnClickListener(v -> finish());

        getRequestedBooks();
    }

    private void getRequestedBooks()
    {
        viewRequestedBookAdapter = new ViewRequestedBookAdapter();
        binding.recyclerviewViewRequest.setLayoutManager(new LinearLayoutManager(this));
        userViewModel.getAllRequestedBooks().observe(this, list -> {
            if (list != null && list.size() > 0)
            {
                viewRequestedBookAdapter.requestBookList(list);
                binding.recyclerviewViewRequest.setAdapter(viewRequestedBookAdapter);
                binding.emptyString.setVisibility(View.GONE);
            }
            else
            {
                binding.emptyString.setVisibility(View.VISIBLE);
            }
        });
    }
}
