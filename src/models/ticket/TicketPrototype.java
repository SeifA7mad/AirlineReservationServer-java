package models.ticket;

import java.rmi.RemoteException;
import java.util.ArrayList;

import models.airline.airlineTrip.AirlineTrip;
import rmi.AirlineTripInterface;

public interface TicketPrototype {
    public TicketPrototype clone(ArrayList<AirlineTripInterface> airlineTrips) throws RemoteException;
}
