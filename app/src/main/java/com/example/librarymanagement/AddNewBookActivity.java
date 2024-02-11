package com.example.librarymanagement;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.librarymanagement.Database.UserViewModel;
import com.example.librarymanagement.ModelClass.BooksModel;
import com.example.librarymanagement.databinding.ActivityAddNewBookBinding;

import java.util.Objects;

public class AddNewBookActivity extends AppCompatActivity implements TextWatcher {
    ActivityAddNewBookBinding binding;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.btnBack.setOnClickListener(v -> finish());

        binding.btnSubmit.setOnClickListener(v -> insertBook());

        binding.edtBookName.addTextChangedListener(this);
        binding.edtCategory.addTextChangedListener(this);
        binding.edtAuthor.addTextChangedListener(this);
        binding.edtYear.addTextChangedListener(this);
        binding.edtBookId.addTextChangedListener(this);
    }

    private void insertBook()
    {
        String bookName = !Objects.requireNonNull(binding.edtBookName.getText()).toString().isEmpty() ? binding.edtBookName.getText().toString() : "";
        String bookCategory = !Objects.requireNonNull(binding.edtCategory.getText()).toString().isEmpty() ? binding.edtCategory.getText().toString() : "";
        String author = !Objects.requireNonNull(binding.edtAuthor.getText()).toString().isEmpty() ? binding.edtAuthor.getText().toString() : "";
        String description = !Objects.requireNonNull(binding.edtDescription.getText()).toString().isEmpty() ? binding.edtDescription.getText().toString() : "";
        String year = !Objects.requireNonNull(binding.edtYear.getText()).toString().isEmpty() ? binding.edtYear.getText().toString() : "";
        String bookId = !Objects.requireNonNull(binding.edtBookId.getText()).toString().isEmpty() ? binding.edtBookId.getText().toString() : "";
        if (!bookName.isEmpty() && !bookCategory.isEmpty() && !author.isEmpty() && !year.isEmpty() && !bookId.isEmpty())
        {
            BooksModel booksModel = new BooksModel();
            booksModel.insertBook(bookName, bookCategory, author, description, year, bookId);
            userViewModel.insertBook(booksModel);
            Toast.makeText(this, "Book added successfully", Toast.LENGTH_SHORT).show();
            clearData();
        }
        else
        {
            if (binding.edtBookName.getText().toString().isEmpty())
            {
                binding.bookNameLayout.setError("Book name required");
            }
            if (binding.edtCategory.getText().toString().isEmpty())
            {
                binding.categoryLayout.setError("Book category required");
            }if (binding.edtAuthor.getText().toString().isEmpty())
            {
                binding.authorLayout.setError("Author name required");
            }if (binding.edtYear.getText().toString().isEmpty())
            {
                binding.yearLayout.setError("Year of publication is required");
            }
            if (binding.edtBookId.getText().toString().isEmpty())
            {
                binding.bookIdLayout.setError("Book Id is required");
            }
        }
    }

    private void clearData()
    {
        binding.edtBookName.setText("");
        binding.edtCategory.setText("");
        binding.edtAuthor.setText("");
        binding.edtDescription.setText("");
        binding.edtYear.setText("");
        binding.edtBookId.setText("");
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
        if (s == binding.edtBookName.getEditableText())
        {
            binding.bookNameLayout.setErrorEnabled(false);
        }
        if (s == binding.edtCategory.getEditableText())
        {
            binding.categoryLayout.setErrorEnabled(false);
        }
        if (s == binding.edtAuthor.getEditableText())
        {
            binding.authorLayout.setErrorEnabled(false);
        }
        if (s == binding.edtYear.getEditableText())
        {
            binding.yearLayout.setErrorEnabled(false);
        }
        if (s == binding.edtBookId.getEditableText())
        {
            binding.bookIdLayout.setErrorEnabled(false);
        }
    }
}
