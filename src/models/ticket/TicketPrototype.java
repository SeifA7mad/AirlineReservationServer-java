package models.ticket;

import java.util.ArrayList;

import models.airline.airlineTrip.AirlineTrip;

public interface TicketPrototype {
    public TicketPrototype clone(ArrayList<AirlineTrip> airlineTrips);
}
