package com.fic.gestor.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;

import android.os.Bundle;

import com.fic.gestor.R;
import com.fic.gestor.controller.TaskController;
import com.fic.gestor.model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    TaskController controller;
    FloatingActionButton fabAdd;
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        toolbar.setTitle(R.string.app_name);

        controller = new TaskController(this);

        recycler = findViewById(R.id.recyclerTasks);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v ->
                startActivity(new Intent(this, AddEditActivity.class))
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadTasks();
    }

    private void reloadTasks() {
        List<Task> tasks = controller.getAll();
        adapter = new TaskAdapter(tasks, this, controller, this::reloadTasks);
        recycler.setAdapter(adapter);
    }
}
