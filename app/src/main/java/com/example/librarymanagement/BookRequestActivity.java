package com.example.librarymanagement;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.librarymanagement.Adapter.BookRequestAdapter;
import com.example.librarymanagement.Database.UserViewModel;
import com.example.librarymanagement.Enum.RequestStatus;
import com.example.librarymanagement.ModelClass.BookRequestModel;
import com.example.librarymanagement.ModelClass.BooksModel;
import com.example.librarymanagement.ModelClass.User;
import com.example.librarymanagement.Util.RandomUUIDGenerator;
import com.example.librarymanagement.Util.SharedPreferencesHelper;
import com.example.librarymanagement.databinding.ActivityRequestBookBinding;

import java.util.ArrayList;
import java.util.List;

public class BookRequestActivity extends AppCompatActivity implements BookRequestAdapter.BookDetailsInterface
{
    String userId = "", userName = "";
    ActivityRequestBookBinding binding;
    UserViewModel userViewModel;
    SharedPreferencesHelper sharedPreferencesHelper;
    BookRequestAdapter bookRequestAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityRequestBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(getApplicationContext());

        getAllBooks();

        binding.btnBack.setOnClickListener(v -> finish());
    }

    private void getAllBooks()
    {
        userId = sharedPreferencesHelper.getUserId();
        userName = userViewModel.getUser(userId).getUserName();

        bookRequestAdapter = new BookRequestAdapter();
        binding.recyclerviewViewBook.setLayoutManager(new LinearLayoutManager(this));
        List<BookRequestModel> requestedBooks = userViewModel.getRequestedBooks(userId);
        List<String> bookIdList = getBookId(requestedBooks);
        List<BooksModel> allBooksList = userViewModel.getAllBooks();
        if (allBooksList != null && allBooksList.size() > 0)
        {
            bookRequestAdapter.bookList(allBooksList, bookIdList, this);
            binding.recyclerviewViewBook.setAdapter(bookRequestAdapter);
            binding.emptyString.setVisibility(View.GONE);
        }
        else
        {
            binding.emptyString.setVisibility(View.VISIBLE);
        }
    }

    private List<String> getBookId(List<BookRequestModel> requestedBooks)
    {
        List<String> bookIdList = new ArrayList<>();
        if (requestedBooks != null && requestedBooks.size() > 0)
        {
            for (BookRequestModel bookRequestModel : requestedBooks)
            {
               bookIdList.add(bookRequestModel.getBookId());
            }
        }
        return bookIdList;
    }

    @Override
    public void passBookDetails(BooksModel booksModel)
    {
        BookRequestModel bookRequestModel = new BookRequestModel();
        bookRequestModel.insertBookRequest(userId, booksModel.getBookId(), userName, booksModel.getBookName(), booksModel.getBookAuthor(),booksModel.getBookCategory(),RequestStatus.PENDING.name());
        checkRequestAvailable(bookRequestModel, userId, booksModel.getBookId());
    }

    private void showDialogDeleteOrNotBookRequest(String bookId)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(BookRequestActivity.this);
        builder.setMessage("Your request already submitted, do you want to remove?");
        builder.setCancelable(false);
        builder.setPositiveButton("Remove", (DialogInterface.OnClickListener) (dialog, which) -> {
            userViewModel.removeRequest(userId, bookId);
            getAllBooks();
            dialog.cancel();
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> dialog.cancel());
        builder.create().show();
    }

    private void checkRequestAvailable(BookRequestModel bookRequestModel, String userId, String bookId)
    {
        BookRequestModel requestThereOrNot = userViewModel.getRequestThereOrNot(userId, bookId);
        if (requestThereOrNot == null)
        {
            userViewModel.insertBookRequest(bookRequestModel);
            showAlertDialog();
            getAllBooks();
        }
        else
        {
            showDialogDeleteOrNotBookRequest(bookId);
        }
    }

    private void showAlertDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(BookRequestActivity.this);
        builder.setMessage("Request submitted successfully!");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> dialog.cancel());
        builder.create().show();
    }
}
