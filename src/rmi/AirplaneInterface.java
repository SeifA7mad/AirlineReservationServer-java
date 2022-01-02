package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import models.airline.Airplane;

public interface AirplaneInterface extends Remote {
    public void addAirplane(String name, String type, String level, int maxSeatsCapacity, int maxBusnissSeats,
            int maxWeightPerSeat, double maxTravelDistance) throws RemoteException;
    public ArrayList<AirplaneInterface> getAirplanes() throws RemoteException;
    public boolean removeAirplane() throws RemoteException;
    public ObjectId getAirplaneID() throws RemoteException;
    public String getName() throws RemoteException;
    public String getType() throws RemoteException;
    public boolean isOnDuty() throws RemoteException;
    public String getLevel() throws RemoteException;
}
