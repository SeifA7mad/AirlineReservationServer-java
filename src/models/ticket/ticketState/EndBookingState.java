package models.ticket.ticketState;

import models.ticket.Ticket;
import models.user.Passenger;
import rmi.PassengerInterface;

public class EndBookingState extends TicketState {

    @Override
    public boolean cancelTicket(int ticketId, PassengerInterface passenger) {
        System.out.println("CANNOT CANCEL AN EXPIRED TICKET");
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
