package com.fic.gestor.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.fic.gestor.R;
import com.fic.gestor.controller.TaskController;
import com.fic.gestor.model.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.Holder> {

    private final List<Task> list;
    private final Context ctx;
    private final TaskController controller;
    private final Runnable onDataChanged;
    private final SimpleDateFormat dateFormat =
            new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());

    public TaskAdapter(List<Task> list,
                       Context ctx,
                       TaskController controller,
                       Runnable onDataChanged) {
        this.list = list;
        this.ctx = ctx;
        this.controller = controller;
        this.onDataChanged = onDataChanged;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx)
                .inflate(R.layout.item_task, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder h, int position) {
        Task t = list.get(position);

        h.title.setText(t.taskTitle);
        h.desc.setText(t.taskDescription == null ? "" : t.taskDescription);

        // Estado / fecha
        if (t.isCompleted) {
            h.title.setPaintFlags(h.title.getPaintFlags()
                    | Paint.STRIKE_THRU_TEXT_FLAG);
            h.status.setText("Completada");
        } else {
            h.title.setPaintFlags(
                    h.title.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            h.status.setText("Pendiente");
        }

        if (t.createdAt != null) {
            try {
                long millis = Long.parseLong(t.createdAt);
                String fecha = dateFormat.format(new Date(millis));
                h.date.setText(fecha);
            } catch (NumberFormatException e) {
                h.date.setText("");
            }
        } else {
            h.date.setText("");
        }

        // Click corto → editar
        h.itemView.setOnClickListener(v -> {
            Intent i = new Intent(ctx, AddEditActivity.class);
            i.putExtra("task_id", t.id);
            i.putExtra("task_title", t.taskTitle);
            i.putExtra("task_desc", t.taskDescription);
            i.putExtra("task_created_at", t.createdAt);
            i.putExtra("task_is_completed", t.isCompleted);
            ctx.startActivity(i);
        });

        // Click largo → menú completar / eliminar
        h.itemView.setOnLongClickListener(v -> {
            CharSequence[] opciones = new CharSequence[]{
                    t.isCompleted ? "Marcar como pendiente" : "Marcar como completada",
                    "Eliminar"
            };

            new AlertDialog.Builder(ctx)
                    .setTitle("Acción para la tarea")
                    .setItems(opciones, (dialog, which) -> {
                        if (which == 0) {
                            // Toggle completada
                            t.isCompleted = !t.isCompleted;
                            controller.update(t);
                            Toast.makeText(ctx, "Estado actualizado", Toast.LENGTH_SHORT).show();
                            onDataChanged.run();
                        } else if (which == 1) {
                            controller.delete(t);
                            Toast.makeText(ctx, "Tarea eliminada", Toast.LENGTH_SHORT).show();
                            onDataChanged.run();
                        }
                    })
                    .show();

            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView title, desc, date, status;

        public Holder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtItemTitle);
            desc = itemView.findViewById(R.id.txtItemDesc);
            date = itemView.findViewById(R.id.txtItemDate);
            status = itemView.findViewById(R.id.txtItemStatus);
        }
    }
}
