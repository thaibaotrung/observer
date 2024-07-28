package model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;


public abstract class File {

    private String type;
    private String name;
    private boolean read;


    public File(String type, String name, boolean read) {
        this.type = type;
        this.name = name;
        this.read = read;
    }

    public String getName() {
        return name;
    }

    public boolean getRead(){
        return read;
    }

    public abstract String getType();


}