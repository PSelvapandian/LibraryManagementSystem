package com.example.librarymanagement.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagement.ModelClass.BooksModel;
import com.example.librarymanagement.databinding.ListItemViewBooksBinding;

import java.util.List;

public class ViewBookDetailsAdapter extends RecyclerView.Adapter<ViewBookDetailsAdapter.ViewBookDetailsViewHolder>
{
    List<BooksModel> bookList;

    public ViewBookDetailsAdapter()
    {

    }

    public void bookList(List<BooksModel> list)
    {
        this.bookList = list;
    }

    @NonNull
    @Override
    public ViewBookDetailsAdapter.ViewBookDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemViewBooksBinding binding = ListItemViewBooksBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewBookDetailsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewBookDetailsAdapter.ViewBookDetailsViewHolder holder, int position)
    {
        String bookName = bookList.get(position).getBookName() != null && !bookList.get(position).getBookName().isEmpty() ? bookList.get(position).getBookName() : "";
        String category = bookList.get(position).getBookCategory() != null && !bookList.get(position).getBookCategory().isEmpty() ? bookList.get(position).getBookCategory() : "";
        String author = bookList.get(position).getBookAuthor() != null && !bookList.get(position).getBookAuthor().isEmpty() ? bookList.get(position).getBookAuthor() : "";
        String description = bookList.get(position).getBookDescription() != null && !bookList.get(position).getBookDescription().isEmpty() ? bookList.get(position).getBookDescription() : "";
        String year = bookList.get(position).getPublicationYear() != null && !bookList.get(position).getPublicationYear().isEmpty() ? bookList.get(position).getPublicationYear() : "";
        String bookCode = bookList.get(position).getBookCode() != null && !bookList.get(position).getBookCode().isEmpty() ? bookList.get(position).getBookCode() : "";
        holder.binding.bookName.setText(bookName);
        holder.binding.bookCategory.setText(category);
        holder.binding.author.setText(author);
        holder.binding.description.setText(description);
        holder.binding.year.setText(year);
        holder.binding.bookId.setText(bookCode);

        ViewBookDetailsViewHolder viewHolder = new ViewBookDetailsViewHolder(holder.binding);
        viewHolder.hideFields(bookName, category, author, description, year, bookCode);
    }

    @Override
    public int getItemCount() {
        return bookList != null ? bookList.size() : 0;
    }

    public class ViewBookDetailsViewHolder extends RecyclerView.ViewHolder
    {
        ListItemViewBooksBinding binding;

        public ViewBookDetailsViewHolder(@NonNull ListItemViewBooksBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void hideFields(String bookName, String category, String author, String description, String year, String code)
        {
            binding.linearBookName.setVisibility(!bookName.isEmpty() ? View.VISIBLE : View.GONE);
            binding.linearCategory.setVisibility(!category.isEmpty() ? View.VISIBLE : View.GONE);
            binding.linearDescription.setVisibility(!description.isEmpty() ? View.VISIBLE : View.GONE);
            binding.linearBookId.setVisibility(!code.isEmpty() ? View.VISIBLE : View.GONE);
        }
    }
}
