package com.example.account_book;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

public class Data_Show_Activity extends AppCompatActivity {

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
                    String fixedData = childSnapshot.child("fixed_data").getValue(String.class);
                    String category = childSnapshot.child("category").getValue(String.class);


                    // selectedIn_Category나 selectedEx_Category와 같은 값인 경우
                    if (fixedData != null) {
                        if (selectedType.equals("고정") && (fixedData.equals("고정 수입") || fixedData.equals("고정 지출"))) {
                            arrayList.add(data);
                        } else if (selectedType.equals("변동") && (fixedData.equals("변동 수입"))) {
                            arrayList.add(data);
                        }
                    }
                    else if (category != null && (category.equals(selectedInCategory) || category.equals(selectedExCategory))) {
                        // Data 객체를 ArrayList에 추가
                        arrayList.add(data);
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

        //Floating Action Button을 리스트 뷰에 적용
        FloatingActionButton fab = findViewById(R.id.add_button);
        //fab.attachToListView(recyclerView);

        //이벤트 적용
        fab.setOnClickListener(v -> {
            //버튼 클릭 시 이벤트
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("추가할 항목 선택");
            builder.setPositiveButton("지출", (dialog, which) -> {
                Intent intent = new Intent(Data_Show_Activity.this, Sub_Expense_Activity.class);
                startActivity(intent);
            });
            builder.setNegativeButton("수입", (dialog, which) -> {
                Intent intent = new Intent(Data_Show_Activity.this, Sub_Income_Activity.class);
                startActivity(intent);
            });
            AlertDialog alertD = builder.create();
            alertD.show();
        });
    }
}