package database.data;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.bson.types.ObjectId;

import database.DatabaseConnection;
import models.airline.Host;
import rmi.HostInterface;

public class HostDataMapper {
    private MongoCollection hostCollection = DatabaseConnection.getCollection("hosts");

    public Document createHostDocument(HostInterface host) throws RemoteException {
        Document hostDoc = new Document().append("passportNumber", host.getPassportNumber())
                .append("name", host.getName()).append("languages", host.getLanguages());

        if (host.getHostId() != null) {
            hostDoc.append("_id", host.getHostId());
        }
        return hostDoc;
    }

    public void insert(Host host) throws RemoteException {
        Document hostDoc = createHostDocument(host);
        hostCollection.insertOne(hostDoc);
    }

    private ArrayList<HostInterface> getHostsArrayList(MongoCursor<Document> cursor) {
        ArrayList<HostInterface> hosts = new ArrayList<HostInterface>();

        while (cursor.hasNext()) {
            Document hostDoc = cursor.next();

            ObjectId hostId = hostDoc.getObjectId("_id");
            String passportNo = hostDoc.getString("passportNumber");
            String name = hostDoc.getString("name");
            ArrayList<String> languages = hostDoc.get("languages", new ArrayList<String>().getClass());

            Host host;
            try {
                host = new Host(hostId, passportNo, name, languages);
                hosts.add(host);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return hosts;
    }

    public ArrayList<HostInterface> getHostsArrayList(Iterator<Document> cursor) {
        ArrayList<HostInterface> hosts = new ArrayList<HostInterface>();

        while (cursor.hasNext()) {
            Document hostDoc = cursor.next();

            ObjectId hostId = hostDoc.getObjectId("_id");
            String passportNo = hostDoc.getString("passportNumber");
            String name = hostDoc.getString("name");
            ArrayList<String> languages = hostDoc.get("languages", new ArrayList<String>().getClass());

            Host host;
            try {
                host = new Host(hostId, passportNo, name, languages);
                hosts.add(host);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return hosts;
    }

    public ArrayList<HostInterface> fetchHosts() {

        MongoCursor<Document> cursor = hostCollection.find().iterator();

        if (cursor == null) {
            return null;
        }

        ArrayList<HostInterface> hosts = getHostsArrayList(cursor);

        return hosts;
    }

    public boolean removeHost(Host host) {
        Document hostDoc = (Document) hostCollection.findOneAndDelete(Filters.eq("_id", host.getHostId()));

        if (hostDoc == null) {
            return false;
        }

        return true;
    }

}
