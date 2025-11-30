package com.fic.gestor.controller;

import android.content.Context;

import com.fic.gestor.model.AppDatabase;
import com.fic.gestor.model.Task;

import java.util.List;

public class TaskController {

    private AppDatabase db;

    public TaskController(Context context) {
        db = AppDatabase.getInstance(context);
    }

    public List<Task> getAll() {
        return db.taskDao().getAllTasks();
    }

    public void add(Task task) {
        db.taskDao().insertTask(task);
    }

    public void update(Task task) {
        db.taskDao().updateTask(task);
    }

    public void delete(Task task) {
        db.taskDao().deleteTask(task);
    }
}
