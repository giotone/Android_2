package br.edu.tonetunidavi.giovani.todolist;

import android.arch.lifecycle.ViewModelProviders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;


import java.util.Date;

public class NewTaskActivity extends AppCompatActivity {

    private TasksViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(v -> {

            EditText inputNewTask = findViewById(R.id.input_new_task);

            String value =  inputNewTask.getText().toString();
            //LocalDate dataAtual = LocalDate.now();

            Date valueDate = new Date();
            //Date valueDate = Date.from(dataAtual.atStartOfDay(ZoneId.systemDefault()).toInstant());

            viewModel = ViewModelProviders.of(this).get(TasksViewModel.class);

            // quando o insert for concluido o sistema joga true e pode finalizar a tread
            viewModel.success.observe(this, success ->{
                if (Boolean.TRUE.equals(success)){
                    finish();
                }
            });

            if (!value.isEmpty()){
                viewModel.insert(new Task(value, valueDate));
                //TasksStore.getInstance(getApplicationContext()).getTasksDao().insert(new Task(value, valueDate))
            }

        });
    }
}
