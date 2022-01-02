package models.user;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import database.data.PassengerDataMapper;
import models.airline.airlineTrip.AirlineTrip;
import models.ticket.Ticket;
import models.user.airlineTripObserver.AirlineTripObserver;
import rmi.PassengerInterface;

public class Passenger extends User implements AirlineTripObserver, PassengerInterface, Serializable {
    private ArrayList<PassengerInterface> companions;
    private ArrayList<Ticket> tickets;

    private PassengerDataMapper passengerMapper = new PassengerDataMapper();

    public Passenger(String passportID, String Fname, String Lname,
            String DOB, String phoneNo, char gender,
            String username, String email, String password) throws RemoteException {
        super(passportID, Fname, Lname, DOB, phoneNo, gender, username, email, password, "passenger");

        this.companions = new ArrayList<PassengerInterface>();
        this.tickets = new ArrayList<Ticket>();
    }

    public Passenger(ObjectId userId, String passportID, String Fname, String Lname,
            String DOB, String phoneNo, char gender,
            String username, String email, String password, ArrayList<PassengerInterface> companions,
            ArrayList<Ticket> tickets) throws RemoteException {
        super(userId, passportID, Fname, Lname, DOB, phoneNo, gender, username, email, password, "passenger");
        this.companions = companions;
        this.tickets = tickets;
    }

    public Passenger() throws RemoteException {
    }

    public void insertPassenger() {
        this.mapper.insert(this);
    }

    public ArrayList<PassengerInterface> getCompanions() {
        return this.companions;
    }

    public void addBookedTicket(Ticket ticket, ArrayList<AirlineTrip> airlineTrips) {
        this.tickets.add(ticket);
        passengerMapper.updateTickets(ticket, this);

        airlineTrips.forEach((airlineTrip) -> {
            airlineTrip.addObserver(this);
        });
    }

    public boolean cancelTicket(int ticketId, Ticket ticket) {
        boolean isRemoved = ticket.cancelTicket(ticketId - 1, this);
        if (isRemoved) {
            this.tickets.remove(ticketId - 1);
        }
        return isRemoved;
    }

    public void addCompanions(ArrayList<String> companionsEmails) {
        companionsEmails.forEach((email) -> {
            Passenger companion = (Passenger) mapper.findPassengerBy(email);
            this.companions.add(companion);
            passengerMapper.updateCompanions(companion, this);
        });
    }

    public boolean removeCompanion(ObjectId companionId) {
        boolean isRemoved = this.passengerMapper.removeCompanion(companionId, this);

        if (isRemoved) {
            for (int i = 0; i < this.companions.size(); i++) {
                if ((((Passenger) this.companions.get(i)).getUserId()).equals(companionId)) {
                    this.companions.remove(i);
                    break;
                }
            }
        }

        return isRemoved;
    }

    public ArrayList<Ticket> getPassengerTickets() {
        ArrayList<Ticket> tickets = passengerMapper.fetchTickets(this);
        return tickets;
    }

    public ArrayList<PassengerInterface> getPassengerCompanions() {
        return this.companions;
    }

    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }

    @Override
    public void update(AirlineTrip airlineTrip, String news) {
        // TODO Auto-generated method stub

    }

}
