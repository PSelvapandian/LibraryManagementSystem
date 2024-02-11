package com.example.librarymanagement.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagement.ModelClass.BookRequestModel;
import com.example.librarymanagement.databinding.ListItemRequestedBooksBinding;

import java.util.List;

public class ViewRequestedBookAdapter extends RecyclerView.Adapter<ViewRequestedBookAdapter.ViewRequestedBookViewHolder>
{
    List<BookRequestModel> list;

    public ViewRequestedBookAdapter()
    {

    }

    public void requestBookList(List<BookRequestModel> list)
    {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewRequestedBookAdapter.ViewRequestedBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemRequestedBooksBinding binding = ListItemRequestedBooksBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewRequestedBookViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewRequestedBookAdapter.ViewRequestedBookViewHolder holder, int position)
    {
        String requestedBook = list.get(position).getBookName() != null && !list.get(position).getBookName().isEmpty() ? list.get(position).getBookName() : "";
        String userName = list.get(position).getUserName() != null && !list.get(position).getUserName().isEmpty() ? list.get(position).getUserName() : "";
        holder.binding.bookName.setText(requestedBook);
        holder.binding.userName.setText(userName);

        holder.binding.btnStatus.setText(list.get(position).getRequestStatus() != null && !list.get(position).getRequestStatus().isEmpty() ? list.get(position).getRequestStatus() : "");
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewRequestedBookViewHolder extends RecyclerView.ViewHolder
    {
        ListItemRequestedBooksBinding binding;

        public ViewRequestedBookViewHolder(@NonNull ListItemRequestedBooksBinding itemView)
        {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
