package com.example.javaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 작성 목적을 입력할 EditText
        EditText editTextPurpose = findViewById(R.id.editText_purpose);

        // 날짜 버튼
        Button buttonDate = findViewById(R.id.button_date);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 날짜 버튼이 클릭되었을 때 실행되는 동작 구현
                Toast.makeText(MainActivity.this, "날짜 버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 통계 버튼
        Button buttonStatistics = findViewById(R.id.button_statistics);
        buttonStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 통계 버튼이 클릭되었을 때 실행되는 동작 구현
                Toast.makeText(MainActivity.this, "통계 버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 자산 버튼
        Button buttonAssets = findViewById(R.id.button_assets);
        buttonAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 자산 버튼이 클릭되었을 때 실행되는 동작 구현
                Toast.makeText(MainActivity.this, "자산 버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
