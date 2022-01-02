package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import models.airline.Host;

public interface HostInterface extends Remote {
    public void addHost(String passportNo, String name, ArrayList<String> languages) throws RemoteException;

    public boolean removeHost() throws RemoteException;

    public ArrayList<HostInterface> getAllHosts() throws RemoteException;

    public ObjectId getHostId() throws RemoteException;

    public String getPassportNumber() throws RemoteException;

    public String getName() throws RemoteException;

    public ArrayList<String> getLanguages() throws RemoteException;
}
