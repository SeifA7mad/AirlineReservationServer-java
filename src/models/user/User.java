package models.user;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import database.data.UserDataMapper;
import rmi.UserInterface;

import org.bson.Document;
import org.bson.types.ObjectId;

public abstract class User extends UnicastRemoteObject implements UserInterface {
    private ObjectId userId;
    private String passportID;
    private String Fname;
    private String Lname;
    private LocalDate DOB;
    private String phoneNo;
    private char gender;
    private Account acc;

    protected UserDataMapper mapper = new UserDataMapper();

    public User() throws RemoteException {

    }

    public User(
            String passportID, String Fname, String Lname, String DOB, String phoneNo, char gender,
            String username, String email, String password, String accType) throws RemoteException {
        this.passportID = passportID;
        this.Fname = Fname;
        this.Lname = Lname;
        this.DOB = LocalDate.parse(DOB);
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.acc = new Account().createAccount(username, email, password, accType, this);
    }

    public User(ObjectId userId,
            String passportID, String Fname, String Lname, String DOB, String phoneNo, char gender,
            String username, String email, String password, String accType) throws RemoteException {
        this.userId = userId;
        this.passportID = passportID;
        this.Fname = Fname;
        this.Lname = Lname;
        this.DOB = LocalDate.parse(DOB);
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.acc = new Account().createAccount(username, email, password, accType, this);
    }

    public void createAccount(String passportID, String Fname, String Lname, String DOB, String phoneNo, char gender,
            String username, String email, String password, String accType) throws RemoteException {
        this.passportID = passportID;
        this.Fname = Fname;
        this.Lname = Lname;
        this.DOB = LocalDate.parse(DOB);
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.acc = new Account().createAccount(username, email, password, accType, this);

        mapper.insert(this);
    }

    public UserInterface login(String email, String password) {
        User user = this.mapper.findUser(email, password);
        if (user == null) {
            System.out.println("wrong email or password");
            return null;
        }
        return user;
    }

    public ObjectId getUserId() {
        return this.userId;
    }

    public String getPassportID() {
        return passportID;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public char getGender() {
        return gender;
    }

    public Account getAcc() {
        return acc;
    }

    public String getUsername() {
        return this.acc.getUsername();
    }

    public String getEmail() {
        return this.acc.getEmail();
    }

    public String getPassword() {
        return this.acc.getPassword();
    }

    public String getAccountType() {
        return this.acc.getAccountType();
    }

}
