package models.user;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.time.LocalDate;

import org.bson.types.ObjectId;

import database.data.UserDataMapper;
import rmi.AdminInterface;

public class Admin extends User implements AdminInterface, Serializable {


    public Admin(String passportID, String Fname, String Lname, String DOB, String phoneNo, char gender,
            String username, String email, String password) throws RemoteException {
        super(passportID, Fname, Lname, DOB, phoneNo, gender, username, email, password, "admin");
    }

    public Admin(ObjectId userId, String passportID, String Fname, String Lname, String DOB, String phoneNo, char gender,
            String username, String email, String password) throws RemoteException {
        super(userId, passportID, Fname, Lname, DOB, phoneNo, gender, username, email, password, "admin");
    }

    public Admin() throws RemoteException {
    }

    public void insertAdmin() throws RemoteException {
        this.mapper.insert(this);
    }

}
