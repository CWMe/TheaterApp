package com.codewithme.theaterseating.pricing;

import com.codewithme.theaterseating.venue.Venue;

public class FirstShowingPricingModule extends BasePricingModule {

    public FirstShowingPricingModule(Venue venue) {
        super(venue);
    }

    @Override
    public SHOW_TYPE getShowType() {
        return SHOW_TYPE.FIRST_SHOWING;
    }
    
    @Override
    public double priceSeat(int row, int seat) {
        return super.priceSeat(row, seat) * 5.0;
    }
    
}
