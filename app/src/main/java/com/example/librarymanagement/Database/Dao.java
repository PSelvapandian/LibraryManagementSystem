package com.example.librarymanagement.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.librarymanagement.ModelClass.BookRequestModel;
import com.example.librarymanagement.ModelClass.BooksModel;
import com.example.librarymanagement.ModelClass.FeedbackModel;
import com.example.librarymanagement.ModelClass.SelectedBooksModel;
import com.example.librarymanagement.ModelClass.User;

import java.util.List;

@androidx.room.Dao
public interface Dao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("SELECT * FROM User WHERE userName=:userName and password=:password")
    User loginUser(String userName, String password);

    @Query("SELECT * FROM User WHERE userId=:userId")
    User getUser(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBook(BooksModel booksModel);

    @Query("SELECT * FROM BooksModel")
    List<BooksModel> getAllBooks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFeedBack(FeedbackModel feedbackModel);

    @Query("SELECT * FROM BooksModel where bookName LIKE '%' || :bookName || '%'")
    LiveData<List<BooksModel>> getBooksBySearch(String bookName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBookRequest(BookRequestModel bookRequestModel);

    @Query("SELECT * FROM BookRequestModel where userId=:userId and bookId=:bookId")
    BookRequestModel getRequestThereOrNot(String userId, String bookId);

    @Query("SELECT * FROM BookRequestModel where userId=:userId")
    List<BookRequestModel> getRequestedBooks(String userId);

    @Query("DELETE FROM BookRequestModel where userId=:userId and bookId=:bookId")
    void removeRequest(String userId, String bookId);

    @Query("SELECT * FROM FeedbackModel")
    LiveData<List<FeedbackModel>> getAllFeedBack();

    @Query("SELECT * FROM BookRequestModel")
    LiveData<List<BookRequestModel>> getAllRequestedBooks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSelectedBooks(SelectedBooksModel selectedBooksModel);

    @Query("SELECT * FROM User where userName=:userName")
    User checkUserExistOrNot(String userName);

    @Query("UPDATE User set password=:password where username=:username")
    void setNewPassword(String username, String password);
}