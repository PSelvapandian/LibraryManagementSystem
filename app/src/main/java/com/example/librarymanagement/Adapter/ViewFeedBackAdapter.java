package com.example.librarymanagement.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagement.ModelClass.FeedbackModel;
import com.example.librarymanagement.databinding.ListItemFeedbackMessagesBinding;

import java.util.List;

public class ViewFeedBackAdapter extends RecyclerView.Adapter<ViewFeedBackAdapter.ViewFeedBackViewHolder>
{
    List<FeedbackModel> list;

    public ViewFeedBackAdapter()
    {

    }

    public void feedBackList(List<FeedbackModel> list)
    {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewFeedBackAdapter.ViewFeedBackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemFeedbackMessagesBinding binding = ListItemFeedbackMessagesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewFeedBackViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewFeedBackAdapter.ViewFeedBackViewHolder holder, int position)
    {
        String feedbackMessage = list.get(position).getFeedbackMessage() != null && !list.get(position).getFeedbackMessage().isEmpty() ? list.get(position).getFeedbackMessage() : "";
        String userName = list.get(position).getUserName() != null && !list.get(position).getUserName().isEmpty() ? list.get(position).getUserName() : "";
        holder.binding.feedbackMessage.setText(feedbackMessage);
        holder.binding.userName.setText(userName);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewFeedBackViewHolder extends RecyclerView.ViewHolder
    {
        ListItemFeedbackMessagesBinding binding;

        public ViewFeedBackViewHolder(@NonNull ListItemFeedbackMessagesBinding itemView)
        {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
