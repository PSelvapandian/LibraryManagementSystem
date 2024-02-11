package com.example.librarymanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagement.Util.SharedPreferencesHelper;
import com.example.librarymanagement.databinding.AdminDashboardBinding;

public class AdminHomeScreenActivity extends AppCompatActivity
{
    AdminDashboardBinding binding;
    SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(getApplicationContext());

        binding.btnAddBooks.setOnClickListener(v -> startActivity(new Intent(this, AddNewBookActivity.class)));

        binding.btnViewBooks.setOnClickListener(v -> startActivity(new Intent(this, ViewBookDetailsActivity.class)));

        binding.btnViewFeedback.setOnClickListener(v -> startActivity(new Intent(this, ViewFeedBackActivity.class)));

        binding.btnViewRequestBooks.setOnClickListener(v -> startActivity(new Intent(this, ViewRequestActivity.class)));

        binding.btnLogout.setOnClickListener(v -> showAlertDialog());
    }

    private void showAlertDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminHomeScreenActivity.this);
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
