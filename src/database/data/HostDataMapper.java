package database.data;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import database.DatabaseConnection;
import models.airline.Host;

public class HostDataMapper {
    private MongoCollection hostCollection = DatabaseConnection.getCollection("hosts");

    public Document createHostDocument(Host host) {
        Document hostDoc = new Document().append("passportNumber", host.getPassportNumber())
                .append("name", host.getName()).append("languages", host.getLanguages());

        if (host.getHostId() != null) {
            hostDoc.append("_id", host.getHostId());
        }
        return hostDoc;
    }
    public void insert(Host host) {
        Document hostDoc = createHostDocument(host);
        hostCollection.insertOne(hostDoc);
    }

}
