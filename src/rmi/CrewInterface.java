package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bson.types.ObjectId;

public interface CrewInterface extends Remote {
    public void createCrew(
            PilotInterface pilot, ArrayList<PilotInterface> co_pilots, ArrayList<HostInterface> hosts,
            ArrayList<String> airplaneLevels) throws RemoteException;

    public ArrayList<CrewInterface> getCrews() throws RemoteException;

    public ObjectId getCrewId() throws RemoteException;

    public ArrayList<PilotInterface> getCo_pilots() throws RemoteException;

    public ArrayList<HostInterface> getHosts() throws RemoteException;

    public ArrayList<String> getAirplaneLevels() throws RemoteException;
}
