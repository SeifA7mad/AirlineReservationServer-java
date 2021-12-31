package models.user;

import java.time.LocalDate;

import org.bson.types.ObjectId;

import database.data.UserDataMapper;

public class Pilot extends User {

    private String expirence;

    public Pilot(String passportID, String Fname, String Lname, 
            String DOB, String phoneNo, char gender,
            String username, String email, String password, String expirence) {
        super(passportID, Fname, Lname, DOB, phoneNo, gender, username, email, password, "pilot");
        this.expirence = expirence;
    }

    public Pilot(ObjectId userId, String passportID, String Fname, String Lname,
            String DOB, String phoneNo, char gender,
            String username, String email, String password, String expirence) {
        super(userId, passportID, Fname, Lname, DOB, phoneNo, gender, username, email, password, "pilot");
        this.expirence = expirence;
    }

    public Pilot() {
        super();
    }

    public void insertPilot() {
        this.mapper.insert(this);
    }
    
    public String getExpirence() {
        return this.expirence;
    }
}
