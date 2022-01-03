package models.ticket.ticketState;

import java.rmi.RemoteException;

import database.data.PassengerDataMapper;
import models.ticket.Ticket;
import models.user.Passenger;
import rmi.PassengerInterface;

public class BookingState extends TicketState {

    private PassengerDataMapper passengerDataMapper = new PassengerDataMapper(); 

    @Override
    public boolean cancelTicket(int ticketId, PassengerInterface passenger) {
        try {
            passengerDataMapper.removeTicket(ticketId, passenger);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
