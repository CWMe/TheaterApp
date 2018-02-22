package com.codewithme.theaterseating;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.codewithme.theaterseating.venue.Venue;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class SeatSelectionActivity extends AppCompatActivity {
    private Venue venue = null;
    private Button purchaseButton;
    private List<String> selectedSeats;
    private List<String> purchasedSeats = new ArrayList<>();
    private GridView gridView;
    private PagerAdapter adapter;
    private ViewPager pager;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat_selection);
        gridView = findViewById(R.id.theater_grid);

        if (getIntent().getSerializableExtra(TheaterSelectionActivity.VENUE_KEY) != null) {
            venue = (Venue) getIntent().getSerializableExtra(TheaterSelectionActivity.VENUE_KEY);
            purchasedSeats = venue.getPurchasedSeats();
        }

        purchaseButton = findViewById(R.id.purchase_seats);
        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SeatSelectionActivity.this, "Thank you for your purchase",
                        Toast.LENGTH_SHORT).show();
                if (!selectedSeats.isEmpty()) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    for (String seat:selectedSeats) {
                        purchasedSeats.add(seat);
                    }
                    databaseReference.child(TheaterSelectionActivity.ROOT_KEY).child(venue.getName()).child("purchasedSeats").setValue(purchasedSeats);
                }
                Intent intent = new Intent(SeatSelectionActivity.this, TheaterSelectionActivity.class);
                startActivity(intent);
            }
        });

        final int[][] seating = new int[venue.getNumRows()][venue.getNumSeats()];
        setVenue(seating);
    }

    public void setVenue(final int[][] seating) {
        selectedSeats = new ArrayList<>();
        gridView.setNumColumns(venue.getNumRows());
        gridView.setAdapter(new SeatAdapter(getApplicationContext(), seating, venue));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (purchasedSeats != null && purchasedSeats.contains(String.valueOf(position))) {
                    view.setBackgroundColor(getResources().getColor(android.R.color.black));
                    view.setClickable(false);
                    view.setEnabled(false);
                }else  if (!selectedSeats.contains(String.valueOf(position))){
                    selectedSeats.add(String.valueOf(position));
                    view.setBackgroundColor(getResources().getColor(android.R.color.black));
                }else {
                    selectedSeats.remove(selectedSeats.indexOf(String.valueOf(position)));
                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                }
            }
        });
    }
}
