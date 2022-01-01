package models.ticket;

import java.util.ArrayList;
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
    private HashMap<ObjectId, Seat> airlineTripSeats;
    private Payment payment;
    private Passenger passenger;
    private Enquiry enquiry;

    public Ticket() {

    }

    public Ticket(ObjectId ticketId, double price, int requestExtraWeight, boolean requestWheelChair, String type,
            TicketState ticketstate,
            HashMap<ObjectId, Seat> airlineTripSeats, Payment payment, Passenger passenger, Enquiry enquiry) {
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
            TicketState ticketstate, HashMap<ObjectId, Seat> airlineTripSeats, Payment payment,
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

    public void bookTicket(ArrayList<AirlineTrip> airlineTrips, Passenger passenger, int requestExtraWeight,
            boolean requestWheelChair, String ticketType, Payment payment) {
        
        this.requestExtraWeight = requestExtraWeight;
        this.requestWheelChair = requestWheelChair;
        this.type = ticketType;
        this.payment = payment;
        this.passenger = passenger;

        // TODO: BOOK TICKET
        airlineTrips.forEach((airlineTrip) -> {
            Seat bookedSeat = airlineTrip.getFirstAvailableSeat(ticketType);
            if (bookedSeat == null) {
                System.out.println("no available seats");
                return;
            }
            this.airlineTripSeats.put(airlineTrip.getAirlineTripID(), bookedSeat);

            // TODO: COMPANIONS BOOK ANOTHER SEAT NEW HASHMAP
            if (passenger.getCompanions().size() > 0) {
                passenger.getCompanions().forEach((companion) -> {
                    companion.addBookedTicket((Ticket) this.clone(airlineTrips));
                });
            }
        });
    }

    @Override
    public TicketPrototype clone(ArrayList<AirlineTrip> airlineTrips) {
        HashMap<ObjectId, Seat> airlineSeats = new HashMap<ObjectId, Seat>();

        airlineTrips.forEach((airlineTrip) -> {
            Seat bookedSeat = airlineTrip.getFirstAvailableSeat(this.type);
            if (bookedSeat == null) {
                System.out.println("no available seats");
                return;
            }
            airlineSeats.put(airlineTrip.getAirlineTripID(), bookedSeat);
        });

        return new Ticket(price, requestExtraWeight, requestWheelChair, type, ticketstate, airlineSeats, payment,
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

    public HashMap<ObjectId, Seat> getAirlineTripSeats() {
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
