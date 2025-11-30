package com.fic.gestor.model;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class TaskDao_Impl implements TaskDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Task> __insertAdapterOfTask;

  private final EntityDeleteOrUpdateAdapter<Task> __deleteAdapterOfTask;

  private final EntityDeleteOrUpdateAdapter<Task> __updateAdapterOfTask;

  public TaskDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfTask = new EntityInsertAdapter<Task>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `tasks` (`id`,`task_title`,`task_description`,`created_at`,`is_completed`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, final Task entity) {
        statement.bindLong(1, entity.id);
        if (entity.taskTitle == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.taskTitle);
        }
        if (entity.taskDescription == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.taskDescription);
        }
        if (entity.createdAt == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.createdAt);
        }
        final int _tmp = entity.isCompleted ? 1 : 0;
        statement.bindLong(5, _tmp);
      }
    };
    this.__deleteAdapterOfTask = new EntityDeleteOrUpdateAdapter<Task>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `tasks` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, final Task entity) {
        statement.bindLong(1, entity.id);
      }
    };
    this.__updateAdapterOfTask = new EntityDeleteOrUpdateAdapter<Task>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `tasks` SET `id` = ?,`task_title` = ?,`task_description` = ?,`created_at` = ?,`is_completed` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, final Task entity) {
        statement.bindLong(1, entity.id);
        if (entity.taskTitle == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.taskTitle);
        }
        if (entity.taskDescription == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.taskDescription);
        }
        if (entity.createdAt == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.createdAt);
        }
        final int _tmp = entity.isCompleted ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.id);
      }
    };
  }

  @Override
  public void insertTask(final Task task) {
    DBUtil.performBlocking(__db, false, true, (_connection) -> {
      __insertAdapterOfTask.insert(_connection, task);
      return null;
    });
  }

  @Override
  public void deleteTask(final Task task) {
    DBUtil.performBlocking(__db, false, true, (_connection) -> {
      __deleteAdapterOfTask.handle(_connection, task);
      return null;
    });
  }

  @Override
  public void updateTask(final Task task) {
    DBUtil.performBlocking(__db, false, true, (_connection) -> {
      __updateAdapterOfTask.handle(_connection, task);
      return null;
    });
  }

  @Override
  public List<Task> getAllTasks() {
    final String _sql = "SELECT * FROM tasks ORDER BY id DESC";
    return DBUtil.performBlocking(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTaskTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "task_title");
        final int _columnIndexOfTaskDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "task_description");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_at");
        final int _columnIndexOfIsCompleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_completed");
        final List<Task> _result = new ArrayList<Task>();
        while (_stmt.step()) {
          final Task _item;
          _item = new Task();
          _item.id = (int) (_stmt.getLong(_columnIndexOfId));
          if (_stmt.isNull(_columnIndexOfTaskTitle)) {
            _item.taskTitle = null;
          } else {
            _item.taskTitle = _stmt.getText(_columnIndexOfTaskTitle);
          }
          if (_stmt.isNull(_columnIndexOfTaskDescription)) {
            _item.taskDescription = null;
          } else {
            _item.taskDescription = _stmt.getText(_columnIndexOfTaskDescription);
          }
          if (_stmt.isNull(_columnIndexOfCreatedAt)) {
            _item.createdAt = null;
          } else {
            _item.createdAt = _stmt.getText(_columnIndexOfCreatedAt);
          }
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsCompleted));
          _item.isCompleted = _tmp != 0;
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
