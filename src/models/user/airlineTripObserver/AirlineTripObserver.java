package models.user.airlineTripObserver;

import models.airline.airlineTrip.AirlineTrip;

public interface AirlineTripObserver {
    public void update(AirlineTrip airlineTrip, String news);
}
