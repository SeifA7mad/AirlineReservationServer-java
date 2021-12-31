package database.data;

import java.util.ArrayList;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import database.DatabaseConnection;
import models.airline.Crew;

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
}
