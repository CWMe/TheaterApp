package com.codewithme.theaterseating.venue;

import com.codewithme.theaterseating.TheaterSelectionActivity;
import com.codewithme.theaterseating.Transformable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.List;

/**
 * The Venue class provides a common set of properties to its subclasses, which 
 * modify those properties via the constructor.
 * 
 * This class is abstract to prevent instantiation of it.  Developers should use 
 * its subclasses.
 * 
 * @author bisoldi
 */
public abstract class Venue implements Serializable {

    private int numRows;
    private int numSeats;
    private int groupingSize;
    private int optimumRow;
    private int optimumSeat;
    private String venueType;
    private String name;
    private List<String> purchasedSeats;

    protected Venue(int numRows, int numSeats, int groupingSize, int optimumRow, int optimumSeat) {
        this.numRows = numRows;
        this.numSeats = numSeats;
        this.groupingSize = groupingSize;
        this.optimumRow = optimumRow;
        this.optimumSeat = optimumSeat;
    }

    public Venue() {

    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public int getGroupingSize() {
        return groupingSize;
    }

    public int getOptimumRow() {
        return optimumRow;
    }

    public int getOptimumSeat() {
        return optimumSeat;
    }

    public String getVenueType() {
        return venueType;
    }

    public String getName() {
        return name;
    }

    public List<String> getPurchasedSeats() {
        return purchasedSeats;
    }

    public abstract int getMaxRowDifference();
    public abstract int getMaxSeatDifference();

//    public void transform() {
//        if (seatsTaken == null) {
//            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(TheaterSelectionActivity.ROOT_KEY).child(name);
//
//            databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    GenericTypeIndicator<List<Integer>> t = new GenericTypeIndicator <List<Integer>>() {};
//                    seatsTaken = dataSnapshot.child("seatsTaken").getValue(t);
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//        }
//    }
}
