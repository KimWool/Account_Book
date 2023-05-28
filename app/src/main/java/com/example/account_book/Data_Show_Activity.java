package com.example.account_book;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.melnykov.fab.FloatingActionButton;

public class Data_Show_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_show);

        //리스트 목록 작성
        String[] listString = {"list 1", "list 2", "list 3", "list 4", "list 5", "list 6", "list 7", "list 8", "list 9", "list 10"};

        //리스트 어뎁터 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listString);

        //리스트 뷰에 어뎁터 적용
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        //Floating Action Button을 리스트 뷰에 적용
        FloatingActionButton fab = findViewById(R.id.add_button);
        fab.attachToListView(listView);

        //이벤트 적용
        fab.setOnClickListener(v -> {
            //버튼 클릭 시 이벤트 작성

            Intent intent = new Intent(Data_Show_Activity.this, Data_Entry_Activity.class);
            startActivity(intent);
        });
    }
}