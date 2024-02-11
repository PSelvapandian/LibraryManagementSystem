package com.example.librarymanagement.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.librarymanagement.ModelClass.BookRequestModel;
import com.example.librarymanagement.ModelClass.BooksModel;
import com.example.librarymanagement.ModelClass.FeedbackModel;
import com.example.librarymanagement.ModelClass.SelectedBooksModel;
import com.example.librarymanagement.ModelClass.User;

import java.util.List;
import java.util.concurrent.Executors;

public class Repository
{
    private final Dao mDao;
    private final DataBaseController mDataBaseController;

    public Repository(Application application)
    {
        mDataBaseController = DataBaseController.getInstance(application);
        mDao = mDataBaseController.getDaoAll();
    }

    public void insertUser(User user)
    {
        Executors.newSingleThreadExecutor().execute(() -> mDataBaseController.getDaoAll().insertUser(user));
    }

    public User loginUser(String userName, String password)
    {
        return mDao.loginUser(userName, password);
    }

    public User getUser(String userId)
    {
        return mDao.getUser(userId);
    }

    public void insertBook(BooksModel booksModel)
    {
        Executors.newSingleThreadExecutor().execute(() -> mDataBaseController.getDaoAll().insertBook(booksModel));
    }

    public List<BooksModel> getAllBooks()
    {
        return mDao.getAllBooks();
    }

    public void insertFeedBack(FeedbackModel feedbackModel)
    {
        Executors.newSingleThreadExecutor().execute(() -> mDataBaseController.getDaoAll().insertFeedBack(feedbackModel));
    }

    public LiveData<List<BooksModel>> getBooksBySearch(String bookName)
    {
        return mDao.getBooksBySearch(bookName);
    }

    public void insertBookRequest(BookRequestModel bookRequestModel)
    {
        Executors.newSingleThreadExecutor().execute(() -> mDataBaseController.getDaoAll().insertBookRequest(bookRequestModel));
    }

    public BookRequestModel getRequestThereOrNot(String userId, String bookId)
    {
        return mDao.getRequestThereOrNot(userId, bookId);
    }

    public List<BookRequestModel> getRequestedBooks(String userId)
    {
        return mDao.getRequestedBooks(userId);
    }

    public void removeRequest(String userId, String bookId)
    {
        Executors.newSingleThreadExecutor().execute(() -> mDataBaseController.getDaoAll().removeRequest(userId, bookId));
    }

    public LiveData<List<FeedbackModel>> getAllFeedBack()
    {
        return mDao.getAllFeedBack();
    }

    public LiveData<List<BookRequestModel>> getAllRequestedBooks()
    {
        return mDao.getAllRequestedBooks();
    }

    public void insertSelectedBooks(SelectedBooksModel selectedBooksModel)
    {
        Executors.newSingleThreadExecutor().execute(() -> mDataBaseController.getDaoAll().insertSelectedBooks(selectedBooksModel));
    }

    public User checkUserExistOrNot(String userName)
    {
        return mDao.checkUserExistOrNot(userName);
    }

    public void setNewPassword(String username, String password)
    {
        Executors.newSingleThreadExecutor().execute(() -> mDataBaseController.getDaoAll().setNewPassword(username, password));
    }
}