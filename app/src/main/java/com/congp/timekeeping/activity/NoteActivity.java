package com.congp.timekeeping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.congp.timekeeping.DatabaseHandler;
import com.congp.timekeeping.R;
import com.congp.timekeeping.adapter.ShiftAdapter;
import com.congp.timekeeping.data.Shift;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteActivity extends AppCompatActivity implements ShiftAdapter.OnEventClick {
    ShiftAdapter adapter;
    @BindView(R.id.tv_curent_day)
    TextView tvCurentDay;
    @BindView(R.id.rcv_ca)
    RecyclerView rcvCa;
    @BindView(R.id.btn_add_Shift)
    Button btnAddShift;
    @BindView(R.id.tv_in_time)
    TextView tvInTime;
    @BindView(R.id.tv_out_time)
    TextView tvOutTime;
    @BindView(R.id.tv_total_time_ca)
    TextView tvTotalTimeCa;
    @BindView(R.id.tv_note)
    TextView tvNote;
    @BindView(R.id.lnca)
    LinearLayout lnca;
    private String sDate;
    private DatabaseHandler databaseHandler;
    private ArrayList<Shift> shifts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);
        lnca.setVisibility(View.GONE);
        databaseHandler = new DatabaseHandler(this);
        getSDate();
        shifts=getListShiftCurentDay();
      //  databaseHandler.addShift(new Shift(sDate, sDate, 111111, 222222, 1, "hahahahahah"));
        if(shifts.size()>0){
            setShift(shifts.get(shifts.size()-1));
        }
        rcvCa.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        adapter = new ShiftAdapter(shifts);
        adapter.setEvents(this);
        rcvCa.setAdapter(adapter);

    }

    private void setShift(Shift shift) {
        if(!shift.getsNote().isEmpty()){
            tvNote.setText(shift.getsNote());
        }else {
            tvNote.setText("Không có ghi chú !");
        }
        tvInTime.setText(String.valueOf(shift.getsInTime()));
        tvOutTime.setText(String.valueOf(shift.getsOutTime()));
        tvTotalTimeCa.setText(shift.getsTotalTime()+" tiếng");
    }

    private ArrayList<Shift> getListShiftCurentDay() {
        return (ArrayList<Shift>) databaseHandler.getShiftOfDate(sDate);
    }

    private void getSDate() {
        Intent intent = getIntent();
        sDate = intent.getStringExtra("sDate");
        tvCurentDay.setText(sDate);
    }

    @Override
    public void onClick(Shift s) {
         setShift(s);
    }

    @Override
    public void setGV(int size) {
        if(size>0)
        lnca.setVisibility(View.VISIBLE);
        else lnca.setVisibility(View.GONE);
    }

    @OnClick({R.id.tv_in_time, R.id.tv_out_time,R.id.btn_add_Shift, R.id.tv_note})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_in_time:
                break;
            case R.id.tv_out_time:
                break;
            case R.id.btn_add_Shift:
                Intent intent=new Intent(this,AddShiftActivity.class);
                intent.putExtra("sDate",sDate);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_note:
                break;
        }
    }
}
