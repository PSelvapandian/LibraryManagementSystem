package com.example.librarymanagement.ModelClass;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.example.librarymanagement.Util.RandomUUIDGenerator;

@Entity(primaryKeys = {"id"})
public class SelectedBooksModel
{
    @NonNull
    private String id;
    @NonNull
    private String userId;
    @NonNull
    private String bookId;
    private String bookName;
    private String returnDate;
    private Boolean bookReturnStatus = false;

    public void insertSelectedBooks(@NonNull String userId, @NonNull String bookId, String bookName, String returnDate) {
        this.id = RandomUUIDGenerator.generateRandomId();
        this.userId = userId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.returnDate = returnDate;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    @NonNull
    public String getBookId() {
        return bookId;
    }

    public void setBookId(@NonNull String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getBookReturnStatus() {
        return bookReturnStatus;
    }

    public void setBookReturnStatus(Boolean bookReturnStatus) {
        this.bookReturnStatus = bookReturnStatus;
    }

    @Override
    public String toString() {
        return "SelectedBooksModel{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", bookReturnStatus=" + bookReturnStatus +
                '}';
    }
}
