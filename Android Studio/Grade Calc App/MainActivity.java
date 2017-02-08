package com.example.sydney.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button calcButt;
    private Button clearButt;
    private Integer assignments;
    private Integer exams;
    private TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcButt = (Button)findViewById(R.id.CalcButton);
        clearButt = (Button)findViewById(R.id.ClearButton);
        result = (TextView)findViewById(R.id.finalGrade);

        calcButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                assignments =  Integer.parseInt(((EditText)findViewById(R.id.assignmentPercent)).getText().toString());
                exams = Integer.parseInt(((EditText)findViewById(R.id.ExamPercent)).getText().toString());
                String finalGrade = Integer.toString((assignments/2) + (exams/2));
                result.setText("Your final grade is: " + finalGrade);
            }
        });

        clearButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((EditText)findViewById(R.id.assignmentPercent)).setText(null);
                ((EditText)findViewById(R.id.ExamPercent)).setText(null);
                result.setText("Final Grade");
            }
        });

    }
}
