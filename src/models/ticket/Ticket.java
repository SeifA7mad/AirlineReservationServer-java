package models.ticket;

import java.util.HashMap;

import org.bson.types.ObjectId;

import models.airline.Seat;
import models.airline.airlineTrip.AirlineTrip;
import models.ticket.ticketState.*;
import models.user.Passenger;

public class Ticket implements TicketPrototype {
    private ObjectId ticketId;
    private double price;
    private int requestExtraWeight;
    private boolean requestWheelChair;
    private String type;
    private TicketState ticketstate;
    private HashMap<AirlineTrip, Seat> airlineTripSeats;
    private Payment payment;
    private Passenger passenger;
    private Enquiry enquiry;

    public Ticket() {

    }

    public Ticket(ObjectId ticketId, double price, int requestExtraWeight, boolean requestWheelChair, String type,
            TicketState ticketstate,
            HashMap<AirlineTrip, Seat> airlineTripSeats, Payment payment, Passenger passenger, Enquiry enquiry) {
        this.ticketId = ticketId;
        this.price = price;
        this.requestExtraWeight = requestExtraWeight;
        this.requestWheelChair = requestWheelChair;
        this.type = type;
        this.ticketstate = ticketstate;
        this.airlineTripSeats = airlineTripSeats;
        this.payment = payment;
        this.passenger = passenger;
        this.enquiry = enquiry;
    }

    public Ticket(double price, int requestExtraWeight, boolean requestWheelChair, String type,
            TicketState ticketstate, HashMap<AirlineTrip, Seat> airlineTripSeats, Payment payment,
            Passenger passenger) {
        this.price = price;
        this.requestExtraWeight = requestExtraWeight;
        this.requestWheelChair = requestWheelChair;
        this.type = type;
        this.ticketstate = ticketstate;
        this.airlineTripSeats = airlineTripSeats;
        this.payment = payment;
        this.passenger = passenger;
    }

    public void bookTicket() {
        // TODO: BOOK TICKET
    }

    @Override
    public TicketPrototype clone(AirlineTrip airline) {
        // TODO: BOOK ANOTHER SEAT NEW HASHMAP
        return new Ticket(price, requestExtraWeight, requestWheelChair, type, ticketstate, airlineTripSeats, payment,
                passenger);
    }

    public ObjectId getTicketId() {
        return ticketId;
    }

    public double getPrice() {
        return price;
    }

    public int getRequestExtraWeight() {
        return requestExtraWeight;
    }

    public boolean isRequestWheelChair() {
        return requestWheelChair;
    }

    public String getType() {
        return type;
    }

    public TicketState getTicketstate() {
        return ticketstate;
    }

    public HashMap<AirlineTrip, Seat> getAirlineTripSeats() {
        return airlineTripSeats;
    }

    public Payment getPayment() {
        return payment;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Enquiry getEnquiry() {
        return enquiry;
    }
}
