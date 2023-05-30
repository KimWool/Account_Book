package com.example.account_book;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sub_Income_Activity extends AppCompatActivity {

    EditText amount_input;
    EditText content_input;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_income);

        aSwitch = findViewById(R.id.fixed_income); //Switch 구성 요소 초기화

        Spinner income_spinner = findViewById(R.id.income_spinner); //Spinner 구성 요소 초기화

        amount_input = findViewById(R.id.amount_input); //금액 입력에 대한 EditText 구성 요소 초기화
        content_input = findViewById(R.id.content_input); //내용 입력을 위한 EditText 구성 요소 초기화

        Button insert_button = findViewById(R.id.insert_button); //Button 구성 요소 초기화

        income_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        insert_button.setOnClickListener(v -> { //Insert_button에 대한 OnClickListener
            String strIncome = income_spinner.getSelectedItem().toString(); //income_spinner에서 선택한 항목을 문자열로 변환
            String strAmountText = amount_input.getText().toString(); //입력된 금액을 문자열로 변환
            Integer strAmount = Integer.parseInt(strAmountText); //문자열을 정수로 변환
            String strContent = content_input.getText().toString(); //입력된 내용을 문자열로 변환

            boolean switchState = aSwitch.isChecked(); //Switch의 On/Off 상태 확인

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Data");
            //Firebase 실시간 데이터베이스의 "데이터" 노드에 대한 참조 가져오기
            String key = databaseReference.push().getKey(); //새 항목에 대한 고유 키 생성
            assert key != null;
            DatabaseReference newDataRef = databaseReference.child(key);
            //생성된 키 아래에 새 자식 항목 만들기
            newDataRef.child("category").setValue(strIncome);
            newDataRef.child("amount").setValue(strAmount);
            newDataRef.child("content").setValue(strContent);
            //각 항목에 데이터 저장

            if (switchState) { //Switch 값에 따라 저장할 데이터 지정
                newDataRef.child("fixed_data").setValue("고정 수입");
            } else {
                newDataRef.child("fixed_data").setValue("변동 수입");
            }

            Intent intent = new Intent(Sub_Income_Activity.this, Data_Show_Activity.class);
            startActivity(intent);
        });

    }
}