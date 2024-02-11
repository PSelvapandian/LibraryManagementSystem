package com.example.librarymanagement;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.librarymanagement.Adapter.ViewBookDetailsAdapter;
import com.example.librarymanagement.Database.UserViewModel;
import com.example.librarymanagement.databinding.ActivitySearchBooksBinding;

import java.util.Objects;

public class SearchBookActivity extends AppCompatActivity implements TextWatcher {
    ActivitySearchBooksBinding binding;
    UserViewModel userViewModel;
    ViewBookDetailsAdapter viewBookDetailsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBooksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.btnBack.setOnClickListener(v -> finish());

        binding.edtSearchBook.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s)
    {
        if (s == binding.edtSearchBook.getEditableText() && !Objects.requireNonNull(binding.edtSearchBook.getText()).toString().isEmpty())
        {
            viewBookDetailsAdapter = new ViewBookDetailsAdapter();
            binding.recyclerviewViewBook.setLayoutManager(new LinearLayoutManager(this));
            userViewModel.getBooksBySearch(binding.edtSearchBook.getText().toString()).observe(this, list -> {
                if (list != null && list.size() > 0)
                {
                    viewBookDetailsAdapter.bookList(list);
                    binding.recyclerviewViewBook.setVisibility(View.VISIBLE);
                    binding.recyclerviewViewBook.setAdapter(viewBookDetailsAdapter);
                    binding.emptyString.setVisibility(View.GONE);
                }
                else
                {
                    binding.recyclerviewViewBook.setVisibility(View.GONE);
                    binding.emptyString.setVisibility(View.VISIBLE);
                    binding.emptyString.setText(getResources().getString(R.string.the_book_you_are_looking_for_is_not_there));
                }
            });
        }
        else
        {
            binding.recyclerviewViewBook.setVisibility(View.GONE);
            binding.emptyString.setVisibility(View.VISIBLE);
            binding.emptyString.setText(getResources().getString(R.string.search_your_book_here));
        }
    }
}
