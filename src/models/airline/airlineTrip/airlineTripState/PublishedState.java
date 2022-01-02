package models.airline.airlineTrip.airlineTripState;

import models.airline.airlineTrip.AirlineTrip;

public class PublishedState extends AirlineTripState {

    @Override
    public void editAirlineTrip(AirlineTrip airlineTrip) {

    }

    @Override
    public boolean cancelAirlineTrip(AirlineTrip airlineTrip) {
        //TODO: UPDATE ALL OBSERVERS
        airlineTrip.updateAll(airlineTrip, "THE AIRLINE TRIP HAS BEEN CANCELED");
        return airlineTrip.getDataMapper().removeAirlineTrip(airlineTrip.getAirlineTripID());
    }

    @Override
    public void requestToCancelAirlineTrip(AirlineTrip airlineTrip) {
        // TODO Auto-generated method stub
        
    }
    
}
