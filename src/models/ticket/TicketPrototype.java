package models.ticket;

import models.airline.airlineTrip.AirlineTrip;

public interface TicketPrototype {
    public TicketPrototype clone(AirlineTrip airline);
}
