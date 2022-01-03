package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import models.airline.Seat;
import models.airline.airlineTrip.AirlineTripDetatils;
import models.airline.airlineTrip.airlineTripState.AirlineTripState;


public interface AirlineTripInterface extends Remote {
    public void addAirlineTrip(AirplaneInterface airplane, AirportInterface origin,
            AirportInterface destination,
            String depatureDateTime, String arrivalDateTime, int destinationTerminalNo, int orginHallNo,
            double airlineCost, CrewInterface crew) throws RemoteException;
    public ArrayList<AirlineTripInterface> getAirlineTripsBy(String from, String to) throws RemoteException;
    public AirlineTripInterface getAirlineTripBy(ObjectId airlineTripId) throws RemoteException;
    
    public boolean cancelAirlineTrip() throws RemoteException;
    public void requestToCancelAirlineTrip(AirlineTripInterface airlineTrip) throws RemoteException;

    public Seat getFirstAvailableSeat(String seatType) throws RemoteException;
    public ObjectId getAirlineTripID() throws RemoteException;

    public int getMaxNumberOfTickets() throws RemoteException;

    public AirplaneInterface getAirplane() throws RemoteException;

    public CrewInterface getCrew() throws RemoteException;
    public AirportInterface getOrigin() throws RemoteException;

    public AirportInterface getDestination() throws RemoteException;

    public ArrayList<AirlineTripDetatils> getAirlineTripDetails() throws RemoteException;

    public double getAirlineCost() throws RemoteException;

    public AirlineTripState getAirlineTripState() throws RemoteException;
}
