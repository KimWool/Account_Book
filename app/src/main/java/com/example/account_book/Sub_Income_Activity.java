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

        aSwitch = findViewById(R.id.fixed_income);

        Spinner income_spinner = findViewById(R.id.income_spinner);

        amount_input = findViewById(R.id.amount_input);
        content_input = findViewById(R.id.content_input);

        Button insert_button = findViewById(R.id.insert_button);

        income_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        insert_button.setOnClickListener(v -> {
            String strIncome = income_spinner.getSelectedItem().toString();
            String strAmountText = amount_input.getText().toString();
            Integer strAmount = Integer.parseInt(strAmountText);
            String strContent = content_input.getText().toString();

            boolean switchState = aSwitch.isChecked();

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Data");
            String key = databaseReference.push().getKey();
            assert key != null;
            DatabaseReference newDataRef = databaseReference.child(key);
            newDataRef.child("category").setValue(strIncome);
            newDataRef.child("amount").setValue(strAmount);
            newDataRef.child("content").setValue(strContent);

            if (switchState) {
                // Switch is ON, perform action A
                newDataRef.child("fixed_data").setValue("고정 수입");
            } else {
                // Switch is OFF, perform action B
                newDataRef.child("fixed_data").setValue("변동 수입");
            }

            Intent intent = new Intent(Sub_Income_Activity.this, Data_Show_Activity.class);
            startActivity(intent);
        });

    }
}