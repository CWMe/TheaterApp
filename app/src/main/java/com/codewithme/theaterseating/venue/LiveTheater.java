package com.codewithme.theaterseating.venue;

public class LiveTheater extends Venue {

    private static final int GROUPING_SIZE = 2;
    private static final int OPTIMUM_ROW = 1;

    public LiveTheater() {
        //default contstructor for Firebase class mapping
    }

    public LiveTheater(int numRows, int numSeats) {
        super(numRows, numSeats, numRows, OPTIMUM_ROW, numSeats / 2 + 1);
    }

    @Override
    public int getMaxRowDifference() {
        return getNumRows() - 1;
    }

    @Override
    public int getMaxSeatDifference() {
        return Math.round(getNumSeats() / 2 / GROUPING_SIZE);
    }

}
