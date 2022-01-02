package models.airline.airlineTrip.airlineTripState;

import models.airline.airlineTrip.AirlineTrip;

public class EndedState extends AirlineTripState {

    @Override
    public void editAirlineTrip(AirlineTrip airlineTrip) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean cancelAirlineTrip(AirlineTrip airlineTrip) {
        System.out.println("cannot cancel an endded airline trip");
        return false;
    }

    @Override
    public void requestToCancelAirlineTrip(AirlineTrip airlineTrip) {
        // TODO Auto-generated method stub
        
    }
    
}
