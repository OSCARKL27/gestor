package com.fic.gestor.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fic.gestor.R;
import com.fic.gestor.controller.TaskController;
import com.fic.gestor.model.Task;

public class AddEditActivity extends AppCompatActivity {

    EditText txtTitle, txtDesc;
    Button btnSave;
    TaskController controller;

    boolean isEditMode = false;
    int taskId = 0;
    String createdAtExisting = null;
    boolean isCompletedExisting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        txtTitle = findViewById(R.id.txtTitle);
        txtDesc = findViewById(R.id.txtDesc);
        btnSave = findViewById(R.id.btnSave);

        controller = new TaskController(this);

        // Revisar si venimos a EDITAR
        if (getIntent() != null && getIntent().hasExtra("task_id")) {
            isEditMode = true;
            taskId = getIntent().getIntExtra("task_id", 0);
            String title = getIntent().getStringExtra("task_title");
            String desc = getIntent().getStringExtra("task_desc");
            createdAtExisting = getIntent().getStringExtra("task_created_at");
            isCompletedExisting = getIntent().getBooleanExtra("task_is_completed", false);

            if (title != null) txtTitle.setText(title);
            if (desc != null) txtDesc.setText(desc);

            setTitle("Editar tarea");
        } else {
            setTitle("Nueva tarea");
        }

        btnSave.setOnClickListener(v -> {
            String title = txtTitle.getText().toString().trim();
            String desc = txtDesc.getText().toString().trim();

            if (title.isEmpty()) {
                Toast.makeText(this, "task_title no puede estar vacío", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isEditMode) {
                Task t = new Task();
                t.id = taskId;
                t.taskTitle = title;
                t.taskDescription = desc;
                t.createdAt = (createdAtExisting != null)
                        ? createdAtExisting
                        : String.valueOf(System.currentTimeMillis());
                t.isCompleted = isCompletedExisting;

                controller.update(t);
                Toast.makeText(this, "Tarea actualizada", Toast.LENGTH_SHORT).show();
            } else {
                Task t = new Task();
                t.id = 0;
                t.taskTitle = title;
                t.taskDescription = desc;
                t.createdAt = String.valueOf(System.currentTimeMillis());
                t.isCompleted = false;

                controller.add(t);
                Toast.makeText(this, "Tarea agregada", Toast.LENGTH_SHORT).show();
            }

            finish();
        });
    }
}
