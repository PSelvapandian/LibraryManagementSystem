package com.example.librarymanagement.ModelClass;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.example.librarymanagement.Util.RandomUUIDGenerator;

@Entity(primaryKeys = {"bookId"})
public class BooksModel
{
    @NonNull
    private String bookId;
    private String bookName;
    private String bookCategory;
    private String bookAuthor;
    private String bookDescription;
    private String publicationYear;
    private String bookCode;

    public void insertBook(String bookName, String bookCategory, String bookAuthor, String bookDescription, String publicationYear, String bookCode) {
        this.bookId = RandomUUIDGenerator.generateRandomId();
        this.bookName = bookName;
        this.bookCategory = bookCategory;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
        this.publicationYear = publicationYear;
        this.bookCode = bookCode;
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

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    @Override
    public String toString() {
        return "BooksModel{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookCategory='" + bookCategory + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookDescription='" + bookDescription + '\'' +
                ", publicationYear='" + publicationYear + '\'' +
                ", bookCode='" + bookCode + '\'' +
                '}';
    }
}
