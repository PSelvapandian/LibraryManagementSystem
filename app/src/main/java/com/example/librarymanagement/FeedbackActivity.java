package com.example.librarymanagement;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.librarymanagement.Database.UserViewModel;
import com.example.librarymanagement.ModelClass.FeedbackModel;
import com.example.librarymanagement.ModelClass.User;
import com.example.librarymanagement.Util.SharedPreferencesHelper;
import com.example.librarymanagement.databinding.ActivityFeedbackBinding;

import java.util.Objects;

public class FeedbackActivity extends AppCompatActivity implements TextWatcher {
    String userId = "";
    ActivityFeedbackBinding binding;
    UserViewModel userViewModel;
    SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedbackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(getApplicationContext());

        binding.btnBack.setOnClickListener(v -> finish());

        binding.btnSubmit.setOnClickListener(v -> insertFeedBack());

        binding.edtFeedback.addTextChangedListener(this);
    }

    private void insertFeedBack()
    {
        String feedBack = !Objects.requireNonNull(binding.edtFeedback.getText()).toString().isEmpty() ? binding.edtFeedback.getText().toString() : "";
        userId = sharedPreferencesHelper.getUserId() != null && !sharedPreferencesHelper.getUserId().isEmpty() ? sharedPreferencesHelper.getUserId() : "";
        if (!feedBack.isEmpty() && !userId.isEmpty())
        {
            User user = userViewModel.getUser(userId);
            FeedbackModel feedbackModel = new FeedbackModel();
            feedbackModel.insertFeedback(userId, user.getUserName(), feedBack);
            userViewModel.insertFeedBack(feedbackModel);
            showAlertDialog();
            clearData();
        }
        else
        {
            binding.feedbackLayout.setError("Feedback required!");
        }
    }

    private void showAlertDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(FeedbackActivity.this);
        builder.setMessage("Feedback send successfully!");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> finish());
        builder.create().show();
    }

    private void clearData()
    {
        binding.edtFeedback.setText("");
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
        if (s == binding.edtFeedback.getEditableText())
        {
            binding.feedbackLayout.setErrorEnabled(false);
        }
    }
}
