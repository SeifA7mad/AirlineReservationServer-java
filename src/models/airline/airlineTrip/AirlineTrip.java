package models.airline.airlineTrip;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import database.data.AirlineTripDataMapper;
import models.airline.Airplane;
import models.airline.Airport;
import models.airline.Crew;
import models.airline.Seat;
import models.airline.airlineTrip.airlineTripState.*;
import models.ticket.Ticket;

import models.user.airlineTripObserver.AirlineTripObserver;
import rmi.AirlineTripInterface;
import rmi.AirplaneInterface;
import rmi.AirportInterface;
import rmi.CrewInterface;

public class AirlineTrip extends UnicastRemoteObject implements AirlineTripSubject, AirlineTripInterface, Serializable {

    private ObjectId airlineTripID;
    private int maxNumberOfTickets;
    private AirplaneInterface airplane;

    private AirportInterface origin;
    private AirportInterface destination;
    private ArrayList<AirlineTripDetatils> airlineTripDetails;
    private double airlineCost;
    private ArrayList<Ticket> tickets;
    private CrewInterface crew;

    private AirlineTripDataMapper mapper = new AirlineTripDataMapper();

    private AirlineTripState airlineTripState;
    private ArrayList<AirlineTripObserver> passengerObservers = new ArrayList<AirlineTripObserver>();

    public AirlineTrip() throws RemoteException {
        this.airlineTripDetails = new ArrayList<AirlineTripDetatils>();
    }

    public AirlineTrip(ObjectId airlineTripID, int maxNumberOfTickets, Airplane airplane, Airport origin,
            Airport destination,
            ArrayList<AirlineTripDetatils> airlineTripDetails, double airlineCost, ArrayList<Ticket> tickets,
            Crew crew, AirlineTripState airlineTripState) throws RemoteException {
        this.airlineTripID = airlineTripID;
        this.maxNumberOfTickets = maxNumberOfTickets;
        this.airplane = airplane;
        this.origin = origin;
        this.destination = destination;
        this.airlineTripDetails = airlineTripDetails;
        this.airlineCost = airlineCost;
        this.tickets = tickets;
        this.crew = crew;

        this.airlineTripState = airlineTripState;
    }

    public void addAirlineTrip(AirplaneInterface airplane, AirportInterface origin,
            AirportInterface destination,
            String depatureDateTime, String arrivalDateTime, int destinationTerminalNo, int orginHallNo,
            double airlineCost, CrewInterface crew) {
        this.airplane = airplane;
        try {
            this.maxNumberOfTickets = this.airplane.getSeats().size();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.origin = origin;
        this.destination = destination;
        this.airlineTripDetails = new ArrayList<AirlineTripDetatils>();
        this.airlineTripDetails.add(new AirlineTripDetatils(depatureDateTime, arrivalDateTime, destinationTerminalNo, orginHallNo));
        this.airlineCost = airlineCost;
        this.crew = crew;
        this.tickets = new ArrayList<Ticket>();
        this.airlineTripState = new UnpublishedState();

        try {
            this.crew.updateCrew(false);
            this.airplane.updateAirplane(true);
        } catch (RemoteException e) {

            e.printStackTrace();
        }


        try {
            mapper.insert(this);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<AirlineTripInterface> getAirlineTripsBy(String from, String to) {
        ArrayList<AirlineTripInterface> airlineTrips = null;
        try {
            airlineTrips = mapper.fetchAirlineTripsBy(from, to);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return airlineTrips;
    }

    public AirlineTripInterface getAirlineTripBy(ObjectId airlineTripId) throws RemoteException {
        return mapper.fetchAirlineTripBy(airlineTripId);
    }

    @Override
    public void addObserver(AirlineTripObserver passenger) {
        this.passengerObservers.add(passenger);
        this.mapper.updatePassengerObservers(this, passenger);
    }

    @Override
    public void removeObserver(AirlineTripObserver passenger) {
        // TODO Auto-generated method stub
    }

    @Override
    public void updateAll(AirlineTrip airlineTrip, String news) {
        // TODO Auto-generated method stub
    }

    public Seat getFirstAvailableSeat(String seatType) {
        Seat seat = null;

        for (int i = 0; i < ((Airplane)this.airplane).getSeats().size(); i++) {
            if (((Airplane) this.airplane).getSeats().get(i).getSeatType().equals(seatType)
                    && ((Airplane) this.airplane).getSeats().get(i).isAvailablility()) {
                ((Airplane) this.airplane).getSeats().get(i).setAvailablility(false);
                seat = ((Airplane) this.airplane).getSeats().get(i);
                seat.setSeatID(i + 1);
                break;
            }
        }

        if (seat == null) {
            return null;
        }
        return seat;
    }

    public void editAirlineTrip(AirlineTrip airlineTrip) {
        // TODO Auto-generated method stub

    }

    public boolean cancelAirlineTrip() {
        // TODO Auto-generated method stub
        this.airlineTripState.cancelAirlineTrip(this);
        return false;
    }

    public void requestToCancelAirlineTrip(AirlineTripInterface airlineTrip) {
        // TODO Auto-generated method stub

    }

    public ObjectId getAirlineTripID() {
        return this.airlineTripID;
    }

    public int getMaxNumberOfTickets() {
        return this.maxNumberOfTickets;
    }

    public AirplaneInterface getAirplane() {
        return this.airplane;
    }

    public CrewInterface getCrew() {
        return this.crew;
    }

    public AirportInterface getOrigin() {
        return this.origin;
    }

    public AirportInterface getDestination() {
        return this.destination;
    }

    public ArrayList<AirlineTripDetatils> getAirlineTripDetails() {
        return this.airlineTripDetails;
    }

    public double getAirlineCost() {
        return this.airlineCost;
    }

    public AirlineTripState getAirlineTripState() {
        return this.airlineTripState;
    }

    public AirlineTripDataMapper getDataMapper() {
        return this.mapper;
    }

}
