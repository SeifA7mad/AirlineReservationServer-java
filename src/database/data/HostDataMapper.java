package database.data;

import java.util.ArrayList;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.bson.Document;
import org.bson.types.ObjectId;

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

    private ArrayList<Host> getHostsArrayList(MongoCursor<Document> cursor) {
        ArrayList<Host> hosts = new ArrayList<Host>();

        while (cursor.hasNext()) {
            Document hostDoc = cursor.next();

            ObjectId hostId = hostDoc.getObjectId("_id");
            String passportNo = hostDoc.getString("passportNumber");
            String name = hostDoc.getString("name");
            ArrayList<String> languages = hostDoc.get("languages", new ArrayList<String>().getClass());

            Host host = new Host(hostId, passportNo, name, languages);
            hosts.add(host);
        }

        return hosts;
    }

    public ArrayList<Host> fetchHosts() {

        MongoCursor<Document> cursor = hostCollection.find().iterator();

        if (cursor == null) {
            return null;
        }

        ArrayList<Host> hosts = getHostsArrayList(cursor);

        return hosts;
    }

}
