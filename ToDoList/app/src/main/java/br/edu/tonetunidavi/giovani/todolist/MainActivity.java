package br.edu.tonetunidavi.giovani.todolist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TasksViewModel viewModel;

    private TasksAdapter adapter = new TasksAdapter(new TasksAdapter.OnTaskClickListener() {
        @Override
        public void onClick(Task task) {
            //Toast.makeText(getApplicationContext(),task.getTitle(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),TaskDetailActivity.class);

            intent.putExtra("id", task.getId());

            startActivity(intent);
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(TasksViewModel.class);

        viewModel.tasks.observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                if (tasks != null){
                    adapter.setup(tasks);
                }
            }
        });

        RecyclerView taskList =  findViewById(R.id.task_list);
        taskList.setLayoutManager(new LinearLayoutManager(this));
        taskList.setAdapter(adapter);

        FloatingActionButton buttonCreate = findViewById(R.id.button_create);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), NewTaskActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.fetchTasks();

    }
}
