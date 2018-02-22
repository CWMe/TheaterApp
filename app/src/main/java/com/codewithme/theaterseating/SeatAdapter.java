package com.codewithme.theaterseating;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.codewithme.theaterseating.venue.Venue;

/**
 * Created by cdlee on 2/13/18.
 */

public class SeatAdapter extends BaseAdapter {
    private final int seatCount;
    private final String name;
    private Context context;
    private int[][] seating;
    private Venue venue;

    public SeatAdapter(Context context, int[][] seating, Venue venue) {
        this.context = context;
        this.seating = seating;
        this.seatCount = venue.getNumSeats();
        this.name = venue.getName();
        this.venue = venue;
    }


    @Override
    public int getCount() {
        return seatCount;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = new View(context);
        view.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
        if (venue.getPurchasedSeats() != null && venue.getPurchasedSeats().size() != 0) {
            for (int i = 0; i < venue.getPurchasedSeats().size(); i++) {
                if (venue.getPurchasedSeats().get(i).equals(String.valueOf(position))) {
                    view.setBackgroundColor(context.getResources().getColor(android.R.color.black));
                    view.setEnabled(false);
                    view.setClickable(false);
                }
            }
        }

        view.setLayoutParams(new GridView.LayoutParams(85, 85));
        return view;
    }
}
