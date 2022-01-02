package models.ticket.ticketState;

import database.data.PassengerDataMapper;
import models.ticket.Ticket;
import models.user.Passenger;

public class BookingState extends TicketState {

    private PassengerDataMapper passengerDataMapper = new PassengerDataMapper(); 

    @Override
    public boolean cancelTicket(int ticketId, Passenger passenger) {
        passengerDataMapper.removeTicket(ticketId, passenger);
        return false;
    }

    @Override
    public boolean requestExtraWeight(int weight) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean requestWheelchair(boolean request) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
