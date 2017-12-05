package com.congp.timekeeping.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.congp.timekeeping.DatabaseHandler;
import com.congp.timekeeping.R;
import com.congp.timekeeping.adapter.SalaryShiftAdapter;
import com.congp.timekeeping.adapter.ShiftAdapter;
import com.congp.timekeeping.data.Shift;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SalaryFragment extends Fragment {
    int month_salary, year_salary;

    double totalTimeOfMonth = 0;
    @BindView(R.id.pk_month)
    MaterialNumberPicker pkMonth;
    @BindView(R.id.pk_year)
    MaterialNumberPicker pkYear;
    @BindView(R.id.btn_xem)
    Button btnXem;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.tv_total_time_month)
    TextView tvTotalTimeMonth;
    @BindView(R.id.tv_hangua)
    TextView tvHangua;
    @BindView(R.id.tv_salary)
    TextView tvSalary;
    Unbinder unbinder;
    private DatabaseHandler databaseHandler;
    private View v;
    private ArrayList<Shift> shifts;
    private String monthOfyear;
    private SalaryShiftAdapter adapter;

    public static SalaryFragment newInstance() {
        SalaryFragment fragment = new SalaryFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_salary, container, false);
        unbinder = ButterKnife.bind(this, v);
        shifts = new ArrayList<>();

        databaseHandler = new DatabaseHandler(v.getContext());
        monthOfyear = CalenderFragment.getMoth();
        int month = Integer.parseInt(monthOfyear.split(" năm ")[0]);
        int year = Integer.parseInt(monthOfyear.split(" năm ")[1]);
        pkYear.setValue(year);
        pkMonth.setValue(month);
        rcv.setLayoutManager(new LinearLayoutManager(v.getContext(),
                LinearLayoutManager.VERTICAL, false));
        adapter = new SalaryShiftAdapter(shifts);
        rcv.setAdapter(adapter);
        getList(monthOfyear,month,year);
        return v;


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_xem)
    public void onViewClicked() {
        month_salary = pkMonth.getValue();
        year_salary = pkYear.getValue();
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, year_salary);
//        calendar.set(Calendar.MONTH, month_salary);
        monthOfyear = month_salary + " năm " + year_salary;
        getList(monthOfyear,month_salary,year_salary);
    }

    private void getList(String monthOfyea,int month, int year) {
        shifts.clear();
//        int daysInPresentMonth = getDaysInMonthInPresentYear(month_salary-1);

        int daysInPresentMonth = getDaysInMonthInPresentYear(month-1, year);
        for (int i = 1; i <=daysInPresentMonth; i++) {
            ArrayList<Shift> sD = new ArrayList<>();
            String sDate = "Ngày " + i + " tháng " + monthOfyear;
            sD = (ArrayList<Shift>) databaseHandler.getListShiftofDay(sDate);
            if (sD.size() > 0) {
                for (Shift s : sD) {
                    shifts.add(new Shift(i, s.getsInTime(), s.getsOutTime(), s.getsTotalTime(), s.getsNote()));
                }
                sD.clear();
            } else shifts.add(new Shift(i, "", "", 0.0, "Nghỉ"));
        }
        adapter.notifyDataSetChanged();
        rcv.smoothScrollToPosition(0);
        Toast.makeText(v.getContext(), String.valueOf(daysInPresentMonth), Toast.LENGTH_LONG).show();
    }

    private int getDaysInMonth(int month_salary, int year_salary) {
        int iDay = 1;
        Calendar mycal = new GregorianCalendar(year_salary, getA(month_salary), iDay);
        return mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private static int getDaysInMonthInPresentYear(int monthNumber,int year) {
        int days = 0;
        if (monthNumber >= 0 && monthNumber < 12) {
            try {
                Calendar calendar = Calendar.getInstance();
                int date = 1;
                calendar.set(year, monthNumber, date);
                days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            } catch (Exception e) {
                if (e != null)
                    e.printStackTrace();
            }
        }

        return days;
    }

    private int getA(int i) {
        int A;
        if (i == 0) {
            A = 12;
        } else A = i;
        return A;
    }
}
