package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import models.airline.Airplane;

public interface AirplaneInterface extends Remote {
    public void addAirplane(String name, String type, String level, int maxSeatsCapacity, int maxBusnissSeats,
            int maxWeightPerSeat, double maxTravelDistance) throws RemoteException;
    public ArrayList<Airplane> getAirplanes() throws RemoteException;
    public boolean removeAirplane() throws RemoteException;
}
