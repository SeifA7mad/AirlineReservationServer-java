package models.airline;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import database.data.AirportDataMapper;
import rmi.AirportInterface;

public class Airport extends UnicastRemoteObject implements AirportInterface, Serializable {
    private ObjectId airportID;
    private String name;
    private String country;
    private ArrayList<Integer> terminalNumber;
    private ArrayList<Integer> hallNumber;

    private AirportDataMapper mapper = new AirportDataMapper();

    public Airport() throws RemoteException {

    }

    public Airport(ObjectId airportID, String name, String country, ArrayList<Integer> terminalNumber,
            ArrayList<Integer> hallNumber) throws RemoteException {
        this.airportID = airportID;
        this.name = name;
        this.country = country;
        this.terminalNumber = terminalNumber;
        this.hallNumber = hallNumber;
    }

    @Override
    public void addAirport(String name, String country, ArrayList<Integer> terminalNumber,
            ArrayList<Integer> hallNumber) throws RemoteException {
        this.name = name;
        this.country = country;
        this.terminalNumber = terminalNumber;
        this.hallNumber = hallNumber;

        mapper.insert(this);
    }

    @Override
    public ArrayList<AirportInterface> getAirports() {
        ArrayList<AirportInterface> airports = null;
        airports = mapper.fetchAirports();
        if (airports == null) {
            return null;
        }
        return airports;
    }

    @Override
    public ObjectId getAirportId() {
        return this.airportID;
    }

    public void setAirportID(ObjectId airportID) {
        this.airportID = airportID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setObj(ArrayList<Integer> obj) {
        this.terminalNumber = obj;
    }

    public void setHallNumber(ArrayList<Integer> hallNumber) {
        this.hallNumber = hallNumber;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public ArrayList<Integer> getTerminalNumber() {
        return terminalNumber;
    }

    public ArrayList<Integer> getHallNumber() {
        return hallNumber;
    }

}
