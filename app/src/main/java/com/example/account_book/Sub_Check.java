package com.example.account_book;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Sub_Check extends AppCompatActivity {

    Button btn_search;
    Button btn_move;
    private Spinner yearSpinner;
    private Spinner monthSpinner;
    private Spinner in_exSpinner;
    private Spinner cateSpinner;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_search = findViewById(R.id.btn_search); // 버튼 클릭으로 다이얼로그 표시
        btn_search.setOnClickListener(v -> {
            AlertDialog.Builder ad = new AlertDialog.Builder(Sub_Check.this);
            ad.setIcon(R.mipmap.ic_launcher);
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.check_dialog, null);
            ad.setView(dialogView);
            ad.setTitle("제목");
            // 년도, 월, 수입/지출, 카테고리를 선택하는 스피너
            yearSpinner = findViewById(R.id.spinner1);
            monthSpinner = findViewById(R.id.spinner2);
            in_exSpinner = findViewById(R.id.spinner3);
            cateSpinner = findViewById(R.id.spinner4);

            yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //Data(selectedYear, getselectedYear());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //Data(selectedMonth, getselectedMonth());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            in_exSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //Data(selectedType, getselectedType());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            cateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //Data(selectedcate, getselectedcate());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ad.setPositiveButton("확인", (dialog, which) -> {

            });

            ad.setNegativeButton("취소", (dialog, which) -> {

            });
        });

        btn_move = findViewById(R.id.btn_search); // 클릭 이벤트로 인한 다른 액티비티 이동
        btn_move.setOnClickListener(v -> {
            Intent intent = new Intent(Sub_Check.this, Sub_Expense_Activity.class);
            startActivity(intent); //액티비티 이동
        });
    }
}