package com.codewithme.theaterseating.pricing;
import com.codewithme.theaterseating.venue.Venue;

import java.text.DecimalFormat;

public abstract class BasePricingModule implements PricingModule {
    
    public enum SHOW_TYPE {
        MATINEE,
        HOLIDAY,
        FIRST_SHOWING,
        NORMAL
    };
 
    private static final double DEFAULT_MAX_PRICE = 10.50;
    private static final double DEFAULT_MIN_PRICE = 8.50;
    
    private final Venue venue;
    private final Double maxPrice;
    private final Double minPrice;
    private final Double priceChange;
    
    public BasePricingModule(Venue venue, double maxPrice, double minPrice) {
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.venue = venue;
        priceChange = (maxPrice - minPrice) / ((venue.getMaxRowDifference() + venue.getMaxSeatDifference()));
    }
    
    public BasePricingModule(Venue venue) {
        this.venue = venue;
        priceChange = (DEFAULT_MAX_PRICE - DEFAULT_MIN_PRICE) / ((venue.getMaxRowDifference() + venue.getMaxSeatDifference()));
        maxPrice = null;
        minPrice = null;
    }
    
    @Override
    public double priceSeat(int row, int seat) {
        int rowDiff = Math.abs(venue.getOptimumRow() - row);
        int seatDiff = (int) Math.ceil(Math.abs(venue.getOptimumSeat() - seat) / venue.getGroupingSize());
        System.out.println("Row Diff: " + rowDiff);
        System.out.println("Seat Diff: " + seatDiff);

        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(((double) maxPrice - (double) ((rowDiff + seatDiff) * priceChange))));
    }
    
    @Override
    public abstract SHOW_TYPE getShowType();
}
