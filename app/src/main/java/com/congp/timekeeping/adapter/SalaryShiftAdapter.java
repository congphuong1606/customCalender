package com.congp.timekeeping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.congp.timekeeping.R;
import com.congp.timekeeping.data.Shift;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ominext on 12/5/2017.
 */

public class SalaryShiftAdapter extends RecyclerView.Adapter<SalaryShiftAdapter.Holder> {

    private ArrayList<Shift> shifts;
    private Context context;


    public SalaryShiftAdapter(ArrayList<Shift> shifts) {
        this.shifts = shifts;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.
                getContext()).inflate(R.layout.item_salary, parent, false);
        context = v.getContext();
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Shift s = shifts.get(position);
        holder.tvDay.setText(String.valueOf(s.getsDay()));
        holder.tvInTimeSalary.setText(s.getsInTime());
        holder.tvOutTimeSalary.setText(s.getsOutTime());
        holder.tvTotalTimeDate.setText(String.valueOf(s.getsTotalTime()));
        holder.tvNoteSalary.setText(s.getsNote());
    }

    @Override
    public int getItemCount() {
        return shifts.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_day)
        TextView tvDay;
        @BindView(R.id.tv_in_time_salary)
        TextView tvInTimeSalary;
        @BindView(R.id.tv_out_time_salary)
        TextView tvOutTimeSalary;
        @BindView(R.id.tv_total_time_date)
        TextView tvTotalTimeDate;
        @BindView(R.id.tv_note_salary)
        TextView tvNoteSalary;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}
