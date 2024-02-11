package com.example.librarymanagement.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.librarymanagement.ModelClass.BookRequestModel;
import com.example.librarymanagement.ModelClass.BooksModel;
import com.example.librarymanagement.ModelClass.FeedbackModel;
import com.example.librarymanagement.ModelClass.SelectedBooksModel;
import com.example.librarymanagement.ModelClass.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel
{
    Repository repository;

    public UserViewModel(@NonNull Application application)
    {
        super(application);
        repository = new Repository(application);
    }

    public void insertUser(User user)
    {
        repository.insertUser(user);
    }

    public User loginUser(String userName, String password)
    {
        return repository.loginUser(userName, password);
    }

    public User getUser(String userId)
    {
        return repository.getUser(userId);
    }

    public void insertBook(BooksModel booksModel)
    {
        repository.insertBook(booksModel);
    }

    public List<BooksModel> getAllBooks()
    {
        return repository.getAllBooks();
    }

    public void insertFeedBack(FeedbackModel feedbackModel)
    {
        repository.insertFeedBack(feedbackModel);
    }

    public LiveData<List<BooksModel>> getBooksBySearch(String bookName)
    {
        return repository.getBooksBySearch(bookName);
    }

    public void insertBookRequest(BookRequestModel bookRequestModel)
    {
        repository.insertBookRequest(bookRequestModel);
    }

    public BookRequestModel getRequestThereOrNot(String userId, String bookId)
    {
        return repository.getRequestThereOrNot(userId, bookId);
    }

    public List<BookRequestModel> getRequestedBooks(String userId)
    {
        return repository.getRequestedBooks(userId);
    }

    public void removeRequest(String userId, String bookId)
    {
        repository.removeRequest(userId, bookId);
    }

    public LiveData<List<FeedbackModel>> getAllFeedBack()
    {
        return repository.getAllFeedBack();
    }

    public LiveData<List<BookRequestModel>> getAllRequestedBooks()
    {
        return repository.getAllRequestedBooks();
    }

    public void insertSelectedBooks(SelectedBooksModel selectedBooksModel)
    {
        repository.insertSelectedBooks(selectedBooksModel);
    }

    public User checkUserExistOrNot(String userName)
    {
        return repository.checkUserExistOrNot(userName);
    }

    public void setNewPassword(String username, String password)
    {
        repository.setNewPassword(username, password);
    }
}
