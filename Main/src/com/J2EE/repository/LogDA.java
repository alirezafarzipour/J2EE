package com.J2EE.repository;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;


public class LogDA {
    public void insert(String username, String log) {
        try (MongoClient mongoClient = new MongoClient("localhost:27017")) {
            DB db = mongoClient.getDB("orcl");

            DBCollection dbCollection = db.getCollection("crud_log");

            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("username", username);
            basicDBObject.put("log", log);

            dbCollection.insert(basicDBObject);
        }
    }

    public List<String> selectAll() {
        List<String> logList = new ArrayList<>();
        try (MongoClient mongoClient = new MongoClient("localhost:27017")) {
            DB db = mongoClient.getDB("orcl");

            DBCollection dbCollection = db.getCollection("crud_log");

            DBCursor dbCursor = dbCollection.find();
            for (DBObject dbObject : dbCursor) {
                logList.add(dbObject.get("username").toString() + " : " + dbObject.get("log").toString());
            }
        }
        return logList;
    }

    public List<String> selectByUsername(String username) {
        List<String> logList = new ArrayList<>();
        try (MongoClient mongoClient = new MongoClient("localhost:27017")) {
            DB db = mongoClient.getDB("orcl");

            DBCollection dbCollection = db.getCollection("crud_log");

            BasicDBObject query = new BasicDBObject();
            query.put("username", username);

            DBCursor dbCursor = dbCollection.find(query);
            for (DBObject dbObject : dbCursor) {
                logList.add(dbObject.get("username").toString() + " : " + dbObject.get("log").toString());
            }
        }
        return logList;
    }

}
