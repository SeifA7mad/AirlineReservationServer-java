package models.airline.airlineTrip.airlineTripState;

import models.airline.airlineTrip.AirlineTrip;

public abstract class AirlineTripState {
    public abstract void editAirlineTrip(AirlineTrip airlineTrip);
    
    public abstract boolean cancelAirlineTrip(AirlineTrip airlineTrip);

    public abstract void requestToCancelAirlineTrip(AirlineTrip airlineTrip);
}
