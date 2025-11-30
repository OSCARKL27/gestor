package com.fic.gestor.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "task_title")
    public String taskTitle;

    @ColumnInfo(name = "task_description")
    public String taskDescription;

    @ColumnInfo(name = "created_at")
    public String createdAt;

    @ColumnInfo(name = "is_completed")
    public boolean isCompleted;
}
