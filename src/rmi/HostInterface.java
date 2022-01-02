package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import models.airline.Host;

public interface HostInterface extends Remote {
    public void addHost(String passportNo, String name, ArrayList<String> languages) throws RemoteException;
    public boolean removeHost() throws RemoteException;
    public ArrayList<Host> getAllHosts() throws RemoteException;
}
