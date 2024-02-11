package com.example.librarymanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.librarymanagement.Database.UserViewModel;
import com.example.librarymanagement.ModelClass.User;
import com.example.librarymanagement.Util.SharedPreferencesHelper;
import com.example.librarymanagement.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
   ActivityMainBinding binding;
   String userId = "";
   UserViewModel userViewModel;
   SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(getApplicationContext());

        getUser();

        binding.btnViewBooks.setOnClickListener(v -> startActivity(new Intent(this, ViewBookDetailsActivity.class)));

        binding.btnSearchBooks.setOnClickListener(v -> startActivity(new Intent(this, SearchBookActivity.class)));

        binding.btnFeedback.setOnClickListener(v -> startActivity(new Intent(this, FeedbackActivity.class)));

        binding.btnRequestBooks.setOnClickListener(v -> startActivity(new Intent(this, BookRequestActivity.class)));

        binding.btnSelectBook.setOnClickListener(v -> startActivity(new Intent(this, SelectBookActivity.class)));

        binding.btnLogout.setOnClickListener(v -> showAlertDialog());
    }

    @SuppressLint("SetTextI18n")
    private void getUser()
    {
        userId = sharedPreferencesHelper.getUserId();
        User user = userViewModel.getUser(userId);
        if (user != null)
        {
            binding.userName.setText((user.getName() != null && !user.getName().isEmpty() ? user.getName() : (user.getUserName() != null && !user.getUserName().isEmpty() ? user.getUserName() : "")) + "!");
        }
    }

    private void showAlertDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to logout!");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            startActivity(new Intent(this, LoginActivity.class));
            sharedPreferencesHelper.setLoginStatus(false);
            finish();
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> dialog.cancel());
        builder.create().show();
    }
}