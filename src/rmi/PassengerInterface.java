package rmi;

import java.rmi.RemoteException;
import java.util.ArrayList;


public interface PassengerInterface extends UserInterface {
    public ArrayList<PassengerInterface> getCompanions() throws RemoteException;
    
}
