package com.example.csit228f2_2;

import javafx.beans.property.*;

public class Task {
    IntegerProperty taskID;
    IntegerProperty userID;
    StringProperty task;
    BooleanProperty isComplete;

    public Task() {
        this.taskID = new SimpleIntegerProperty();
        this.userID = new SimpleIntegerProperty();
        this.task = new SimpleStringProperty();
        this.isComplete = new SimpleBooleanProperty();
    }

    public int getTaskID() {
        return taskID.get();
    }

    public int getUserIDl() {
        return userID.get();
    }

    public String getTask() {
        return task.get();
    }

    public boolean isComplete() {
        return isComplete.get();
    }

    public void setTaskID(int taskID) {
        this.taskID.set(taskID);
    }

    public void setUserID(int userID) {
        this.userID.set(userID);
    }

    public void setTask(String task) {
        this.task.set(task);
    }

    public void setComplete(boolean complete) {
        this.isComplete.set(complete);
    }
}
