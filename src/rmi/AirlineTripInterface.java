package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bson.types.ObjectId;


public interface AirlineTripInterface extends Remote {
    public void addAirlineTrip(AirplaneInterface airplane, AirportInterface origin,
            AirportInterface destination,
            String depatureDateTime, String arrivalDateTime, int destinationTerminalNo, int orginHallNo,
            double airlineCost, CrewInterface crew) throws RemoteException;
    public ArrayList<AirlineTripInterface> getAirlineTripsBy(String from, String to) throws RemoteException;
    public AirlineTripInterface getAirlineTripBy(ObjectId airlineTripId) throws RemoteException;
    
    public boolean cancelAirlineTrip() throws RemoteException;
    public void requestToCancelAirlineTrip(AirlineTripInterface airlineTrip) throws RemoteException;
}
