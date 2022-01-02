package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;

import org.bson.types.ObjectId;

import models.user.Account;

public interface UserInterface extends Remote {
    public void createAccount(String passportID, String Fname, String Lname, String DOB, String phoneNo, char gender,
            String username, String email, String password, String accType) throws RemoteException;

    public UserInterface login(String email, String password) throws RemoteException;

    public ObjectId getUserId() throws RemoteException;
    
    public String getPassportID() throws RemoteException;

    public String getFname() throws RemoteException;

    public String getLname() throws RemoteException;;

    public LocalDate getDOB() throws RemoteException;
    public String getPhoneNo() throws RemoteException;

    public char getGender() throws RemoteException;

    public String getUsername() throws RemoteException;
    public String getEmail() throws RemoteException;

    public String getPassword() throws RemoteException;

    public String getAccountType() throws RemoteException;
}
