package database.data;

import java.util.ArrayList;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.bson.types.ObjectId;

import database.DatabaseConnection;
import models.user.*;

public class UserDataMapper {
    private MongoCollection userCollection = DatabaseConnection.getCollection("users");

    public Document createUserDocument(User user) {
        Document userAccountDoc = new Document().append("username", user.getAcc().getUsername())
                .append("email", user.getAcc().getEmail()).append("password", user.getAcc().getPassword())
                .append("accType", user.getAcc().getAccountType());

        Document userDoc = new Document().append("passportID", user.getPassportID()).append("Fname", user.getFname())
                .append("Lname", user.getLname()).append("DOB",
                        user.getDOB().toString())
                .append("phoneNo", user.getPhoneNo()).append("gender", user.getGender()).append("account",
                        userAccountDoc);

        if (user.getUserId() != null) {
            userDoc.append("_id", user.getUserId());
        }

        if (user.getAcc().getAccountType() == "pilot") {
            userDoc.append("expirence", ((Pilot) user).getExpirence());
        } else if (user.getAcc().getAccountType() == "passenger") {
            userDoc.append("companions", ((Passenger) user).getCompanions());
        }
        return userDoc;
    }

    public void insert(User user) {
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
            user = new Pilot(userId, passportID, Fname, Lname, DOB, phoneNo, gender,
                    username, email, password, expirence);
        } else if (userAccountDoc.getString("accType").equals("passenger")) {
            ArrayList<ObjectId> companions = userDoc.get("companions", new ArrayList<ObjectId>().getClass());
            user = new Passenger(userId, passportID, Fname, Lname, DOB, phoneNo, gender,
                    username, email, password, companions);
        } else if (userAccountDoc.getString("accType").equals("admin")) {
            user = new Admin(userId, passportID, Fname, Lname, DOB, phoneNo, gender,
                    username, email, password);
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
}
