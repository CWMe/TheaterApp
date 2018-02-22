package com.codewithme.theaterseating.pricing;

public interface PricingModule {
    double priceSeat(int row, int seat);
    BasePricingModule.SHOW_TYPE getShowType();
}
