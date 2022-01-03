package rmi;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import models.ticket.Ticket;

public interface PassengerInterface extends UserInterface {
    public void addCompanions(ArrayList<String> companionsEmails) throws RemoteException;

    public boolean removeCompanion(ObjectId companionId) throws RemoteException;

    public ArrayList<TicketInterface> getPassengerTickets() throws RemoteException;

    public ArrayList<PassengerInterface> getCompanions() throws RemoteException;

    public void addBookedTicket(
            Ticket ticket, ArrayList<AirlineTripInterface> airlineTrips) throws RemoteException;

}
