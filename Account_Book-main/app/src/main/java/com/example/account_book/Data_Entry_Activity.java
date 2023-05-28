package com.example.account_book;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Data_Entry_Activity extends AppCompatActivity {

    LinearLayout bottom_layer;
    Button income_button;
    Button expense_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        bottom_layer = findViewById(R.id.bottom_layer);

        income_button = findViewById(R.id.income_button);
        income_button.setOnClickListener(v -> {
            LayoutInflater inflater = (LayoutInflater) //레이아웃 인플레이터를 활용하여 인플레이터 객체를 생성한다.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE); //getSystemService 메서드를 활용하여 인플레이터 객체를 참조한 후 사용한다.
            inflater.inflate(R.layout.activity_sub_income, bottom_layer, true); //inflater.inflate(삽입할 레이아웃, 레이아웃을 삽입할 곳, true)

            //이 부분에 activity_sub_income 실행 액티비티 작성
        });

        expense_button = findViewById(R.id.expense_button);
        expense_button.setOnClickListener(v -> {
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.activity_sub_expense, bottom_layer, true);

            //이 부분에 activity_sub_expense 실행 액티비티 작성
        });
    }
}