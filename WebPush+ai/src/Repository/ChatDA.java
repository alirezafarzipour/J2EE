package Repository;

import ai.Generator;
import ai.Type;
import com.mongodb.*;
import org.json.simple.JSONArray;
import ai.Bayes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class ChatDA {
    public void insert(ArrayList<String> users, ArrayList<String> message) {
        String messageForAI = "";
        for (String s :
                message) {
            messageForAI += (s + " ");
        }

        String result = "";
        try {
            result = getResult(messageForAI);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (MongoClient mongoClient = new MongoClient("localhost:27017")) {
            DB db = mongoClient.getDB("orcl");

            DBCollection dbCollection = db.getCollection("chat_log");

            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("users", users);
            basicDBObject.put("message", message);
            basicDBObject.put("time", new Date());
            basicDBObject.put("result", result);

            dbCollection.insert(basicDBObject);
        }
    }

    public JSONArray selectAll() {
        try (MongoClient mongoClient = new MongoClient("localhost:27017")) {
            DB db = mongoClient.getDB("orcl");

            DBCollection dbCollection = db.getCollection("chat_log");

            DBCursor dbCursor = dbCollection.find();//select
            JSONArray jsonArray = new JSONArray();
            for (DBObject dbObject : dbCursor) {
                ////print on server
                System.out.print("time: " + dbObject.get("time"));
                System.out.print(" | users: " + dbObject.get("users"));
                System.out.println(" | result: " + dbObject.get("result"));

                ////send to web view
                String result = dbObject.get("time")
                        +"  ---<b style=\"color: red;\">Users:</b> "+dbObject.get("users")
                        +"  ---<b style=\"color: red;\">Result:</b> "+dbObject.get("result");
                jsonArray.add(result);
            }
            return jsonArray;
        }
    }

    public String getResult(String msg) throws Exception {
        Generator generator = new Generator();
        generator.setTitle("chat");
        generator.addMetaData("content", Type.STRING);
        generator.addMetaData("type", "{salary,work,vacation,problem}");

        generator.addRow(Arrays.asList("'" + msg + "'", "?"));

        Bayes bayes = new Bayes();
        return bayes.process("C:\\Users\\AR\\Desktop\\dataset\\chat.arff", generator.exportARFFFile());
    }

}
