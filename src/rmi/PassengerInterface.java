package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import models.user.User;

public interface PassengerInterface extends Remote {
    public void createAccount(String passportID, String Fname, String Lname, String DOB, String phoneNo, char gender,
            String username, String email, String password, String accType) throws RemoteException;

    public User login(String email, String password) throws RemoteException;

    public ArrayList<PassengerInterface> getCompanions() throws RemoteException;
}
