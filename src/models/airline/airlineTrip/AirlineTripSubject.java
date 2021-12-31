package models.airline.airlineTrip;

import java.util.ArrayList;

import models.user.airlineTripObserver.AirlineTripObserver;

public interface AirlineTripSubject {
    public void addObserver(ArrayList<AirlineTripObserver> o);

    public void removeObserver(AirlineTripObserver o);

    public void updateAll(AirlineTrip airlineTrip, String news);
}
