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
    private Spinner typeSpinner;
    private Spinner in_cateSpinner;
    private Spinner ex_cateSpinner;

    private String selectedYear;
    private String selectedMonth;
    private String selectedType;
    private String selectedInCategory;
    private String selectedExCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_check);
        // 년도, 월, 고정/변동, 카테고리를 선택하는 스피너
        yearSpinner = (Spinner) findViewById(R.id.spinner1);
        monthSpinner = (Spinner) findViewById(R.id.spinner2);
        typeSpinner = (Spinner) findViewById(R.id.spinner3);
        in_cateSpinner = (Spinner) findViewById(R.id.spinner4);
        ex_cateSpinner = (Spinner) findViewById(R.id.spinner5);

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedYear = parent.getItemAtPosition(position).toString();
                //Data(selectedYear, getselectedYear());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedYear = "";
            }
        });

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMonth = parent.getItemAtPosition(position).toString();
                //Data(selectedMonth, getselectedMonth());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedMonth = "";
            }
        });

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedType = parent.getItemAtPosition(position).toString();
                //Data(selectedType, getselectedType());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedType = "";

            }
        });
        in_cateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedInCategory = parent.getItemAtPosition(position).toString();
                //Data(selectedcate, getselectedcate());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedInCategory = "";
            }
        });

        ex_cateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedExCategory = parent.getItemAtPosition(position).toString();
                //Data(selectedcate, getCategory());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedExCategory = "";
            }
        });

        btn_move = findViewById(R.id.btn_move); // 클릭 이벤트로 인한 다른 액티비티 이동
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sub_Check.this, Data_Show_Activity.class);
                intent.putExtra("selectedYear", yearSpinner.getSelectedItem().toString());
                intent.putExtra("selectedMonth", monthSpinner.getSelectedItem().toString());
                intent.putExtra("selectedType", typeSpinner.getSelectedItem().toString());
                intent.putExtra("selectedIn_Category", in_cateSpinner.getSelectedItem().toString());
                intent.putExtra("selectedEx_Category", ex_cateSpinner.getSelectedItem().toString());
                startActivity(intent); //액티비티 이동
            }
        });
    }
}