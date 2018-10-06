package br.edu.tonetunidavi.giovani.todolist;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TaskDetailActivity extends AppCompatActivity {

    private Task task;
    private TasksViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        viewModel = ViewModelProviders.of(this).get(TasksViewModel.class);

        viewModel.taskLiveData.observe(this, task -> {
            if (task != null){
                this.task = task;
                setTitle(task.getTitle());
            }
        });

        int id = getIntent().getIntExtra("id", 0);
        viewModel.findTaskById(id);

        viewModel.success.observe(this, success ->{
            if (Boolean.TRUE.equals(success)){
                finish();
            }
        });

        Button buttonDelete = findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.delete(task);
                //TasksStore.getInstance(getApplicationContext()).getTasksDao().delete(task);
                //finish();
            }
        });

        Button buttonDone = findViewById(R.id.button_done);

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.update(new Task(task.getId(), task.getTitle(), true, task.getData()));
                //TasksStore.getInstance(getApplicationContext()).getTasksDao().update(new Task(task.getId(), task.getTitle(), true, task.getData()));
                //finish();
            }
        });
    }
}
