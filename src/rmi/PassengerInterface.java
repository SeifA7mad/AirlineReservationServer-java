package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import models.ticket.Ticket;
import models.user.User;

public interface PassengerInterface extends UserInterface{
    public ArrayList<PassengerInterface> getCompanions() throws RemoteException;

}
