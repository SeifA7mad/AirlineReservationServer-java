package models.ticket.ticketState;

import models.ticket.Ticket;

public abstract class TicketState {
    public abstract boolean cancelTicket(Ticket ticket);
    
    public abstract boolean requestExtraWeight(int weight);

    public abstract boolean requestWheelchair(boolean request);
}
