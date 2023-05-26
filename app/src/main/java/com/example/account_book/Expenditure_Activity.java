package com.example.account_book;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Expenditure_Activity extends AppCompatActivity {

    private TextView monthTextView;
    private TextView expensesTextView;
    private TextView incomeTextView;

    private Calendar calendar;
    private DateFormat monthFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure);

        monthTextView = findViewById(R.id.monthTextView);
        expensesTextView = findViewById(R.id.expensesTextView);
        incomeTextView = findViewById(R.id.incomeTextView);

        calendar = Calendar.getInstance();
        monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());

        updateMonth();
        updateExpensesForMonth();
        updateIncomeForMonth();

        Button previousButton = findViewById(R.id.previousButton);
        previousButton.setOnClickListener(v -> {
            calendar.add(Calendar.MONTH, -1);
            updateMonth();
            updateExpensesForMonth();
            updateIncomeForMonth();
        });

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            calendar.add(Calendar.MONTH, 1);
            updateMonth();
            updateExpensesForMonth();
            updateIncomeForMonth();
        });

        // TODO: 해당 달의 소비와 수입 데이터를 토대로 expensesTextView, incomeTextView 업데이트
    }

    private void updateExpensesForMonth() {
        int expenses = getExpensesForMonth(calendar.get(Calendar.MONTH));
        expensesTextView.setText(String.valueOf(expenses));
    }

    private void updateIncomeForMonth() {
        int income = getIncomeForMonth(calendar.get(Calendar.MONTH));
        incomeTextView.setText(String.valueOf(income));
    }

    private void updateMonth() {
        String monthString = monthFormat.format(calendar.getTime());
        monthTextView.setText(monthString);
    }

    // TODO: 해당 달의 소비값을 반영하는 메서드 구현
    private int getExpensesForMonth(int month) {
        return 1000; // 임시로 1000으로 설정
    }

    // TODO: 해당 달의 수입값을 반영하는 메서드 구현
    private int getIncomeForMonth(int month) {
        return 2000; // 임시로 2000으로 설정
    }
}