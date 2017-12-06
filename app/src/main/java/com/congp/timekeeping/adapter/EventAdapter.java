package com.congp.timekeeping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.congp.timekeeping.R;
import com.congp.timekeeping.data.Event;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by congp on 11/24/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    ArrayList<Event> events;
    int day;



    private Context context;
    OnEventClick listener;

    public void setEvents(OnEventClick listener) {
        this.listener = listener;
    }

    public EventAdapter(ArrayList<Event> events, int day) {
        this.events = events;
        this.day = day;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.
                getContext()).inflate(R.layout.item_event, parent, false);
        context = v.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event e = events.get(position);
        holder.tvDate.setText(String.valueOf(e.getDay()));


        if (e.getMonth() == 0) {
            holder.cimvLast.setVisibility(View.VISIBLE);
            holder.tvDate.setFocusable(true);
            holder.tvDate.setClickable(false);
            holder.tvDate.setTextColor(context.getResources().getColor(R.color.fff));
        }
        else if (e.getMonth() == 13) {
            holder.cimvLast.setVisibility(View.GONE);
            holder.tvDate.setFocusable(true);
            holder.tvDate.setClickable(false);
            holder.tvDate.setTextColor(context.getResources().getColor(R.color.fff));
        } else {
            holder.tvDate.setTextColor(context.getResources().getColor(R.color.colorBlack));
            if (e.getDay() < day) {
                holder.cimvLast.setVisibility(View.VISIBLE);
            }
            if (e.getDay() == day) {
                holder.cimv.setVisibility(View.VISIBLE);
            }
            holder.tvDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(e.getId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cimv)
        CircleImageView cimv;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.cimv_last)
        CircleImageView cimvLast;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnEventClick {
        void onClick(String s);
    }
}
