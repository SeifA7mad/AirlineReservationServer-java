package models.airline.airlineTrip.airlineTripState;

import models.airline.airlineTrip.AirlineTrip;

public class PublishedState extends AirlineTripState {

    @Override
    public void editAirlineTrip(AirlineTrip airlineTrip) {

    }

    @Override
    public boolean cancelAirlineTrip(AirlineTrip airlineTrip) {
        
        return false;
    }

    @Override
    public void requestToCancelAirlineTrip(AirlineTrip airlineTrip) {
        // TODO Auto-generated method stub
        
    }
    
}
