package models.airline;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import database.data.CrewDataMapper;

import rmi.CrewInterface;
import rmi.HostInterface;
import rmi.PilotInterface;

public class Crew extends UnicastRemoteObject implements CrewInterface, Serializable {
    private ObjectId crewId;
    private PilotInterface pilot;
    private ArrayList<PilotInterface> co_pilots;
    private ArrayList<HostInterface> hosts;
    private ArrayList<String> airplaneLevels;
    private boolean isAvailable;

    private CrewDataMapper mapper = new CrewDataMapper();

    public void createCrew(
            PilotInterface pilot, ArrayList<PilotInterface> co_pilots, ArrayList<HostInterface> hosts,
            ArrayList<String> airplaneLevels) {
        this.pilot = pilot;
        this.co_pilots = co_pilots;
        this.hosts = hosts;
        this.airplaneLevels = airplaneLevels;
        this.isAvailable = true;

        mapper.insert(this);
    }

    public Crew(ObjectId crewId, 
            PilotInterface pilot, ArrayList<PilotInterface> co_pilots, ArrayList<HostInterface> hosts,
            ArrayList<String> airplaneLevels, boolean isAvailable) throws RemoteException {
        this.crewId = crewId;
        this.pilot = pilot;
        this.co_pilots = co_pilots;
        this.hosts = hosts;
        this.airplaneLevels = airplaneLevels;
        this.isAvailable = isAvailable;
    }

    public Crew() throws RemoteException {
    }

    public ArrayList<CrewInterface> getCrews() {
        ArrayList<CrewInterface> crews = null;
        crews = mapper.fetchCrews();
        if (crews == null) {
            return null;
        }
        return crews;
    }

    public void updateCrew(boolean isAvailable) {
        this.isAvailable = isAvailable;
        mapper.updateCrew(this.crewId, this.isAvailable);
    }

    public ObjectId getCrewId() {
        return crewId;
    }

    public PilotInterface getPilot() {
        return pilot;
    }

    public ArrayList<PilotInterface> getCo_pilots() {
        return co_pilots;
    }

    public ArrayList<HostInterface> getHosts() {
        return hosts;
    }

    public ArrayList<String> getAirplaneLevels() {
        return airplaneLevels;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}
