package com.example.account_book;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button incomeBtn;
    Button expenseBtn;
    TextView incomeCategoryTextView;
    TextView expenseCategoryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // 날짜 버튼
        Button buttonDate = findViewById(R.id.button_date);
        buttonDate.setOnClickListener(v -> {
            // 날짜 버튼이 클릭되었을 때 실행되는 동작 구현
            Toast.makeText(MainActivity.this, "날짜 버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show();

            // StatisticsActivity로 전환하는 Intent 생성
            Intent intent = new Intent(MainActivity.this, Sub_Check.class);

            // 액티비티 전환
            startActivity(intent);

        });

        // 통계 버튼
        Button buttonStatistics = findViewById(R.id.button_statistics);
        buttonStatistics.setOnClickListener(v -> {
            // 통계 버튼이 클릭되었을 때 실행되는 동작 구현
            Toast.makeText(MainActivity.this, "통계 버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show();

            // StatisticsActivity로 전환하는 Intent 생성
            Intent intent = new Intent(MainActivity.this, Expenditure_Activity.class);

            // 액티비티 전환
            startActivity(intent);

        });

        // 자산 버튼
        Button buttonAssets = findViewById(R.id.button_assets);
        buttonAssets.setOnClickListener(v -> {
            // 자산 버튼이 클릭되었을 때 실행되는 동작 구현
            //Toast.makeText(MainActivity.this, "자산 버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show();

            // StatisticsActivity로 전환하는 Intent 생성
            Intent intent = new Intent(MainActivity.this, Data_Show_Activity.class);

            // 액티비티 전환
            startActivity(intent);

        });

        incomeBtn = findViewById(R.id.income_btn);
        expenseBtn = findViewById(R.id.expense_btn);
        incomeCategoryTextView = findViewById(R.id.income_category_textview);
        expenseCategoryTextView = findViewById(R.id.expense_category_textview);


        incomeBtn.setOnClickListener(v -> {
            // 카테고리 선택 다이얼로그 띄우기
            showDialog("income");

        });

        expenseBtn.setOnClickListener(v -> {
            // 카테고리 선택 다이얼로그 띄우기
            showDialog("expense");

        });
    }

    private void showDialog(String type) {
        final String[] items;
        if (type.equals("income")) {
            items = getResources().getStringArray(R.array.income_categories);
        } else { // type.equals("expense")
            items = getResources().getStringArray(R.array.expense_categories);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("카테고리 선택");
        builder.setItems(items, (dialog, which) -> {
            String category = items[which];
            // 선택한 카테고리를 화면에 표시
            if (type.equals("income")) {
                incomeCategoryTextView.setText(category);
            } else { // type.equals("expense")
                expenseCategoryTextView.setText(category);
            }

            // 선택한 카테고리를 기반으로 Sub_Expense_Activity로 전환하는 Intent 생성
            Intent intent = new Intent(MainActivity.this, Sub_Expense_Activity.class);
            intent.putExtra("category", category); // 선택한 카테고리를 인텐트에 추가

            // 액티비티 전환
            startActivity(intent);
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}