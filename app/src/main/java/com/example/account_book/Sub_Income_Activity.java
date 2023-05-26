package com.example.account_book;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class Sub_Income_Activity extends AppCompatActivity {

    LinearLayout container;
    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_income);

        container = findViewById(R.id.container);
        aSwitch = findViewById(R.id.fixed_income);

        aSwitch.setOnClickListener(v -> {
            boolean checked = ((Switch) v).isChecked();

            if(checked){
                LayoutInflater inflater=(LayoutInflater)
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.fixed_income,container,true);

                //이 부분에 fixed_income 실행 액티비티 작성
            }
        });
    }
}