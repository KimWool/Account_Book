package com.example.account_book;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button incomeBtn;
    Button expenseBtn;
    TextView incomeCategoryTextView;
    TextView expenseCategoryTextView;
    TextView purposeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        incomeBtn = findViewById(R.id.income_btn);
        expenseBtn = findViewById(R.id.expense_btn);
        incomeCategoryTextView = findViewById(R.id.income_category_textview);
        expenseCategoryTextView = findViewById(R.id.expense_category_textview);
        purposeTextView = findViewById(R.id.purpose_textview);

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
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}