/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.airline.airlineTrip.facadeDTO;

import database.data.AirlineTripDataMapper;
import java.io.Serializable;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import models.airline.Airplane;
import models.airline.Airport;
import models.airline.Crew;
import models.airline.airlineTrip.AirlineTripDetatils;
import models.airline.airlineTrip.airlineTripState.AirlineTripState;
import models.ticket.Ticket;
import models.user.Admin;
import models.user.airlineTripObserver.AirlineTripObserver;
import rmi.AirplaneInterface;
import rmi.AirportInterface;
import rmi.CrewInterface;


public class AirlineTripDTO implements Serializable {

    private int airlineTripID;
    private int maxNumberOfTickets;
    private Airplane airplane ;
    private Crew crew;
    private Admin admin;
    private Airport Origin;
    private Airport destination;
    private ArrayList<AirlineTripDetatils> airlineTripDetails;
    private double airlineCost;
    private AirlineTripState airlineTripState;
    private ArrayList<Ticket> tickets;
    private AirlineTripObserver crewObserver;
    private ArrayList<AirlineTripObserver> passengerObservers;

    AirlineTripDTO(ObjectId objectId, int maxNumberOfTickets, AirplaneInterface airplaneInterface, AirportInterface airportInterface, AirportInterface airportInterface2, ArrayList<AirlineTripDetatils> airlineTripDetails, double airlineCost, ArrayList<Ticket> tickets, CrewInterface crewInterface, Admin admin, AirlineTripDataMapper mapper, AirlineTripState airlineTripState, AirlineTripObserver crewObserver, ArrayList<AirlineTripObserver> passengerObservers) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void setAirlineTripID(int airlineTripID) {
        this.airlineTripID = airlineTripID;
    }

    public void setMaxNumberOfTickets(int maxNumberOfTickets) {
        this.maxNumberOfTickets = maxNumberOfTickets;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setOrigin(Airport Origin) {
        this.Origin = Origin;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public void setAirlineTripDetails(ArrayList<AirlineTripDetatils> airlineTripDetails) {
        this.airlineTripDetails = airlineTripDetails;
    }

    public void setAirlineCost(double airlineCost) {
        this.airlineCost = airlineCost;
    }

    public void setAirlineTripState(AirlineTripState airlineTripState) {
        this.airlineTripState = airlineTripState;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setCrewObserver(AirlineTripObserver crewObserver) {
        this.crewObserver = crewObserver;
    }

    public void setPassengerObservers(ArrayList<AirlineTripObserver> passengerObservers) {
        this.passengerObservers = passengerObservers;
    }

    public int getAirlineTripID() {
        return airlineTripID;
    }

    public int getMaxNumberOfTickets() {
        return maxNumberOfTickets;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public Crew getCrew() {
        return crew;
    }

    public Admin getAdmin() {
        return admin;
    }

    public Airport getOrigin() {
        return Origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public ArrayList<AirlineTripDetatils> getAirlineTripDetails() {
        return airlineTripDetails;
    }

    public double getAirlineCost() {
        return airlineCost;
    }

    public AirlineTripState getAirlineTripState() {
        return airlineTripState;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public AirlineTripObserver getCrewObserver() {
        return crewObserver;
    }

    public ArrayList<AirlineTripObserver> getPassengerObservers() {
        return passengerObservers;
    }
    
    
    
}
