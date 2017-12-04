package com.congp.timekeeping.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.congp.timekeeping.R;
import com.congp.timekeeping.activity.CustomCalActivity;
import com.congp.timekeeping.activity.NoteActivity;
import com.congp.timekeeping.adapter.EventAdapter;
import com.congp.timekeeping.data.Event;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class CalenderFragment extends Fragment implements EventAdapter.OnEventClick {
    EventAdapter adapter;
    ArrayList<Event> events;
    @BindView(R.id.btn_next_month)
    Button btnNextMonth;
    @BindView(R.id.tv_curent_month)
    TextView tvCurentMonth;
    @BindView(R.id.btn_reback_month)
    Button btnRebackMonth;
    @BindView(R.id.rcv_event)
    RecyclerView recyclerView;
    private static int year;
    private static int month;
    private int day;

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
        View v = inflater.inflate(R.layout.fragment_calender, container, false);
        events = new ArrayList<>();
        ButterKnife.bind(this, v);
        GridLayoutManager layoutManager =
                new GridLayoutManager(getActivity(), 7);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new EventAdapter(events);
        adapter.setEvents(this::onClick);
        recyclerView.setAdapter(adapter);
        Calendar calendar = Calendar.getInstance();
        DateFormat sdf = new SimpleDateFormat("EEEE");
        DateFormat yfm = new SimpleDateFormat("yyyy");
        DateFormat mfm = new SimpleDateFormat("MM");
        DateFormat dfm = new SimpleDateFormat("dd");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        year = Integer.parseInt(yfm.format(d));
        month = Integer.parseInt(mfm.format(d));
        day = Integer.parseInt(dfm.format(d));
        tvCurentMonth.setText("Tháng " + month + " năm " + year);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH, 1);
        String firtDayOfMonth = String.valueOf(date.getTime()).split(" ")[0] + "day";
        int lastDayOfw = dayNameComparison(firtDayOfMonth);
        int daysLastMonth = getDaysInMonthInPresentYear(month - 2);
        int daysInPresentMonth = getDaysInMonthInPresentYear(getA(month - 1));
        events.clear();
        for (int a = (daysLastMonth - lastDayOfw + 1); a <= daysLastMonth; a++) {
            events.add(new Event("Ngày " + a + " tháng " + getA(month - 1) + " năm " + year, a, 0, 0));
        }
        for (int i = 1; i <= daysInPresentMonth; i++) {
            events.add(new Event("Ngày "+i +  " tháng " + month + " năm " + year, i, month, year));
        }
        for (int b = 1; b < 8; b++) {
            events.add(new Event("Ngày "+b +  " tháng " + getA(month + 1) + "+" + year, b, 0, 0));
            if (events.size() == 42) {
                break;
            }
        }
        adapter.notifyDataSetChanged();
        return v;
    }
     public static String getMoth(){
        return month+ " năm " +year;
      }
    private int getA(int i) {
        int A;
        if (i == 13) {
            A = 1;
        } else if ((i) == 0) {
            A = 12;
        } else A = i;
        return A;
    }

    public static int getDaysInMonthInPresentYear(int monthNumber) {
        int days = 0;
        if (monthNumber >= 0 && monthNumber < 12) {
            try {
                Calendar calendar = Calendar.getInstance();
                int date = 1;
                int year = calendar.get(Calendar.YEAR);
                calendar.set(year, monthNumber, date);
                days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            } catch (Exception e) {
                if (e != null)
                    e.printStackTrace();
            }
        }

        return days;
    }

    public int dayNameComparison(String dayName) {
        DateFormatSymbols objDaySymbol = new DateFormatSymbols();
        String symbolDayNames[] = objDaySymbol.getWeekdays();
        for (int countDayname = 0; countDayname < symbolDayNames.length; countDayname++) {
            if (dayName.equalsIgnoreCase(symbolDayNames[countDayname])) {
                System.out.println(dayName + " = " + symbolDayNames[countDayname]);
                switch (countDayname) {
                    case 7:
                        return 6;

                    case 6:
                        return 5;

                    case 5:
                        return 4;

                    case 4:
                        return 3;

                    case 3:
                        return 2;

                    case 2:
                        return 1;

                    case 1:
                        return 0;


                }

            }
        }
        return 0;
    }

    @Override
    public void onClick(String s) {
        Intent intent = new Intent(((CustomCalActivity) getContext()), NoteActivity.class);
        intent.putExtra("sDate", s);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
