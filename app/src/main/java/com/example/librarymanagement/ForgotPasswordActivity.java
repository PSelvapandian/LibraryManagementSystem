package com.example.librarymanagement;

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
import com.example.librarymanagement.databinding.ActivityForgotPasswordBinding;

import java.util.Objects;

public class ForgotPasswordActivity extends AppCompatActivity implements TextWatcher
{
    ActivityForgotPasswordBinding binding;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        binding.btnSetPassword.setOnClickListener(v -> setNewPassword());

        binding.edtUserName.addTextChangedListener(this);
        binding.edtPassword.addTextChangedListener(this);
    }

    private void setNewPassword()
    {
        String username = !Objects.requireNonNull(binding.edtUserName.getText()).toString().isEmpty() ? binding.edtUserName.getText().toString() : "";
        String password = !Objects.requireNonNull(binding.edtPassword.getText()).toString().isEmpty() ? binding.edtPassword.getText().toString() : "";
        if (!username.isEmpty() && !password.isEmpty())
        {
            Boolean usernameCheck = checkUserName(username);
            if (usernameCheck)
            {
                userViewModel.setNewPassword(username, password);
                Toast.makeText(this, "New password created successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                binding.userNameLayout.setError("Invalid Username");
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

    private Boolean checkUserName(String userName)
    {
        User user = userViewModel.checkUserExistOrNot(userName);
        return user != null;
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
