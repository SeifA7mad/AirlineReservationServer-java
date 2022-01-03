// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */
// package models.airline.airlineTrip.facadeDTO;

// import database.data.AirlineTripDataMapper;
// import java.rmi.RemoteException;
// import java.rmi.server.UnicastRemoteObject;
// import java.util.ArrayList;
// import java.util.HashMap;
// import models.airline.Airplane;
// import models.airline.Airport;
// import models.airline.Crew;
// import models.airline.Seat;
// import models.airline.airlineTrip.AirlineTrip;
// import models.airline.airlineTrip.airlineTripState.AirlineTripState;
// import models.ticket.Enquiry;
// import models.ticket.Payment;
// import models.ticket.Ticket;
// import models.ticket.ticketState.TicketState;
// import models.user.Admin;
// import models.user.Passenger;
// import models.user.airlineTripObserver.AirlineTripObserver;

// public class TicketFacade extends UnicastRemoteObject implements FacadeInterface {
// Ticket ticket;
//    public TicketFacade () throws RemoteException {
//         ticket= new Ticket("", "", "","", "", "","", "", "","");
//     }

 
//     public void makeTicket(int ticketId,double price,int requestExtraWeight,
//     boolean requestWheelChair,Enums.TicketType type,TicketState ticketstate,
//     HashMap<AirlineTrip, Seat> airlineTripSeats,Payment payment,
//     Passenger passenger,Enquiry enquiry)throws RemoteException {
//         ticket.setTicketId(ticketId);
//         ticket.setPrice(price);
//         ticket.setRequestWheelChair(requestWheelChair);
//         ticket.setRequestExtraWeight(requestExtraWeight);
//         ticket.setType(type);
//         ticket.setTicketstate(ticketstate);
//         ticket.setPayment(payment);
//         ticket.setAirlineTripSeats(airlineTripSeats);
//         ticket.setPassenger(passenger);
//         ticket.setEnquiry(enquiry);
        
//     }
//     public TicketDTO getTicket() throws RemoteException {
        
//         TicketDTO dto=new TicketDTO(ticket.getTicketId(),
//         ticket.getPrice(),
//         ticket.isRequestWheelChair(),
//         ticket.getRequestExtraWeight(),
//         ticket.getType(),
//         ticket.getTicketstate(),
//         ticket.getPayment(),
//         ticket.getAirlineTripSeats(),
//         ticket.getPassenger(),
//         ticket.getEnquiry());
//         return dto;
//     }

//     @Override
//     public void SetAirlineTrip(String airlineTripID, int maxNumberOfTickets, Airplane airplane, Airport origin, Airport destination, ArrayList<AirlineTripDetails> airlineTripDetails, double airlineCost, ArrayList<Ticket> tickets, Crew crew, Admin admin, AirlineTripDataMapper mapper, AirlineTripState airlineTripState, AirlineTripObserver crewObserver, ArrayList<AirlineTripObserver> passengerObservers) throws RemoteException {
//         throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//     }

//     @Override
//     public AirlineTripDTO getAirlineTrip() throws RemoteException {
//         throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//     }
// }