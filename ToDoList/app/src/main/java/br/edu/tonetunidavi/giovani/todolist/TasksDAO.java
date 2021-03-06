package br.edu.tonetunidavi.giovani.todolist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TasksDAO {

    @Query("SELECT * FROM tasks")
    List<Task> fetchTasks();

    @Query("SELECT * FROM tasks WHERE id = :id")
    Task findById(int id);

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);
}

