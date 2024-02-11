package com.example.librarymanagement.ModelClass;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.example.librarymanagement.Util.RandomUUIDGenerator;

@Entity(primaryKeys = {"requestId"})
public class BookRequestModel
{
    @NonNull
    private String requestId;
    @NonNull
    private String userId;
    @NonNull
    private String bookId;
    private String userName;
    private String bookName;
    private String authorName;
    private String category;
    private String requestStatus;

    public void insertBookRequest(@NonNull String userId, @NonNull String bookId, String userName, String bookName, String authorName, String category, String requestStatus) {
        this.requestId = RandomUUIDGenerator.generateRandomId();
        this.userId = userId;
        this.bookId = bookId;
        this.userName = userName;
        this.bookName = bookName;
        this.authorName = authorName;
        this.category = category;
        this.requestStatus = requestStatus;
    }

    @NonNull
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(@NonNull String requestId) {
        this.requestId = requestId;
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Override
    public String toString() {
        return "BookRequestModel{" +
                "requestId='" + requestId + '\'' +
                ", userId='" + userId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", userName='" + userName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", category='" + category + '\'' +
                ", requestStatus='" + requestStatus + '\'' +
                '}';
    }
}
