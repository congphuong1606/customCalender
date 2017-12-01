package com.congp.timekeeping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.congp.timekeeping.R;
import com.congp.timekeeping.data.Shift;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by congp on 11/26/2017.
 */

public class ShiftAdapter extends RecyclerView.Adapter<ShiftAdapter.ShiftHolder> {
    ArrayList<Shift> shifts;


    private Context context;
    OnEventClick listener;

    public void setEvents(OnEventClick listener) {
        this.listener = listener;
    }

    public ShiftAdapter(ArrayList<Shift> shifts) {
        this.shifts = shifts;
    }

    @Override
    public ShiftHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.
                getContext()).inflate(R.layout.item_button, parent, false);
        context = v.getContext();
        return new ShiftHolder(v);
    }

    @Override
    public void onBindViewHolder(ShiftHolder holder, int position) {
        Shift s = shifts.get(position);
        listener.setGV(shifts.size());

        holder.btnShift.setText(s.getsName());
        holder.btnShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(s);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shifts.size();
    }


    public class ShiftHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.btn_shift)
        Button btnShift;

        public ShiftHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface OnEventClick {
        void onClick(Shift s);

        void setGV(int size);
    }
}
