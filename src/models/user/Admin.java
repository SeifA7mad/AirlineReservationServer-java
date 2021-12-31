package models.user;

import java.time.LocalDate;

import org.bson.types.ObjectId;

import database.data.UserDataMapper;

public class Admin extends User {


    public Admin(String passportID, String Fname, String Lname, String DOB, String phoneNo, char gender,
            String username, String email, String password) {
        super(passportID, Fname, Lname, DOB, phoneNo, gender, username, email, password, "admin");
    }

    public Admin(ObjectId userId, String passportID, String Fname, String Lname, String DOB, String phoneNo, char gender,
            String username, String email, String password) {
        super(userId, passportID, Fname, Lname, DOB, phoneNo, gender, username, email, password, "admin");
    }

    public void insertAdmin() {
        this.mapper.insert(this);
    }

}
