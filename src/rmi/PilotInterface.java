package rmi;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PilotInterface extends UserInterface {
    public ArrayList<PilotInterface> getAllPilots() throws RemoteException;

    public String getExpirence() throws RemoteException;
}
