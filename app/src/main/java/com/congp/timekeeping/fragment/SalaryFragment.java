package com.congp.timekeeping.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.congp.timekeeping.DatabaseHandler;
import com.congp.timekeeping.R;
import com.congp.timekeeping.data.Shift;

import java.util.ArrayList;

public class SalaryFragment extends Fragment {
    String month;
    ArrayList<Shift> shifts;
    double totalTimeOfMonth=0;
    private DatabaseHandler databaseHandler;
    private View v;

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
        databaseHandler = new DatabaseHandler(v.getContext());
        month = CalenderFragment.getMoth();
        shifts = new ArrayList<>();
        setList(month);
        return v;
    }

    private void setList(String m) {
        shifts.clear();
        totalTimeOfMonth=0;
        shifts.addAll(databaseHandler.getAllShiftOfMoth(m));
        for(Shift shift:shifts){
            totalTimeOfMonth=totalTimeOfMonth+shift.getsTotalTime();
        }
    }


}
