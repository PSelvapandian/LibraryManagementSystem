package com.example.librarymanagement.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagement.ModelClass.BooksModel;
import com.example.librarymanagement.databinding.ListItemViewBooksBinding;

import java.util.List;

public class BookRequestAdapter extends RecyclerView.Adapter<BookRequestAdapter.BookRequestViewHolder> {
    List<BooksModel> bookList;
    List<String> bookIdList;
    BookDetailsInterface bookDetailsInterface;

    public BookRequestAdapter()
    {

    }

    public void bookList(List<BooksModel> list, List<String> bookIdList, BookDetailsInterface bookDetailsInterface)
    {
        this.bookList = list;
        this.bookIdList = bookIdList;
        this.bookDetailsInterface = bookDetailsInterface;
    }

    @NonNull
    @Override
    public BookRequestAdapter.BookRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemViewBooksBinding binding = ListItemViewBooksBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BookRequestViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BookRequestAdapter.BookRequestViewHolder holder, int position)
    {
        holder.binding.btnRequest.setVisibility(View.VISIBLE);
        String bookName = bookList.get(position).getBookName() != null && !bookList.get(position).getBookName().isEmpty() ? bookList.get(position).getBookName() : "";
        String category = bookList.get(position).getBookCategory() != null && !bookList.get(position).getBookCategory().isEmpty() ? bookList.get(position).getBookCategory() : "";
        String author = bookList.get(position).getBookAuthor() != null && !bookList.get(position).getBookAuthor().isEmpty() ? bookList.get(position).getBookAuthor() : "";
        String description = bookList.get(position).getBookDescription() != null && !bookList.get(position).getBookDescription().isEmpty() ? bookList.get(position).getBookDescription() : "";

        holder.binding.bookName.setText(bookName);
        holder.binding.bookCategory.setText(category);
        holder.binding.author.setText(author);
        holder.binding.description.setText(description);

        BookRequestViewHolder viewHolder = new BookRequestViewHolder(holder.binding);
        viewHolder.hideFields(bookName, category, author, description);

        for (String bookId : bookIdList)
        {
            if (bookId.equals(bookList.get(position).getBookId()))
            {
                holder.binding.btnRequest.setText("Requested");
            }
        }

        holder.binding.btnRequest.setOnClickListener(v -> bookDetailsInterface.passBookDetails(bookList.get(position)));
    }

    @Override
    public int getItemCount() {
        return bookList != null ? bookList.size() : 0;
    }

    public class BookRequestViewHolder extends RecyclerView.ViewHolder
    {
        ListItemViewBooksBinding binding;

        public BookRequestViewHolder(@NonNull ListItemViewBooksBinding itemView)
        {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void hideFields(String bookName, String category, String author, String description)
        {
            binding.linearBookName.setVisibility(!bookName.isEmpty() ? View.VISIBLE : View.GONE);
            binding.linearCategory.setVisibility(!category.isEmpty() ? View.VISIBLE : View.GONE);
            binding.linearDescription.setVisibility(!description.isEmpty() ? View.VISIBLE : View.GONE);
        }
    }

    public interface BookDetailsInterface
    {
        void passBookDetails(BooksModel booksModel);
    }
}
