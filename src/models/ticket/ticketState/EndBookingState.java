package models.ticket.ticketState;

import models.ticket.Ticket;

public class EndBookingState extends TicketState {

    @Override
    public boolean cancelTicket(Ticket ticket) {
        // TODO Auto-generated method stub
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
