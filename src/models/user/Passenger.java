package models.user;

import java.util.ArrayList;

import org.bson.types.ObjectId;

import models.airline.airlineTrip.AirlineTrip;
import models.user.airlineTripObserver.AirlineTripObserver;

public class Passenger extends User implements AirlineTripObserver {
    private ArrayList<ObjectId> companions;

    public Passenger(String passportID, String Fname, String Lname,
            String DOB, String phoneNo, char gender,
            String username, String email, String password) {
        super(passportID, Fname, Lname, DOB, phoneNo, gender, username, email, password, "passenger");

        this.companions = new ArrayList<ObjectId>();
    }

    public Passenger(ObjectId userId, String passportID, String Fname, String Lname,
            String DOB, String phoneNo, char gender,
            String username, String email, String password, ArrayList<ObjectId> companions) {
        super(userId, passportID, Fname, Lname, DOB, phoneNo, gender, username, email, password, "passenger");
        this.companions = companions;
    }

    public void insertPassenger() {
        this.mapper.insert(this);
    }

    public ArrayList<ObjectId> getCompanions() {
        return this.companions;
    }

    @Override
    public void update(AirlineTrip airlineTrip, String news) {
        // TODO Auto-generated method stub

    }

}
