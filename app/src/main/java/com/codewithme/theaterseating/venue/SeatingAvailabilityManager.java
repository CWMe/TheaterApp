package com.codewithme.theaterseating.venue;

import com.codewithme.theaterseating.SeatSelectionActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cdlee on 2/15/18.
 */

public class SeatingAvailabilityManager {
    private static Map<String, List<Integer>> theaters;

    public static void init() {
        if (theaters == null) theaters = new HashMap<>();
    }

    public static void reserveSeat(String key, int seatNumber) {
        theaters.get(key).add(seatNumber);
    }

    public static void addPurchases(String key, List<Integer> purchasedSeats) {
        theaters.put(key, purchasedSeats);
    }


    public static List<Integer> getPurchasedSeats(String key) {

        if (theaters.get(key) != null && theaters.get(key).size() != 0) {
            return theaters.get(key);

        } else {
            return new ArrayList<Integer>();
        }
    }
}
