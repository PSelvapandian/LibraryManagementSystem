package com.example.librarymanagement.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.librarymanagement.ModelClass.BookRequestModel;
import com.example.librarymanagement.ModelClass.BooksModel;
import com.example.librarymanagement.ModelClass.FeedbackModel;
import com.example.librarymanagement.ModelClass.SelectedBooksModel;
import com.example.librarymanagement.ModelClass.User;
import com.example.librarymanagement.R;

@Database(entities = {User.class, BooksModel.class, FeedbackModel.class, BookRequestModel.class, SelectedBooksModel.class}, version = 2, exportSchema = false)

public abstract class DataBaseController extends RoomDatabase
{
    private static DataBaseController mDbController;

    public abstract Dao getDaoAll();

    public static DataBaseController getInstance(Context context)
    {
        if (mDbController == null)
        {
            mDbController = Room.databaseBuilder(context.getApplicationContext(),
                    DataBaseController.class, context.getResources().getString(R.string.app_name))
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return mDbController;
    }
}