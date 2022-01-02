package database.data;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.bson.types.ObjectId;

import database.DatabaseConnection;
import models.ticket.Ticket;
import models.user.*;
import rmi.PassengerInterface;
import rmi.PilotInterface;
import rmi.UserInterface;

public class UserDataMapper {
    private MongoCollection userCollection = DatabaseConnection.getCollection("users");

    public Document createUserDocument(UserInterface user) throws RemoteException {
        System.out.println("x");
        Document userAccountDoc = new Document().append("username", user.getUsername())
                .append("email", user.getEmail()).append("password", user.getPassword())
                .append("accType", user.getAccountType());
                System.out.println("xx");
        Document userDoc = new Document().append("passportID", user.getPassportID()).append("Fname", user.getFname())
                .append("Lname", user.getLname()).append("DOB",
                        user.getDOB().toString())
                .append("phoneNo", user.getPhoneNo()).append("gender", user.getGender()).append("account",
                        userAccountDoc)
                .append("tickets", new ArrayList<Document>());
        System.out.println("xxx");
        if (user.getUserId() != null) {
            userDoc.append("_id", user.getUserId());
        }

        if (user.getAccountType() == "pilot") {
            userDoc.append("expirence", ((PilotInterface)user).getExpirence());
        } else if (user.getAccountType() == "passenger") {
            ArrayList<ObjectId> companionsDoc = new ArrayList<ObjectId>();

            ((PassengerInterface)user).getCompanions().forEach((companion) -> {
                try {
                    companionsDoc.add(((PassengerInterface) companion).getUserId());
                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            userDoc.append("companions", companionsDoc);
        }
        return userDoc;
    }

    public void insert(User user) throws RemoteException {
        Document userDoc = createUserDocument(user);

        userCollection.insertOne(userDoc);
    }

    public User createUserObj(Document userDoc) {
        Document userAccountDoc = (Document) userDoc.get("account");

        ObjectId userId = userDoc.getObjectId("_id");
        String passportID = userDoc.getString("passportID");
        String Fname = userDoc.getString("Fname");
        String Lname = userDoc.getString("Lname");
        String DOB = userDoc.getString("DOB");
        String phoneNo = userDoc.getString("phoneNo");
        char gender = userDoc.getString("gender").charAt(0);
        String username = userAccountDoc.getString("username");
        String email = userAccountDoc.getString("email");
        String password = userAccountDoc.getString("password");

        User user = null;
        if (userAccountDoc.getString("accType").equals("pilot")) {
            String expirence = userDoc.getString("expirence");
            try {
                user = new Pilot(userId, passportID, Fname, Lname, DOB, phoneNo, gender,
                        username, email, password, expirence);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (userAccountDoc.getString("accType").equals("passenger")) {

            // CHECK IF COMPANION != NULL AND TICKETS != NULL
            ArrayList<ObjectId> companionsDoc = userDoc.get("companions", new ArrayList<ObjectId>().getClass());
            ArrayList<PassengerInterface> companions = new ArrayList<PassengerInterface>();
            ArrayList<Ticket> tickets = new ArrayList<Ticket>();

            companionsDoc.forEach((id) -> {
                companions.add((Passenger) findPassengerBy(id));
            });

            try {
                user = new Passenger(userId, passportID, Fname, Lname, DOB, phoneNo, gender,
                        username, email, password, companions, tickets);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (userAccountDoc.getString("accType").equals("admin")) {
            try {
                user = new Admin(userId, passportID, Fname, Lname, DOB, phoneNo, gender,
                        username, email, password);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return user;
    }

    public User findUser(String userEmail, String userPassword) {
        Document userDoc = (Document) userCollection.find(Filters.and(Filters.eq("account.password",
                userPassword), Filters.eq("account.email", userEmail))).first();
        if (userDoc == null) {
            return null;
        }
        return createUserObj(userDoc);
    }

    public User findPassengerBy(String email) {
        Document userDoc = (Document) userCollection.find(Filters.eq("account.email", email)).first();
        if (userDoc == null) {
            return null;
        }
        return createUserObj(userDoc);
    }

    public User findPassengerBy(ObjectId id) {
        Document userDoc = (Document) userCollection.find(Filters.eq("_id", id)).first();
        if (userDoc == null) {
            return null;
        }
        return createUserObj(userDoc);
    }

    public ArrayList<PilotInterface> getPilotsArrayList(MongoCursor<Document> cursor) {
        ArrayList<PilotInterface> pilots = new ArrayList<PilotInterface>();

        while (cursor.hasNext()) {
            Document pilotDoc = cursor.next();

            pilots.add((Pilot) createUserObj(pilotDoc));
        }

        return pilots;
    }

    public ArrayList<PilotInterface> fetchPilots() {
        MongoCursor<Document> cursor = userCollection.find(Filters.eq("account.accType", "pilot")).iterator();

        if (cursor == null) {
            return null;
        }
        ArrayList<PilotInterface> pilots = getPilotsArrayList(cursor);

        return pilots;
    }
}
