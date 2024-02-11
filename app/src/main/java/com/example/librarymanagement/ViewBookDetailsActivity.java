package com.example.librarymanagement;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.librarymanagement.Adapter.ViewBookDetailsAdapter;
import com.example.librarymanagement.Database.UserViewModel;
import com.example.librarymanagement.ModelClass.BooksModel;
import com.example.librarymanagement.databinding.ActivityViewBookDetailsBinding;

import java.util.List;

public class ViewBookDetailsActivity extends AppCompatActivity
{
    ActivityViewBookDetailsBinding binding;
    UserViewModel userViewModel;
    ViewBookDetailsAdapter viewBookDetailsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewBookDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        getAllBooks();

        binding.btnBack.setOnClickListener(v -> finish());
    }

    private void getAllBooks()
    {
        viewBookDetailsAdapter = new ViewBookDetailsAdapter();
        binding.recyclerviewViewBook.setLayoutManager(new LinearLayoutManager(this));
        List<BooksModel> allBooksList = userViewModel.getAllBooks();
        if (allBooksList != null && allBooksList.size() > 0)
        {
            viewBookDetailsAdapter.bookList(allBooksList);
            binding.recyclerviewViewBook.setAdapter(viewBookDetailsAdapter);
            binding.emptyString.setVisibility(View.GONE);
        }
        else
        {
            binding.emptyString.setVisibility(View.VISIBLE);
        }
    }
}
