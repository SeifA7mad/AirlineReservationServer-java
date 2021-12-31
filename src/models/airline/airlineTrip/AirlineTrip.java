package models.airline.airlineTrip;

import java.util.ArrayList;

import database.data.AirlineTripDataMapper;
import models.airline.Airplane;
import models.airline.Airport;
import models.airline.Crew;
import models.airline.airlineTrip.airlineTripState.*;
import models.ticket.Ticket;
import models.user.Admin;
import models.user.airlineTripObserver.AirlineTripObserver;

public class AirlineTrip implements AirlineTripSubject {
    private String airlineTripID;
    private int maxNumberOfTickets;
    private Airplane airplane;

    private Airport origin;
    private Airport destination;
    private ArrayList<AirlineTripDetatils> airlineTripDetails;
    private double airlineCost;

    private ArrayList<Ticket> tickets;

    private Crew crew;
    private Admin admin;

    private AirlineTripDataMapper mapper = new AirlineTripDataMapper();

    private AirlineTripState airlineTripState;
    private AirlineTripObserver crewObserver;
    private ArrayList<AirlineTripObserver> passengerObservers;

    public AirlineTrip() {

    }

    public void addAirlineTrip(int maxNumberOfTickets, Airplane airplane, Airport origin, Airport destination,
            AirlineTripDetatils airlineTripDetails, double airlineCost, Crew crew) {
                this.maxNumberOfTickets = maxNumberOfTickets;
                this.airplane = airplane;
                this.origin = origin;
                this.destination = destination;
                this.airlineTripDetails.add(airlineTripDetails);
                this.airlineCost = airlineCost;
                this.crew = crew;

                this.airlineTripState = new UnpublishedState();
                
                mapper.insert(this);
    }

    @Override
    public void addObserver(ArrayList<AirlineTripObserver> o) {
        // TODO Auto-generated method stub
    }

    @Override
    public void removeObserver(AirlineTripObserver o) {
        // TODO Auto-generated method stub
    }

    @Override
    public void updateAll(AirlineTrip airlineTrip, String news) {
        // TODO Auto-generated method stub
    }

    public String getAirlineTripID() {
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

    public Admin getAdmin() {
        return this.admin;
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

}
