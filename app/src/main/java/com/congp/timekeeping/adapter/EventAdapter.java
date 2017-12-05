package com.congp.timekeeping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.congp.timekeeping.R;
import com.congp.timekeeping.data.Event;

import java.util.ArrayList;

/**
 * Created by congp on 11/24/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    ArrayList<Event> events;
    private Context context;
    OnEventClick listener;
    public void setEvents(OnEventClick listener){
        this.listener=listener;
    }

    public EventAdapter(ArrayList<Event> events) {
        this.events = events;
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
        holder.tv.setText(String.valueOf(e.getDay()));
        if(e.getMonth()==0){
            holder.tv.setFocusable(true);
            holder.tv.setClickable(false);
            holder.tv.setTextColor(context.getResources().getColor(R.color.fff));
        }else {
            holder.tv.setTextColor(context.getResources().getColor(R.color.colorBlack));
            holder.tv.setOnClickListener(new View.OnClickListener() {
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
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }
    public interface OnEventClick{
        void onClick(String s);
    }
}
