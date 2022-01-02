package models.airline;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import database.data.HostDataMapper;
import rmi.HostInterface;

public class Host extends UnicastRemoteObject implements HostInterface, Serializable {
    private ObjectId hostId;
    private String passportNumber;
    private String name;
    private ArrayList<String> languages;

    private HostDataMapper mapper = new HostDataMapper();

    public Host(ObjectId hostId, String passportNo, String name, ArrayList<String> languages) throws RemoteException {
        this.hostId = hostId;
        this.passportNumber = passportNo;
        this.name = name;
        this.languages = languages;
    }

    public Host() throws RemoteException {
    }

    @Override
    public void addHost(String passportNo, String name, ArrayList<String> languages) {
        this.passportNumber = passportNo;
        this.name = name;
        this.languages = languages;
        insertHost();
    }

    public void insertHost() {
        mapper.insert(this);
    }

    public boolean removeHost() {
        return this.mapper.removeHost(this);
    }

    public ArrayList<Host> getAllHosts() {
        ArrayList<Host> hosts = null;
        hosts = mapper.fetchHosts();
        if (hosts == null) {
            return null;
        }
        return hosts;
    }

    public ObjectId getHostId() {
        return this.hostId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

}
