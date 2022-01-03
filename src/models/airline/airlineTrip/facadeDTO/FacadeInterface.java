/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.airline.airlineTrip.facadeDTO;

import database.data.AirlineTripDataMapper;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import models.airline.Airplane;
import models.airline.Airport;
import models.airline.Crew;
import models.airline.Seat;
import models.airline.airlineTrip.AirlineTrip;
import models.airline.airlineTrip.AirlineTripDetatils;
import models.airline.airlineTrip.airlineTripState.AirlineTripState;
import models.ticket.Enquiry;
import models.ticket.Payment;
import models.ticket.Ticket;
import models.ticket.ticketState.TicketState;
import models.user.Admin;
import models.user.Passenger;
import models.user.airlineTripObserver.AirlineTripObserver;

public interface FacadeInterface extends Remote {
    
    
   public void makeTicket(int ticketId,double price,int requestExtraWeight,
    boolean requestWheelChair,String type,TicketState ticketstate,
    HashMap<AirlineTrip, Seat> airlineTripSeats,Payment payment,
    Passenger passenger,Enquiry enquiry)throws RemoteException;
    public TicketDTO getTicket() throws RemoteException;
    public void SetAirlineTrip( String airlineTripID, int maxNumberOfTickets,
     Airplane airplane, Airport origin, Airport destination, ArrayList<AirlineTripDetatils> airlineTripDetails,
     double airlineCost, ArrayList<Ticket> tickets, Crew crew,Admin admin,AirlineTripDataMapper mapper ,AirlineTripState airlineTripState,
     AirlineTripObserver crewObserver,  ArrayList<AirlineTripObserver> passengerObservers)throws RemoteException;
    public AirlineTripDTO getAirlineTrip() throws RemoteException;
   
    
}

