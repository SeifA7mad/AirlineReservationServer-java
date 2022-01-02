package models.user;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import rmi.PilotInterface;
import rmi.UserInterface;

public class Pilot extends User implements PilotInterface, Serializable {

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

    public void insertPilot() throws RemoteException {
        this.mapper.insert(this);
    }

    public ArrayList<PilotInterface> getAllPilots() {
        ArrayList<PilotInterface> pilots = null;
        pilots = this.mapper.fetchPilots();
        if (pilots == null) {
            return null;
        }
        return pilots;
    }

    public String getExpirence() {
        return this.expirence;
    }

    // public void createAccount(String passportID, String Fname, String Lname, String DOB, String phoneNo, char gender,
    //         String username, String email, String password, String accType) {
    //     this.createAccount(passportID, Fname, Lname, DOB, phoneNo, gender, username, email, password, accType);
    // }

    // public UserInterface login(String email, String password) {
    //     return this.login(email, password);
    // }
}
