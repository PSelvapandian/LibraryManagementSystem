package com.example.librarymanagement;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.librarymanagement.Database.UserViewModel;
import com.example.librarymanagement.ModelClass.BooksModel;
import com.example.librarymanagement.ModelClass.SelectedBooksModel;
import com.example.librarymanagement.ModelClass.User;
import com.example.librarymanagement.Util.SharedPreferencesHelper;
import com.example.librarymanagement.databinding.ActivityGetBookFromLibraryBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SelectBookActivity extends AppCompatActivity
{
    int REQUEST_SEND_SMS = 1001;
    String userId = "", mobileNumber = "", bookId = "", name = "", gender = "";
    List<BooksModel> allBooksList = new ArrayList<>();
    ActivityGetBookFromLibraryBinding binding;
    UserViewModel userViewModel;
    SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGetBookFromLibraryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(getApplicationContext());

        getUserDataAndBooks();

        binding.edtDate.setOnClickListener(v -> getDate());

        binding.btnBack.setOnClickListener(v -> finish());

        binding.btnSelect.setOnClickListener(v -> insertSelectedBooks());

        binding.spinnerBookNames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (allBooksList != null && allBooksList.size() > 0)
                {
                    bookId = allBooksList.get(position).getBookId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void insertSelectedBooks()
    {
        String bookName = String.valueOf(binding.spinnerBookNames.getSelectedItem());
        String returnDate = !binding.edtDate.getText().toString().isEmpty() ? binding.edtDate.getText().toString() : "";
        if (!binding.edtDate.getText().toString().isEmpty())
        {
            SelectedBooksModel selectedBooksModel = new SelectedBooksModel();
            selectedBooksModel.insertSelectedBooks(userId, bookId, bookName, returnDate);
            userViewModel.insertSelectedBooks(selectedBooksModel);
            checkSmsPermission();
        }
        else
        {
            showAlertDialog("Return date is required!");
        }
    }

    private void sendMessageToUser()
    {
        String nameRs = gender.equalsIgnoreCase("MALE") ? "Mr." : (gender.equalsIgnoreCase("FeMale") ? "Ms." : "Mr/Ms.");
        String message = "Hi " + nameRs + "" + name + " Your book will receive with in two days, Thank you for choosing our library and we look forward to serving you again in the future.";

//        android.telephony.SmsManager sms=android.telephony.SmsManager.getDefault();
//        sms.sendTextMessage(mobileNumber, null,message, null, null);

        SmsManager.getDefault().sendTextMessage(mobileNumber, null, message, PendingIntent.getActivity(getApplicationContext(), 0, new Intent(),PendingIntent.FLAG_IMMUTABLE),null);
        showAlertDialog("Your book received with in two days, Thank you");
    }

    @SuppressLint("SimpleDateFormat")
    private void getDate()
    {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            calendar.set(year, month, dayOfMonth);
            String dateString = new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime());
            binding.edtDate.setText(dateString);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.show();
    }

    private void getUserDataAndBooks()
    {
        userId = sharedPreferencesHelper.getUserId();
        User user = userViewModel.getUser(userId);
        if (user != null)
        {
            mobileNumber = user.getContactNumber() != null && !user.getContactNumber().isEmpty() ? user.getContactNumber() : "";
            name = user.getName() != null && !user.getName().isEmpty() ? user.getName() : (user.getUserName() != null && !user.getUserName().isEmpty() ? user.getUserName() : "");
            gender = user.getGender() != null && !user.getGender().isEmpty() ? user.getGender() : "";
        }
        List<BooksModel> allBooks = userViewModel.getAllBooks();
        allBooksList = allBooks;
        List<String> bookNameList = new ArrayList<>();
        if (allBooks != null && allBooks.size() > 0)
        {
            for (BooksModel booksModel : allBooks)
            {
                if (booksModel.getBookName() != null && !booksModel.getBookName().isEmpty())
                {
                    bookNameList.add(booksModel.getBookName());
                }
            }
            ArrayAdapter<String> a = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bookNameList);
            a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerBookNames.setAdapter(a);
//            binding.spinnerBookNames.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bookNameList));
        }
        else
        {
            showAlertDialog("There is no books in library!");
        }
    }

    private void showAlertDialog(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(SelectBookActivity.this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
            finish();
        });
        builder.create().show();
    }

    private void checkSmsPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
        }
        else
        {
            sendMessageToUser();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1001)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                sendMessageToUser();
            }
            else
            {
                Toast.makeText(this, "Permission is denied", Toast.LENGTH_SHORT).show();
                showAlertDialog("Your book received with in two days, Thank you");
            }
        }
    }

}
