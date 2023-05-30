package com.example.account_book;

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
        String selectedInCategory = intent1.getStringExtra("selectedin_Category");
        String selectedExCategory = intent1.getStringExtra("selectedex_Category");

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
                arrayList.clear();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                    Data data = childSnapshot.getValue(Data.class);
                    arrayList.add(data);
                    if (/*selectedYear.equals(data.getYear()) &&
                            selectedMonth.equals(data.getMonth()) &&*/
                            selectedType.equals(data.getFixed_data()) &&
                            (selectedInCategory.equals(data.getCategory()) ||
                                    selectedExCategory.equals(data.getCategory()))) {
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