package com.codewithme.theaterseating;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.codewithme.theaterseating.venue.LiveTheater;
import com.codewithme.theaterseating.venue.MovieTheater;
import com.codewithme.theaterseating.venue.SeatingAvailabilityManager;
import com.codewithme.theaterseating.venue.Venue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cdlee on 2/15/18.
 */

public class TheaterSelectionActivity extends AppCompatActivity {
    private Button amcButton, regalButton, amphitheaterButton, pavilionButton;
    private ArrayList<Venue> venues;
    private List<Integer> seatAvailability;
    public static final String VENUE_KEY = "VENUE";
    public static final String ROOT_KEY = "Theaters";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theater_selection);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                venues = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.child(ROOT_KEY).getChildren()) {
                    venues.add(dataSnapshot1.getValue(MovieTheater.class));
                }
                setListeners(venues);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        databaseReference.addValueEventListener(valueEventListener);
    }

    public void setListeners(List<Venue> venues) {
        amcButton = findViewById(R.id.amc_button);
        amcButton.setOnClickListener(new TheaterSelectionListener(venues.get(0)));

        regalButton = findViewById(R.id.regal_button);
        regalButton.setOnClickListener(new TheaterSelectionListener(venues.get(1)));

        amphitheaterButton = findViewById(R.id.amphitheater_button);
        amphitheaterButton.setOnClickListener(new TheaterSelectionListener(venues.get(2)));

        pavilionButton = findViewById(R.id.pavilion_button);
        pavilionButton.setOnClickListener(new TheaterSelectionListener(venues.get(3)));

    }

    public class TheaterSelectionListener implements View.OnClickListener {
        private Venue venue;

        public TheaterSelectionListener(Venue venue) {
            this.venue = venue;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(TheaterSelectionActivity.this, SeatSelectionActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(TheaterSelectionActivity.VENUE_KEY, venue);
            startActivity(intent);
        }
    }

}
