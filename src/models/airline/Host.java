package models.airline;

import java.util.ArrayList;

import org.bson.types.ObjectId;

import database.data.HostDataMapper;

public class Host {
    private ObjectId hostId;
    private String passportNumber;
    private String name;
    private ArrayList<String> languages;

    private HostDataMapper mapper = new HostDataMapper();

    public Host(ObjectId hostId, String passportNo, String name, ArrayList<String> languages) {
        this.hostId = hostId;
        this.passportNumber = passportNo;
        this.name = name;
        this.languages = languages;
    }

    public Host() {
    }

    public void addHost(String passportNo, String name, ArrayList<String> languages) {
        this.passportNumber = passportNo;
        this.name = name;
        this.languages = languages;
        insertHost();
    }

    public void insertHost() {
        mapper.insert(this);
    }

    public boolean removeHost(Host H) {
        return true;
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
