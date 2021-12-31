package models.airline;

import java.util.ArrayList;

import org.bson.types.ObjectId;

import database.data.CrewDataMapper;

import models.user.Pilot;

public class Crew {
    private ObjectId crewId;
    private Pilot pilot;
    private ArrayList<Pilot> co_pilots;
    private ArrayList<Host> hosts;
    private ArrayList<String> airplaneLevels;
    private boolean isAvailable;

    private CrewDataMapper mapper = new CrewDataMapper();

    public void createCrew(Pilot pilot, ArrayList<Pilot> co_pilots, ArrayList<Host> hosts,
            ArrayList<String> airplaneLevels) {
        this.pilot = pilot;
        this.co_pilots = co_pilots;
        this.hosts = hosts;
        this.airplaneLevels = airplaneLevels;
        this.isAvailable = true;

        mapper.insert(this);
    }

    public Crew(ObjectId crewId, Pilot pilot, ArrayList<Pilot> co_pilots, ArrayList<Host> hosts,
            ArrayList<String> airplaneLevels, boolean isAvailable) {
        this.crewId = crewId;
        this.pilot = pilot;
        this.co_pilots = co_pilots;
        this.hosts = hosts;
        this.airplaneLevels = airplaneLevels;
        this.isAvailable = isAvailable;
    }

    public Crew() {
    }

    public ObjectId getCrewId() {
        return crewId;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public ArrayList<Pilot> getCo_pilots() {
        return co_pilots;
    }

    public ArrayList<Host> getHosts() {
        return hosts;
    }

    public ArrayList<String> getAirplaneLevels() {
        return airplaneLevels;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

}
