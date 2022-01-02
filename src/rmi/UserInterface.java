package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bson.types.ObjectId;

public interface UserInterface extends Remote {
    public void createAccount(String passportID, String Fname, String Lname, String DOB, String phoneNo, char gender,
            String username, String email, String password, String accType) throws RemoteException;

    public UserInterface login(String email, String password) throws RemoteException;

    public ObjectId getUserId() throws RemoteException;
    
    public String getPassportID() throws RemoteException;

    public String getFname() throws RemoteException;

}
