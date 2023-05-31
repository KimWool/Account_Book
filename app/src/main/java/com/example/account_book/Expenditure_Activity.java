package com.example.account_book;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Expenditure_Activity extends AppCompatActivity {
    private TextView monthTextView;
    private TextView expensesTextView;
    private TextView incomeTextView;
    private BarChart barChart;
    private Calendar calendar;

    private List<Integer> expensesList;
    private List<Integer> incomeList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure);

        // Firebase 초기화
        FirebaseApp.initializeApp(this);

        // XML에서 해당하는 뷰들을 찾아와 변수에 할당
        monthTextView = findViewById(R.id.monthTextView);
        Button previousButton = findViewById(R.id.previousButton);
        Button nextButton = findViewById(R.id.nextButton);
        expensesTextView = findViewById(R.id.expensesTextView);
        incomeTextView = findViewById(R.id.incomeTextView);
        barChart = findViewById(R.id.bar_chart);

        // Calendar 객체를 생성하고 현재 날짜로 설정
        calendar = Calendar.getInstance();

        // 초기에 현재 달을 표시
        updateMonthText();

        // previousButton 클릭 시 이전 달로 변경
        previousButton.setOnClickListener(v -> {
            calendar.add(Calendar.MONTH, -1);
            updateMonthText();
            fetchDataFromFirebase();
        });

        // nextButton 클릭 시 다음 달로 변경
        nextButton.setOnClickListener(v -> {
            calendar.add(Calendar.MONTH, 1);
            updateMonthText();
            fetchDataFromFirebase();
        });

        // BarChart 데이터 설정
        setupBarChart();

        // Firebase에서 데이터 가져오기
        fetchDataFromFirebase();
    }

    // monthTextView에 현재 달을 표시하는 메서드
    private void updateMonthText() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM", Locale.getDefault());
        String monthString = sdf.format(calendar.getTime());
        monthTextView.setText(monthString);
    }

    // BarChart 데이터 설정 메서드
    private void setupBarChart() {
        List<BarEntry> entries = new ArrayList<>();

        // BarDataSet 생성 및 속성 설정
        BarDataSet dataSet = new BarDataSet(entries, "금액");
        dataSet.setColors(new int[]{R.color.expenses_color, R.color.income_color}, this);

        // BarData 생성 및 dataSet 설정
        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.5f);

        // BarChart 속성 설정
        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.setDrawValueAboveBar(true);
        barChart.getDescription().setEnabled(false);  // 그래프 설명 표시 비활성화

        // x축 설정
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                // x축 레이블 설정
                if (value == 0)
                    return "지출";
                else if (value == 1)
                    return "수입";
                else
                    return "";
            }
        });

        // y축 설정
        barChart.getAxisLeft().setAxisMinimum(0f); // y축의 최소값을 0으로 설정
        barChart.getAxisRight().setEnabled(false);  // 오른쪽 y축 비활성화

        // BarChart 갱신
        barChart.invalidate();
    }

    // Firebase에서 데이터 가져오기
    private void fetchDataFromFirebase() {
        String databaseUrl = "https://account-book-889ee-default-rtdb.firebaseio.com/";
        String dataPath = "Data";

        FirebaseDatabase.getInstance(databaseUrl)
                .getReference(dataPath)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    // 수입과 지출에 대한 데이터를 저장하는 리스트
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        expensesList = new ArrayList<>();
                        incomeList = new ArrayList<>();

                        // fixed_data와 amount의 속성 값 가져옴
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            String fixedData = data.child("fixed_data").getValue(String.class);
                            Integer amountInt = data.child("amount").getValue(Integer.class);

                            // amountInt가 null이 아닌 경우에만 처리 진행
                            if (amountInt != null) {
                                int amount = amountInt;

                                // fixedData가 null이 아니고 "지출"을 포함하는 경우 expensesList에 금액을 추가
                                // "수입"을 포함하는 경우에는 incomeList에 금액을 추가
                                if (fixedData != null && fixedData.contains("지출")) {
                                    expensesList.add(amount);
                                } else if (fixedData != null && fixedData.contains("수입")) {
                                    incomeList.add(amount);
                                }
                            }
                        }

                        // expensesList와 incomeList의 총합 계산
                        int expensesTotal = calculateTotal(expensesList);
                        int incomeTotal = calculateTotal(incomeList);

                        // expensesTextView와 incomeTextView에 금액 표시
                        expensesTextView.setText(String.format(Locale.getDefault(), "%7d", expensesTotal));
                        incomeTextView.setText(String.format(Locale.getDefault(), "%7d", incomeTotal));

                        // BarChart에 데이터 추가
                        List<BarEntry> entries = new ArrayList<>();
                        entries.add(new BarEntry(0, expensesTotal));
                        entries.add(new BarEntry(1, incomeTotal));

                        // BarDataSet 생성 및 속성 설정
                        BarDataSet dataSet = new BarDataSet(entries, "금액");
                        dataSet.setColors(new int[]{R.color.expenses_color, R.color.income_color}, Expenditure_Activity.this);
                        dataSet.setValueTextSize(12f);  // 숫자 크기 설정

                        // BarData 생성 및 dataSet 설정
                        BarData barData = new BarData(dataSet);
                        barData.setBarWidth(0.5f);

                        // BarChart에 데이터 설정
                        barChart.setData(barData);
                        barChart.notifyDataSetChanged();
                        barChart.invalidate();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // 데이터 읽기가 실패한 경우 호출되는 콜백 메서드
                        Log.d("Firebase", "Failed to read data: " + databaseError.getMessage());
                    }
                });
    }

    // 리스트의 총합을 계산하는 메서드
    private int calculateTotal(List<Integer> dataList) {
        int total = 0;
        if (dataList != null) {
            for (int value : dataList) {
                total += value;
            }
        }
        return total;
    }
}