// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */
// package models.airline.airlineTrip.facadeDTO;
// import database.data.AirlineTripDataMapper;
// import models.airline.*;
// import models.airline.airlineTrip.AirlineTrip;
// import models.airline.airlineTrip.AirlineTripDetatils;
// import models.airline.airlineTrip.airlineTripState.*;
// import models.ticket.Enquiry;
// import models.ticket.Payment;
// import models.ticket.Ticket;
// import models.ticket.ticketState.TicketState;
// import models.user.Admin;
// import models.user.Passenger;
// import models.user.airlineTripObserver.AirlineTripObserver;

// import java.rmi.RemoteException;
// import java.rmi.server.UnicastRemoteObject;
// import java.util.ArrayList;
// import java.util.HashMap;

// /**
//  *
//  * @author shiko
//  */
// public class AirlineTripFacade extends UnicastRemoteObject implements FacadeInterface {
    
//     AirlineTrip airline;
//      public AirlineTripFacade() throws RemoteException {
//         airline = new AirlineTrip();
//     }
     
     
//      public void SetAirlineTrip( String airlineTripID, int maxNumberOfTickets,
//      Airplane airplane, Airport origin, Airport destination, ArrayList<AirlineTripDetatils> airlineTripDetails,
//      double airlineCost, ArrayList<Ticket> tickets, Crew crew,Admin admin,AirlineTripDataMapper mapper ,
//      AirlineTripObserver crewObserver,  ArrayList<AirlineTripObserver> passengerObservers)throws RemoteException {
//         airline.setAirlineTripID(airlineTripID);
//         airline.setMaxNumberOfTickets(maxNumberOfTickets);
//         airline.setAirplane(airplane);
//         airline.setOrigin(origin);
//         airline.setDestination(destination);
//         airline.setAirlineTripDetails(airlineTripDetails);
//         airline.setAirlineCost(airlineCost);
//         airline.setTickets(tickets);
//         airline.setCrew(crew);
//         airline.setAdmin(admin);
//         airline.setMapper(mapper);
//         airline.setCrewObserver(crewObserver);
//         airline.setPassengerObservers(passengerObservers);
//     }
     
    
//      public AirlineTripDTO getAirlineTrip() throws RemoteException {
        
//         AirlineTripDTO dto= new AirlineTripDTO (
//         airline.getAirlineTripID(),
//         airline.getMaxNumberOfTickets(),
//         airline.getAirplane(),
//         airline.getOrigin(),
//         airline.getDestination(),
//         airline.getAirlineTripDetails(),
//         airline.getAirlineCost(),
//         airline.getTickets(),
//         airline.getCrew(),
//         airline.getAdmin(),
//         airline.getMapper(),
//         airline.getAirlineTripState(),
//         airline.getCrewObserver(),
//         airline.getPassengerObservers()
//         );
//         return dto;
//     }

//     public void makeTicket(int ticketId, double price, int requestExtraWeight, boolean requestWheelChair, String type, TicketState ticketstate, HashMap<AirlineTrip, Seat> airlineTripSeats, Payment payment, Passenger passenger, Enquiry enquiry) throws RemoteException {
//         throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//     }

//     @Override
//     public TicketDTO getTicket() throws RemoteException {
//         throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//     }


//     @Override
//     public void makeTicket(int ticketId, double price, int requestExtraWeight, boolean requestWheelChair,
//             String type, TicketState ticketstate, HashMap<AirlineTrip, Seat> airlineTripSeats, Payment payment,
//             Passenger passenger, Enquiry enquiry) throws RemoteException {
//         // TODO Auto-generated method stub
        
//     }


    
// }
