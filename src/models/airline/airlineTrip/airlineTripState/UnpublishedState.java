package models.airline.airlineTrip.airlineTripState;

import models.airline.airlineTrip.AirlineTrip;

public class UnpublishedState extends AirlineTripState {

    @Override
    public void editAirlineTrip(AirlineTrip airlineTrip) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean cancelAirlineTrip(AirlineTrip airlineTrip) {
        return airlineTrip.getDataMapper().removeAirlineTrip(airlineTrip.getAirlineTripID());
    }

    @Override
    public void requestToCancelAirlineTrip(AirlineTrip airlineTrip) {
        // TODO Auto-generated method stub
        
    }
    
}
