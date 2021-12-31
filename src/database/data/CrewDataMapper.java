package database.data;

import java.util.ArrayList;
import java.util.Iterator;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.bson.Document;
import org.bson.types.ObjectId;

import database.DatabaseConnection;
import models.airline.Crew;
import models.airline.Host;
import models.user.Pilot;

public class CrewDataMapper {
    private MongoCollection crewCollection = DatabaseConnection.getCollection("crews");

    private UserDataMapper userDataMapper = new UserDataMapper();
    private HostDataMapper hostDataMapper = new HostDataMapper();

    public Document createCrewDocument(Crew crew) {
        Document pilotDoc = userDataMapper.createUserDocument(crew.getPilot());

        ArrayList<Document> coPioltsDoc = new ArrayList<Document>();
        crew.getCo_pilots().forEach((coPilot) -> {
            Document coPilotDoc = userDataMapper.createUserDocument(coPilot);
            coPioltsDoc.add(coPilotDoc);
        });

        ArrayList<Document> hostsDoc = new ArrayList<Document>();
        crew.getHosts().forEach((host) -> {
            Document hostDoc = hostDataMapper.createHostDocument(host);
            hostsDoc.add(hostDoc);
        });

        ArrayList<String> airplaneLevels = new ArrayList<String>();
        crew.getAirplaneLevels().forEach((level) -> {
            airplaneLevels.add(level.toString());
        });

        Document crewDoc = new Document().append("Pilot", pilotDoc).append("coPilots", coPioltsDoc).append("hosts",
                hostsDoc).append("airplaneLevels", airplaneLevels).append("isAvailable", crew.isIsAvailable());
        return crewDoc;
    }

    public void insert(Crew crew) {
        Document crewDoc = createCrewDocument(crew);
        crewCollection.insertOne(crewDoc);
    }

    private ArrayList<Crew> getCrewArrayList(MongoCursor<Document> cursor) {
        ArrayList<Crew> crews = new ArrayList<Crew>();

        while (cursor.hasNext()) {
            Document crewDoc = cursor.next();

            ObjectId crewId = crewDoc.getObjectId("_id");
            Pilot pilot = (Pilot) userDataMapper.createUserObj((Document)crewDoc.get("Pilot"));

            ArrayList<Pilot> co_pilots = new ArrayList<Pilot>();
            ArrayList<Document> coPilotsDoc = crewDoc.get("coPilots", new ArrayList<Document>().getClass());
            coPilotsDoc.forEach((coPilot) -> {
                co_pilots.add((Pilot) userDataMapper.createUserObj((Document) coPilot));
            });

            ArrayList<Document> hostsDoc = crewDoc.get("hosts", new ArrayList<Document>().getClass());
            Iterator<Document> hostcursor =  hostsDoc.iterator();
            ArrayList<Host> hosts = hostDataMapper.getHostsArrayList(hostcursor); 

            ArrayList<String> airplaneLevels = crewDoc.get("airplaneLevels", new ArrayList<String>().getClass());

            Boolean isAvailable = crewDoc.getBoolean("isAvailable");

            Crew crew = new Crew(crewId, pilot, co_pilots, hosts, airplaneLevels, isAvailable);
            crews.add(crew);
        }

        return crews;
    }

    public ArrayList<Crew> fetchCrews() {

        MongoCursor<Document> cursor = crewCollection.find().iterator();

        if (cursor == null) {
            return null;
        }

        ArrayList<Crew> crews = getCrewArrayList(cursor);

        return crews;
    }
}
