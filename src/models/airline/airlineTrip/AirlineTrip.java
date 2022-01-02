package models.airline.airlineTrip;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import database.data.AirlineTripDataMapper;
import models.airline.Airplane;
import models.airline.Airport;
import models.airline.Crew;
import models.airline.Seat;
import models.airline.airlineTrip.airlineTripState.*;
import models.ticket.Ticket;
import models.user.Admin;
import models.user.airlineTripObserver.AirlineTripObserver;

public class AirlineTrip implements AirlineTripSubject {

    private ObjectId airlineTripID;
    private int maxNumberOfTickets;
    private Airplane airplane;

    private Airport origin;
    private Airport destination;
    private ArrayList<AirlineTripDetatils> airlineTripDetails;
    private double airlineCost;
    private ArrayList<Ticket> tickets;
    private Crew crew;

    private AirlineTripDataMapper mapper = new AirlineTripDataMapper();

    private AirlineTripState airlineTripState;
    private ArrayList<AirlineTripObserver> passengerObservers = new ArrayList<AirlineTripObserver>();

    public AirlineTrip() {
        this.airlineTripDetails = new ArrayList<AirlineTripDetatils>();
    }

    public AirlineTrip(ObjectId airlineTripID, int maxNumberOfTickets, Airplane airplane, Airport origin,
            Airport destination,
            ArrayList<AirlineTripDetatils> airlineTripDetails, double airlineCost, ArrayList<Ticket> tickets,
            Crew crew, AirlineTripState airlineTripState) {
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

    public void addAirlineTrip(Airplane airplane, Airport origin, Airport destination,
            AirlineTripDetatils airlineTripDetails, double airlineCost, Crew crew) {
        this.airplane = airplane;
        this.maxNumberOfTickets = airplane.getSeats().size();
        this.origin = origin;
        this.destination = destination;
        this.airlineTripDetails.add(airlineTripDetails);
        this.airlineCost = airlineCost;
        this.crew = crew;
        this.tickets = new ArrayList<Ticket>();
        this.airlineTripState = new UnpublishedState();

        this.crew.updateCrew(false);
        this.airplane.updateAirplane(true);

        mapper.insert(this);
    }

    public ArrayList<AirlineTrip> getAirlineTripsBy(String from, String to) throws RemoteException {
        ArrayList<AirlineTrip> airlineTrips = mapper.fetchAirlineTripsBy(from, to);
        return airlineTrips;
    }

    public AirlineTrip getAirlineTripBy(ObjectId airlineTripId) throws RemoteException {
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

        for (int i = 0; i < this.airplane.getSeats().size(); i++) {
            if (this.airplane.getSeats().get(i).getSeatType().equals(seatType)
                    && this.airplane.getSeats().get(i).isAvailablility()) {
                this.airplane.getSeats().get(i).setAvailablility(false);
                seat = this.airplane.getSeats().get(i);
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

    public void requestToCancelAirlineTrip(AirlineTrip airlineTrip) {
        // TODO Auto-generated method stub

    }

    public ObjectId getAirlineTripID() {
        return this.airlineTripID;
    }

    public int getMaxNumberOfTickets() {
        return this.maxNumberOfTickets;
    }

    public Airplane getAirplane() {
        return this.airplane;
    }

    public Crew getCrew() {
        return this.crew;
    }

    public Airport getOrigin() {
        return this.origin;
    }

    public Airport getDestination() {
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
