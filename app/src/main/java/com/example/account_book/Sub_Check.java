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

    Button btn_search;
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
        btn_search = (Button) findViewById(R.id.btn_search); // 버튼 클릭으로 다이얼로그 표시
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(Sub_Check.this);
                ad.setIcon(R.mipmap.ic_launcher);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.check_dialog, null)
                ad.setView(dialogView);
                ad.setTitle("제목");
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
                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
            }
        });

        btn_move = findViewById(R.id.btn_search); // 클릭 이벤트로 인한 다른 액티비티 이동
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sub_Check.this, Sub_Expense_Activity.class);
                startActivity(intent); //액티비티 이동
            }
        });
    }
}