package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bson.types.ObjectId;

import models.airline.Seat;
import models.ticket.Enquiry;
import models.ticket.Payment;
import models.ticket.ticketState.TicketState;

public interface TicketInterface extends Remote {
    public void bookTicket(ArrayList<AirlineTripInterface> airlineTrips, PassengerInterface passenger,
            int requestExtraWeight,
            boolean requestWheelChair, String ticketType, String creditcardNumber, String nameOnCard,
            String expiredDate) throws RemoteException;

    public boolean cancelTicket(int ticketId, PassengerInterface passenger) throws RemoteException;

    public ObjectId getTicketId() throws RemoteException;

    public double getPrice() throws RemoteException;

    public int getRequestExtraWeight() throws RemoteException;

    public boolean isRequestWheelChair() throws RemoteException;

    public String getType() throws RemoteException;

    public TicketState getTicketstate() throws RemoteException;

    public HashMap<ObjectId, Seat> getAirlineTripSeats() throws RemoteException;

    public Payment getPayment() throws RemoteException;

    public PassengerInterface getPassenger() throws RemoteException;

    public Enquiry getEnquiry() throws RemoteException;
}
