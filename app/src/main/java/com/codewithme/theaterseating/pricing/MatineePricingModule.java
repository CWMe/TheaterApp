package com.codewithme.theaterseating.pricing;

import com.codewithme.theaterseating.venue.Venue;

public class MatineePricingModule extends BasePricingModule {
    
    private static final Double MAX_PRICE = 7.50;
    private static final Double MIN_PRICE = 4.50;

    public MatineePricingModule(Venue venue) {
        super(venue, MAX_PRICE, MIN_PRICE);
    }
    
    @Override
    public SHOW_TYPE getShowType() {
        return SHOW_TYPE.MATINEE;
    }
    
}
