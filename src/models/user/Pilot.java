package models.user;

import java.rmi.RemoteException;
import java.time.LocalDate;

import org.bson.types.ObjectId;

import database.data.UserDataMapper;
import rmi.PilotInterface;

public class Pilot extends User implements PilotInterface {

    private String expirence;

    public Pilot(String passportID, String Fname, String Lname, 
            String DOB, String phoneNo, char gender,
            String username, String email, String password, String expirence) throws RemoteException {
        super(passportID, Fname, Lname, DOB, phoneNo, gender, username, email, password, "pilot");
        this.expirence = expirence;
    }

    public Pilot(ObjectId userId, String passportID, String Fname, String Lname,
            String DOB, String phoneNo, char gender,
            String username, String email, String password, String expirence) throws RemoteException {
        super(userId, passportID, Fname, Lname, DOB, phoneNo, gender, username, email, password, "pilot");
        this.expirence = expirence;
    }

    public Pilot() throws RemoteException {
        super();
    }

    public void insertPilot() {
        this.mapper.insert(this);
    }
    
    public String getExpirence() {
        return this.expirence;
    }
}
