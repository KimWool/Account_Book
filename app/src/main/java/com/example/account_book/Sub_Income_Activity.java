package com.example.account_book;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class Sub_Income_Activity extends AppCompatActivity {

    LinearLayout container;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch aSwitch;

    private Spinner fixed_income_spinner;

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
                fixed_income_spinner = findViewById(R.id.fixed_income_spinner);

                fixed_income_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //값이 선택 되었을 때
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        //값이 선택되지 않았을 때
                    }
                });
            }
        });
    }
}