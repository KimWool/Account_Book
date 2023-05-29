package com.example.account_book;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.graphics.Color;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

public class Expenditure_Activity extends AppCompatActivity {

    private TextView monthTextView;
    private TextView expensesTextView;
    private TextView incomeTextView;

    private Calendar calendar;
    private DateFormat monthFormat;

    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure);

        // XML 레이아웃 파일에서 해당 뷰들을 가져옴
        monthTextView = findViewById(R.id.monthTextView);
        expensesTextView = findViewById(R.id.expensesTextView);
        incomeTextView = findViewById(R.id.incomeTextView);

        // 달력 및 월 형식 설정을 위한 변수 초기화
        calendar = Calendar.getInstance();
        monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());

        // 막대 차트 초기화
        barChart = findViewById(R.id.bar_chart);
        setupBarChart();

        // 초기화된 월, 소비 및 수입 데이터를 화면에 업데이트함
        updateMonth();
        updateExpensesForMonth();
        updateIncomeForMonth();

        // 이전 달 버튼 클릭 시 이벤트 처리
        Button previousButton = findViewById(R.id.previousButton);
        previousButton.setOnClickListener(v -> {
            calendar.add(Calendar.MONTH, -1);
            updateMonth();
            updateExpensesForMonth();
            updateIncomeForMonth();
        });

        // 다음 달 버튼 클릭 시 이벤트 처리
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            calendar.add(Calendar.MONTH, 1);
            updateMonth();
            updateExpensesForMonth();
            updateIncomeForMonth();
        });

        // 월별 소비 총액 데이터 (예시)
        int[] monthlyExpenses = new int[12]; // 12개월에 대한 데이터를 담을 배열

        // 월별 소비 데이터 초기화
        for (int i = 0; i < monthlyExpenses.length; i++) {
            monthlyExpenses[i] = getExpensesForMonth(i); // 각 월에 대한 소비값을 메서드로부터 가져옴
        }

        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < monthlyExpenses.length; i++) {
            entries.add(new BarEntry(i, monthlyExpenses[i]));
        }

        // 막대 데이터셋 생성
        BarDataSet dataSet = new BarDataSet(entries, "월별 소비 총액");
        dataSet.setColor(Color.GRAY); // 막대의 색상 (회색)
        dataSet.setDrawValues(true); // 막대 위에 값 표시 활성화

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int monthIndex = (int) value;
                Calendar monthCalendar = Calendar.getInstance();
                monthCalendar.setTime(calendar.getTime());
                monthCalendar.add(Calendar.MONTH, monthIndex);
                return monthFormat.format(monthCalendar.getTime());
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(monthlyExpenses.length); // 보여줄 레이블 수
        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(6); // 한 번에 보여줄 최대 레이블 수

        // y축 설정
        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setGranularity(1f); // 간격 설정
        yAxisLeft.setAxisMinimum(0f); // 최소 값 설정
        // 월별 소비 값 중 최댓값을 구하여 최댓값보다 약간 더 큰 값으로 최대 값 설정
        int maxExpense = getMaxExpense(monthlyExpenses);
        yAxisLeft.setAxisMaximum(maxExpense + 100); // 최대 값 설정

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false); // 오른쪽 y축 비활성화

        barChart.invalidate();

        // TODO: 해당 달의 소비와 수입 데이터를 토대로 expensesTextView, incomeTextView 업데이트
    }

    // 해당 달의 소비값을 업데이트하는 메서드
    private void updateExpensesForMonth() {
        int expenses = getExpensesForMonth(calendar.get(Calendar.MONTH));
        expensesTextView.setText(String.valueOf(expenses));
    }

    // 해당 달의 수입값을 업데이트하는 메서드
    private void updateIncomeForMonth() {
        int income = getIncomeForMonth(calendar.get(Calendar.MONTH));
        incomeTextView.setText(String.valueOf(income));
    }

    // 현재 선택된 달을 업데이트하는 메서드
    private void updateMonth() {
        String monthString = monthFormat.format(calendar.getTime());
        monthTextView.setText(monthString);
    }

    // TODO: 해당 달의 소비값을 반영하는 메서드 구현
    private int getExpensesForMonth(int month) {
        return 1000; // 임시로 1000으로 설정
    }

    // TODO: 해당 달의 수입값을 반영하는 메서드 구현
    private int getIncomeForMonth(int month) {
        return 2000; // 임시로 2000으로 설정
    }

    // 최댓값을 구하는 메서드
    private int getMaxExpense(int[] expenses) {
        int max = Integer.MIN_VALUE;
        for (int expense : expenses) {
            if (expense > max) {
                max = expense;
            }
        }
        return max;
    }

    // 막대 차트 초기 설정 메서드
    private void setupBarChart() {
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.setPinchZoom(true);
        barChart.setScaleEnabled(false);
        barChart.setDragEnabled(true);
    }
}
