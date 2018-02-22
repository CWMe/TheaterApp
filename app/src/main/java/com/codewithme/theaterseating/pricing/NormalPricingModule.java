package com.codewithme.theaterseating.pricing;


import com.codewithme.theaterseating.venue.Venue;

public class NormalPricingModule extends BasePricingModule {

    public NormalPricingModule(Venue venue) {
        super(venue);
    }
    
    @Override
    public SHOW_TYPE getShowType() {
        return SHOW_TYPE.NORMAL;
    }
    
}
