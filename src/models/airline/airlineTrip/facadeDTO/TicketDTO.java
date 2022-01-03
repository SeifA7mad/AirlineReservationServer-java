/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.airline.airlineTrip.facadeDTO;

import java.util.HashMap;

import models.airline.Seat;
import models.airline.airlineTrip.AirlineTrip;
import models.ticket.Enquiry;
import models.ticket.Payment;
import models.ticket.ticketState.TicketState;
import models.user.Passenger;

/**
 *
 * @author shiko
 */
public class TicketDTO {
    private int ticketId;
    private Passenger passenger;
    private Payment payment;
    private double price;
    private String type;
    private int requestExtraWeight;
    private boolean requestWheelChair;
    private HashMap<AirlineTrip, Seat> airlineTripSeats;
    private TicketState ticketstate;
    private Enquiry enquiry;

    TicketDTO(int ticketId, double price, boolean requestWheelChair, int requestExtraWeight, String type, TicketState ticketstate, Payment payment, HashMap<AirlineTrip, Seat> airlineTripSeats, Passenger passenger, Enquiry enquiry) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRequestExtraWeight(int requestExtraWeight) {
        this.requestExtraWeight = requestExtraWeight;
    }

    public void setRequestWheelChair(boolean requestWheelChair) {
        this.requestWheelChair = requestWheelChair;
    }

    public void setAirlineTripSeats(HashMap<AirlineTrip, Seat> airlineTripSeats) {
        this.airlineTripSeats = airlineTripSeats;
    }

    public void setTicketstate(TicketState ticketstate) {
        this.ticketstate = ticketstate;
    }

    public void setEnquiry(Enquiry enquiry) {
        this.enquiry = enquiry;
    }

    public int getTicketId() {
        return ticketId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Payment getPayment() {
        return payment;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getRequestExtraWeight() {
        return requestExtraWeight;
    }

    public boolean isRequestWheelChair() {
        return requestWheelChair;
    }

    public HashMap<AirlineTrip, Seat> getAirlineTripSeats() {
        return airlineTripSeats;
    }

    public TicketState getTicketstate() {
        return ticketstate;
    }

    public Enquiry getEnquiry() {
        return enquiry;
    }
    
     
     
    
    
}
