package com.example.account_book;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Sub_Check extends AppCompatActivity {
    Button btn_move;
    private Spinner yearSpinner;
    private Spinner monthSpinner;
    private Spinner in_exSpinner;
    private Spinner cateSpinner;

    private String str;
    private ListView transactionListView;
    private int selectedYear;
    private int selectedMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_check);
        // 년도, 월, 수입/지출, 카테고리를 선택하는 스피너
        yearSpinner = (Spinner) findViewById(R.id.spinner1);
        monthSpinner = (Spinner) findViewById(R.id.spinner2);
        in_exSpinner = (Spinner) findViewById(R.id.spinner3);
        cateSpinner = (Spinner) findViewById(R.id.spinner4);

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedYear = parent.getItemAtPosition(position).toString();
                //Data(selectedYear, getselectedYear());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMonth = parent.getItemAtPosition(position).toString();
                //Data(selectedMonth, getselectedMonth());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        in_exSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedType = parent.getItemAtPosition(position).toString();
                //Data(selectedType, getselectedType());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedcate = parent.getItemAtPosition(position).toString();
                //Data(selectedcate, getselectedcate());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_move = findViewById(R.id.btn_move); // 클릭 이벤트로 인한 다른 액티비티 이동
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sub_Check.this, Sub_Expense_Activity.class);
                startActivity(intent); //액티비티 이동
            }
        });
    }
}