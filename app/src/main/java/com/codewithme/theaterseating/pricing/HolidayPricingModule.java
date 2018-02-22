package com.codewithme.theaterseating.pricing;

import com.codewithme.theaterseating.venue.Venue;

public class HolidayPricingModule extends BasePricingModule {
    
    private static final double MAX_PRICE = 18.50;
    private static final double MIN_PRICE = 12.50;

    public HolidayPricingModule(Venue venue) {
        super(venue, MAX_PRICE, MIN_PRICE);
    }
    
    @Override
    public SHOW_TYPE getShowType() {
        return SHOW_TYPE.HOLIDAY;
    }
    
    @Override
    public double priceSeat(int row, int seat) {
        return super.priceSeat(row, seat) / 2.0;
    }
    
}
