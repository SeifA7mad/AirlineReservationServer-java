package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bson.types.ObjectId;


public interface AirportInterface extends Remote {

    public void addAirport(String name, String country, ArrayList<Integer> terminalNumber,
            ArrayList<Integer> hallNumber) throws RemoteException;

    public ArrayList<AirportInterface> getAirports() throws RemoteException;

    public ObjectId getAirportId() throws RemoteException;
    
    public String getName() throws RemoteException;

    public String getCountry() throws RemoteException;

    public ArrayList<Integer> getTerminalNumber() throws RemoteException;

    public ArrayList<Integer> getHallNumber() throws RemoteException;
}
