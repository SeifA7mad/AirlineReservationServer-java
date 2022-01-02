package models.airline.airlineTrip;

import java.util.ArrayList;

import models.user.airlineTripObserver.AirlineTripObserver;

public interface AirlineTripSubject {
    public void addObserver(AirlineTripObserver passegenr);

    public void removeObserver(AirlineTripObserver paasnger);

    public void updateAll(AirlineTrip airlineTrip, String news);
}
