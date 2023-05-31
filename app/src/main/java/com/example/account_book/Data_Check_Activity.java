package com.example.account_book;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Data_Check_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Data> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_show);

        // Sub_Check에서 값을 받아온다
        Intent intent1 = getIntent();
        String selectedYear = intent1.getStringExtra("selectedYear");
        String selectedMonth = intent1.getStringExtra("selectedMonth");
        String selectedType = intent1.getStringExtra("selectedType");
        String selectedInCategory = intent1.getStringExtra("selectedIn_Category");
        String selectedExCategory = intent1.getStringExtra("selectedEx_Category");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("Data");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터 받아오는 곳
                arrayList.clear();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Data data = childSnapshot.getValue(Data.class);
                    // fixedData에 데이터베이스의 fixed_data에 해당하는 값 저장
                    String fixedData = childSnapshot.child("fixed_data").getValue(String.class);
                    // category에 데이터베이스의 category에 해당하는 값 저장
                    String category = childSnapshot.child("category").getValue(String.class);


                    // fixedData가 고정 수입이거나 고정 지출일 때 조회값이 고정인 경우
                    if (fixedData != null) {
                        if (selectedType.equals("고정") && (fixedData.equals("고정 수입") || fixedData.equals("고정 지출"))) {
                            // category가 조회한 수입 카테고리와 일치하는 경우 데이터 보여줌
                            if (category != null && (category.equals(selectedInCategory))){
                                arrayList.add(data);
                            }
                            // fixedDate 변동 수입이거나 변동 지출일 때 조회값이 변동일 경우
                        } else if (selectedType.equals("변동") && (fixedData.equals("변동 수입") || fixedData.equals(("변동 지출")))) {
                            // category가 조회한 지출 카테고리와 일치하는 경우 데이터 보여줌
                            if (category != null && category.equals(selectedExCategory)){
                                arrayList.add(data);
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Data_Show_Activity", String.valueOf(databaseError.toException()));
            }
        });



        adapter = new CustomAdapter(arrayList, this);
        recyclerView.setAdapter(adapter); //recyclerView에 adapter 연결
    }
}