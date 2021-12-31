package models.ticket;

import java.util.HashMap;

import models.airline.Seat;
import models.airline.airlineTrip.AirlineTrip;
import models.ticket.ticketState.*;
import models.user.Passenger;

public class Ticket {
    private int ticketId;
    private double price;
    private int requestExtraWeight;
    private boolean requestWheelChair;
    private String type;
    private TicketState ticketstate;
    private HashMap<AirlineTrip, Seat> airlineTripSeats;
    private Payment payment;
    private Passenger passenger;
    private Enquiry enquiry;
}
