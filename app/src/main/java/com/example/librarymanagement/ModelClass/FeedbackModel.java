package com.example.librarymanagement.ModelClass;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.example.librarymanagement.Util.RandomUUIDGenerator;

@Entity(primaryKeys = {"feedbackId"})
public class FeedbackModel
{
    @NonNull
    private String feedbackId;
    @NonNull
    private String userId;
    private String userName;
    private String feedbackMessage;

    public void insertFeedback(@NonNull String userId, String userName, String feedbackMessage) {
        this.feedbackId = RandomUUIDGenerator.generateRandomId();
        this.userId = userId;
        this.userName = userName;
        this.feedbackMessage = feedbackMessage;
    }

    @NonNull
    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(@NonNull String feedbackId) {
        this.feedbackId = feedbackId;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

    public void setFeedbackMessage(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "FeedbackModel{" +
                "feedbackId='" + feedbackId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", feedbackMessage='" + feedbackMessage + '\'' +
                '}';
    }
}
