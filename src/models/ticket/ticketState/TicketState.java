package models.ticket.ticketState;

import models.ticket.Ticket;
import models.user.Passenger;
import rmi.PassengerInterface;

public abstract class TicketState {
    public abstract boolean cancelTicket(int ticketId, PassengerInterface passenger);
    
    public abstract boolean requestExtraWeight(int weight);

    public abstract boolean requestWheelchair(boolean request);
}
