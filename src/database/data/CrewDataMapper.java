package database.data;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.bson.Document;
import org.bson.types.ObjectId;

import database.DatabaseConnection;
import models.airline.Crew;
import models.airline.Host;
import models.user.Pilot;
import rmi.CrewInterface;
import rmi.HostInterface;
import rmi.PilotInterface;

public class CrewDataMapper {
    private MongoCollection crewCollection = DatabaseConnection.getCollection("crews");

    private UserDataMapper userDataMapper = new UserDataMapper();
    private HostDataMapper hostDataMapper = new HostDataMapper();

    public Document createCrewDocument(Crew crew) {
        Document pilotDoc = userDataMapper.createUserDocument((Pilot)crew.getPilot());

        ArrayList<Document> coPioltsDoc = new ArrayList<Document>();
        crew.getCo_pilots().forEach((coPilot) -> {
            Document coPilotDoc = userDataMapper.createUserDocument((Pilot)coPilot);
            coPioltsDoc.add(coPilotDoc);
        });

        ArrayList<Document> hostsDoc = new ArrayList<Document>();
        crew.getHosts().forEach((host) -> {
            Document hostDoc = hostDataMapper.createHostDocument((Host)host);
            hostsDoc.add(hostDoc);
        });

        ArrayList<String> airplaneLevels = new ArrayList<String>();
        crew.getAirplaneLevels().forEach((level) -> {
            airplaneLevels.add(level.toString());
        });

        Document crewDoc = new Document().append("Pilot", pilotDoc).append("coPilots", coPioltsDoc).append("hosts",
                hostsDoc).append("airplaneLevels", airplaneLevels).append("isAvailable", crew.isIsAvailable());

        if (crew.getCrewId() != null) {
            crewDoc.append("_id", crew.getCrewId());
        }
        return crewDoc;
    }

    public void insert(Crew crew) {
        Document crewDoc = createCrewDocument(crew);
        crewCollection.insertOne(crewDoc);
    }

    public Crew createCrewObj(Document crewDoc) {
        ObjectId crewId = crewDoc.getObjectId("_id");
        Pilot pilot = (Pilot) userDataMapper.createUserObj((Document) crewDoc.get("Pilot"));

        ArrayList<PilotInterface> co_pilots = new ArrayList<PilotInterface>();
        ArrayList<Document> coPilotsDoc = crewDoc.get("coPilots", new ArrayList<Document>().getClass());
        coPilotsDoc.forEach((coPilot) -> {
            co_pilots.add((Pilot) userDataMapper.createUserObj((Document) coPilot));
        });

        ArrayList<Document> hostsDoc = crewDoc.get("hosts", new ArrayList<Document>().getClass());
        Iterator<Document> hostcursor = hostsDoc.iterator();
        ArrayList<HostInterface> hosts = hostDataMapper.getHostsArrayList(hostcursor);

        ArrayList<String> airplaneLevels = crewDoc.get("airplaneLevels", new ArrayList<String>().getClass());

        Boolean isAvailable = crewDoc.getBoolean("isAvailable");

        try {
            return new Crew(crewId, pilot, co_pilots, hosts, airplaneLevels, isAvailable);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<CrewInterface> getCrewArrayList(MongoCursor<Document> cursor) {
        ArrayList<CrewInterface> crews = new ArrayList<CrewInterface>();

        while (cursor.hasNext()) {
            Document crewDoc = cursor.next();

            crews.add(createCrewObj(crewDoc));
        }

        return crews;
    }

    public ArrayList<CrewInterface> fetchCrews() {

        MongoCursor<Document> cursor = crewCollection.find().iterator();

        if (cursor == null) {
            return null;
        }
        ArrayList<CrewInterface> crews = getCrewArrayList(cursor);

        return crews;
    }

    public void updateCrew(ObjectId crewId, boolean isAvailable) {
        crewCollection.updateOne(Filters.eq("_id", crewId), Updates.set("isAvailable", isAvailable));
    }
}
