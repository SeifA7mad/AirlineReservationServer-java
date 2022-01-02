package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import models.airline.Airport;

public interface AirportInterface extends Remote {

    public void addAirport(String name, String country, ArrayList<Integer> terminalNumber,
            ArrayList<Integer> hallNumber) throws RemoteException;

    public ArrayList<Airport> getAirports() throws RemoteException;
}
