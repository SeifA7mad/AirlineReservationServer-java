package models.user;

import java.util.ArrayList;

import org.bson.types.ObjectId;

import database.data.PassengerDataMapper;
import models.airline.airlineTrip.AirlineTrip;
import models.ticket.Ticket;
import models.user.airlineTripObserver.AirlineTripObserver;

public class Passenger extends User implements AirlineTripObserver {
    private ArrayList<Passenger> companions;
    private ArrayList<Ticket> tickets;

    private PassengerDataMapper passengerMapper = new PassengerDataMapper();

    public Passenger(String passportID, String Fname, String Lname,
            String DOB, String phoneNo, char gender,
            String username, String email, String password) {
        super(passportID, Fname, Lname, DOB, phoneNo, gender, username, email, password, "passenger");

        this.companions = new ArrayList<Passenger>();
        this.tickets = new ArrayList<Ticket>();
    }

    public Passenger(ObjectId userId, String passportID, String Fname, String Lname,
            String DOB, String phoneNo, char gender,
            String username, String email, String password, ArrayList<Passenger> companions,
            ArrayList<Ticket> tickets) {
        super(userId, passportID, Fname, Lname, DOB, phoneNo, gender, username, email, password, "passenger");
        this.companions = companions;
        this.tickets = tickets;
    }

    public Passenger() {
    }

    public void insertPassenger() {
        this.mapper.insert(this);
    }

    public ArrayList<Passenger> getCompanions() {
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
                if (this.companions.get(i).getUserId().equals(companionId)) {
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

    public ArrayList<Passenger> getPassengerCompanions() {
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
