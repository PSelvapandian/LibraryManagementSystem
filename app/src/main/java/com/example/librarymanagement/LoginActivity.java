package com.example.librarymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.librarymanagement.Database.UserViewModel;
import com.example.librarymanagement.ModelClass.User;
import com.example.librarymanagement.Util.SharedPreferencesHelper;
import com.example.librarymanagement.databinding.ActivityLoginScreenBinding;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements TextWatcher
{
    ActivityLoginScreenBinding binding;
    UserViewModel userViewModel;
    SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(getApplicationContext());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
            finish();
        });

        binding.btnForgotPassword.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ForgotPasswordActivity.class)));

        binding.btnLogin.setOnClickListener(v -> loginUser());

        binding.edtUserName.addTextChangedListener(this);
        binding.edtPassword.addTextChangedListener(this);
    }

    private void loginUser()
    {
        String userName = !Objects.requireNonNull(binding.edtUserName.getText()).toString().isEmpty() ? binding.edtUserName.getText().toString() : "";
        String password = !Objects.requireNonNull(binding.edtPassword.getText()).toString().isEmpty() ? binding.edtPassword.getText().toString() : "";
        if (!userName.isEmpty() && !password.isEmpty())
        {
            if (userName.trim().equalsIgnoreCase("admin") && password.trim().equalsIgnoreCase("admin"))
            {
                Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, AdminHomeScreenActivity.class));
                finish();
            }
            else
            {
                User user = userViewModel.loginUser(userName, password);
                if (user != null)
                {
                    sharedPreferencesHelper.setUserId(user.getUserId());
                    sharedPreferencesHelper.setLoginStatus(true);
                    Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(this, "Invalid Username & password!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else
        {
            if (binding.edtUserName.getText().toString().isEmpty())
            {
                binding.userNameLayout.setError("Username required");
            }
            if (binding.edtPassword.getText().toString().isEmpty())
            {
                binding.passwordLayout.setError("Password required");
            }
        }
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
        if (s == binding.edtUserName.getEditableText())
        {
            binding.userNameLayout.setErrorEnabled(false);
        }
        if (s == binding.edtPassword.getEditableText())
        {
            binding.passwordLayout.setErrorEnabled(false);
        }
    }
}
