package models.ticket;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bson.types.ObjectId;

import database.data.PassengerDataMapper;
import database.data.TicketDataMapper;
import models.airline.Seat;
import models.airline.airlineTrip.AirlineTrip;
import models.ticket.ticketState.*;
import models.user.Passenger;
import rmi.AirlineTripInterface;
import rmi.PassengerInterface;
import rmi.TicketInterface;

public class Ticket implements TicketPrototype, TicketInterface {
    private ObjectId ticketId;
    private double price;
    private int requestExtraWeight;
    private boolean requestWheelChair;
    private String type;
    private TicketState ticketstate;
    private HashMap<ObjectId, Seat> airlineTripSeats;
    private Payment payment;
    private PassengerInterface passenger;
    private Enquiry enquiry;

    private PassengerDataMapper passengerMapper = new PassengerDataMapper();

    public Ticket() {

    }

    public Ticket(double price, int requestExtraWeight, boolean requestWheelChair, String type,
            TicketState ticketstate,
            HashMap<ObjectId, Seat> airlineTripSeats, Payment payment, Enquiry enquiry) {
        this.price = price;
        this.requestExtraWeight = requestExtraWeight;
        this.requestWheelChair = requestWheelChair;
        this.type = type;
        this.ticketstate = ticketstate;
        this.airlineTripSeats = airlineTripSeats;
        this.payment = payment;
        this.enquiry = enquiry;
    }

    public Ticket(double price, int requestExtraWeight, boolean requestWheelChair, String type,
            TicketState ticketstate, HashMap<ObjectId, Seat> airlineTripSeats, Payment payment) {
        this.price = price;
        this.requestExtraWeight = requestExtraWeight;
        this.requestWheelChair = requestWheelChair;
        this.type = type;
        this.ticketstate = ticketstate;
        this.airlineTripSeats = airlineTripSeats;
        this.payment = payment;
    }

    public void bookTicket(ArrayList<AirlineTripInterface> airlineTrips, PassengerInterface passenger,
            int requestExtraWeight,
            boolean requestWheelChair, String ticketType, String creditcardNumber, String nameOnCard,
            String expiredDate) throws RemoteException {

        boolean booked = false;
        this.requestExtraWeight = requestExtraWeight;
        this.requestWheelChair = requestWheelChair;
        this.type = ticketType;
        this.passenger = passenger;
        this.payment = new Payment(creditcardNumber, nameOnCard, expiredDate);
        this.price = this.requestExtraWeight * 10;
        this.ticketstate = new BookingState();
        this.airlineTripSeats = new HashMap<ObjectId, Seat>();

        if (requestWheelChair) {
            this.price += 50;
        }

        for (int i = 0; i < airlineTrips.size(); i++) {
            Seat bookedSeat = airlineTrips.get(i).getFirstAvailableSeat(ticketType);
            if (bookedSeat == null) {
                System.out.println("no available seats");
                return;
            }
            this.airlineTripSeats.put(airlineTrips.get(i).getAirlineTripID(), bookedSeat);
            this.price += airlineTrips.get(i).getAirlineCost();
            booked = true;
        }
        if (booked) {
            this.passenger.addBookedTicket(this, airlineTrips);
            if (passenger.getCompanions().size() > 0) {
                passenger.getCompanions().forEach((companion) -> {
                    try {
                        ((PassengerInterface) companion).addBookedTicket((Ticket) this.clone(airlineTrips), airlineTrips);
                    } catch (RemoteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    @Override
    public TicketPrototype clone(ArrayList<AirlineTripInterface> airlineTrips) {
        HashMap<ObjectId, Seat> airlineSeats = new HashMap<ObjectId, Seat>();

        airlineTrips.forEach((airlineTrip) -> {
            Seat bookedSeat;
            try {
                bookedSeat = airlineTrip.getFirstAvailableSeat(this.type);
                if (bookedSeat == null) {
                    System.out.println("no available seats");
                    return;
                }

                airlineSeats.put(airlineTrip.getAirlineTripID(), bookedSeat);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        return new Ticket(price, requestExtraWeight, requestWheelChair, type, ticketstate, airlineSeats, payment);
    }

    public boolean cancelTicket(int ticketId, PassengerInterface passenger) {
        return this.ticketstate.cancelTicket(ticketId, passenger);
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

    public PassengerInterface getPassenger() {
        return passenger;
    }

    public Enquiry getEnquiry() {
        return enquiry;
    }
}
