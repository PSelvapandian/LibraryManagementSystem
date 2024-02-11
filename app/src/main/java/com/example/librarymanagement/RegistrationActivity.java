package com.example.librarymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.librarymanagement.Database.UserViewModel;
import com.example.librarymanagement.ModelClass.User;
import com.example.librarymanagement.databinding.ActivityRegisterScreenBinding;

import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity implements TextWatcher
{
    ActivityRegisterScreenBinding binding;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });

        binding.btnRegister.setOnClickListener(v -> insertUserData());

        binding.edtUserName.addTextChangedListener(this);
        binding.edtPassword.addTextChangedListener(this);
    }

    private void insertUserData()
    {
        String gender = getGender();
        String name = !Objects.requireNonNull(binding.edtName.getText()).toString().isEmpty() ? binding.edtName.getText().toString() : "";
        String dob = !Objects.requireNonNull(binding.edtDob.getText()).toString().isEmpty() ? binding.edtDob.getText().toString() : "";
        String address = !Objects.requireNonNull(binding.edtAddress.getText()).toString().isEmpty() ? binding.edtAddress.getText().toString() : "";
        String email = !Objects.requireNonNull(binding.edtEmail.getText()).toString().isEmpty() ? binding.edtEmail.getText().toString() : "";
        String contactNumber = !Objects.requireNonNull(binding.edtMobileNumber.getText()).toString().isEmpty() ? binding.edtMobileNumber.getText().toString() : "";
        String userName = !Objects.requireNonNull(binding.edtUserName.getText()).toString().isEmpty() ? binding.edtUserName.getText().toString() : "";
        String password = !Objects.requireNonNull(binding.edtPassword.getText()).toString().isEmpty() ? binding.edtPassword.getText().toString() : "";
        if (!userName.isEmpty() && !password.isEmpty())
        {
            if (userName.equalsIgnoreCase("admin"))
            {
                binding.userNameLayout.setError("This username already exist");
            }
            else
            {
                Boolean usernameCheck = checkUserName(userName);
                if (!usernameCheck)
                {
                    User user = new User();
                    user.insertUser(name, gender, dob, address, email, contactNumber, userName, password);
                    userViewModel.insertUser(user);
                    Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show();
                    clearData();
                }
                else
                {
                    binding.userNameLayout.setError("Username already exist");
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

    private Boolean checkUserName(String userName)
    {
        User user = userViewModel.checkUserExistOrNot(userName);
        return user != null;
    }

    private void clearData()
    {
        binding.edtName.setText("");
        binding.edtDob.setText("");
        binding.edtEmail.setText("");
        binding.edtMobileNumber.setText("");
        binding.edtAddress.setText("");
        binding.edtUserName.setText("");
        binding.edtPassword.setText("");
        binding.radioBtnMale.setChecked(false);
        binding.radioBtnFemale.setChecked(false);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private String getGender()
    {
        if (binding.radioBtnMale.isChecked())
        {
            return "MALE";
        }
        else if (binding.radioBtnFemale.isChecked())
        {
            return "FEMALE";
        }
        else
        {
            return "";
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}
