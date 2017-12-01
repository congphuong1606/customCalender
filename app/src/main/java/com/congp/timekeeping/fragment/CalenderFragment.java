package com.congp.timekeeping.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.congp.timekeeping.R;
import com.congp.timekeeping.activity.CustomCalActivity;
import com.congp.timekeeping.activity.MainActivity;
import com.congp.timekeeping.activity.NoteActivity;
import com.congp.timekeeping.adapter.EventAdapter;
import com.congp.timekeeping.data.Event;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class CalenderFragment extends Fragment  implements EventAdapter.OnEventClick{

RecyclerView recyclerView;
    EventAdapter adapter;
    ArrayList<Event > events;
    public static CalenderFragment newInstance() {
        CalenderFragment fragment = new CalenderFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_calender, container, false);
        events=new ArrayList<>();

        recyclerView=(RecyclerView)v.findViewById(R.id.rcv_event);
        GridLayoutManager layoutManager =
                new GridLayoutManager(getActivity(), 7);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter= new EventAdapter(events);
        adapter.setEvents(this::onClick);
        recyclerView.setAdapter(adapter);
        Calendar calendar=Calendar.getInstance();
        DateFormat sdf = new SimpleDateFormat("EEEE");
        DateFormat yfm = new SimpleDateFormat("yyyy");
        DateFormat mfm = new SimpleDateFormat("MM");
        DateFormat dfm = new SimpleDateFormat("dd");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        int  year = Integer.parseInt(yfm.format(d));
        int  month = Integer.parseInt(mfm.format(d));
        int  day = Integer.parseInt(dfm.format(d));
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DATE,day);
        int lastDayOfw=dayNameComparison(dayOfTheWeek);
        int daysLastMonth=getDaysInMonthInPresentYear(month-2);
        int daysInPresentMonth=getDaysInMonthInPresentYear(getA(month-1));
        events.clear();
        for(int a=(daysLastMonth-lastDayOfw+1);a<=daysLastMonth;a++){
           events.add(new Event(a+"+"+getA(month-1)+"+"+year,a,0,0));
        }
        for(int i=1;i<=daysInPresentMonth;i++){
            events.add(new Event(i+"+"+month+"+"+year,i,month,year));
        }
        for(int b=1;b<8;b++){
            events.add(new Event(b+"+"+getA(month+1)+"+"+year,b,0,0));
            if(events.size()==42){
                break;
            }
        }

        adapter.notifyDataSetChanged();



        Toast.makeText(getContext(),String.valueOf(dayNameComparison(dayOfTheWeek)),Toast.LENGTH_SHORT).show();
        return v;
    }

    private int getA(int i) {
        int A;
        if(i==13){
            A=1;
        }
        else if((i)==0){
            A=12;
        }else A=i;
        return A;
    }

    public static int getDaysInMonthInPresentYear(int monthNumber)
    {
        int days=0;
        if(monthNumber>=0 && monthNumber<12){
            try
            {
                Calendar calendar = Calendar.getInstance();
                int date = 1;
                int year = calendar.get(Calendar.YEAR);
                calendar.set(year, monthNumber, date);
                days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            } catch (Exception e)
            {
                if(e!=null)
                    e.printStackTrace();
            }
        }

        return days;
    }
    public  int dayNameComparison(String dayName)
    {
        DateFormatSymbols objDaySymbol = new DateFormatSymbols();
        String symbolDayNames[] = objDaySymbol.getWeekdays();
        for (int countDayname = 0; countDayname < symbolDayNames.length; countDayname++)
        {
            if(dayName.equalsIgnoreCase(symbolDayNames[countDayname]))
            {
                System.out.println(dayName +" = " + symbolDayNames[countDayname]);
                switch (countDayname){
                    case 7:
                        return 6;

                    case 6:
                        return 6;

                    case 5:
                        return 6;

                    case 4:
                        return 6;

                    case 3:
                        return 6;

                    case 2:
                        return 6;

                    case 1:
                        return 6;


                }

            }
        }
        return 0;
    }
    @Override
    public void onClick(String s) {
        Intent intent=new Intent(((CustomCalActivity) getContext()), NoteActivity.class);
        intent.putExtra("sDate",s);
        startActivity(intent);
    }

}
