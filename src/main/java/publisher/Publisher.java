package publisher;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.File;
import org.bson.Document;
import subcriber.Subscriber;

import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class Publisher {
    private static List<File> files = new ArrayList<>();
    private List<Subscriber> subscribers = new ArrayList<>();
    private MongoClient mongoClient;
    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }
    private MongoCollection<Document> getCollection() {
        MongoDatabase database = mongoClient.getDatabase("test");
        return database.getCollection("observer");
    }

    public void addFile(File file) {
        if (!isFileInDatabase(file)) {
            files.add(file);
            notifySubscribers(file);
            saveFileToDatabase(file);
        }
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubcriber(Subscriber subscriber){
        subscribers.remove(subscriber);
    }
    private void notifySubscribers(File file) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(file);
        }
    }
    private void saveFileToDatabase(File file) {
        MongoCollection<Document> collection = getCollection();
        Document document = new Document();
        document.append("type", file.getType());
        document.append("name", file.getName());
        document.append("read", true);
        collection.insertOne(document);
    }

    private boolean isFileInDatabase(File file) {
        MongoCollection<Document> collection = getCollection();
        Document existingFile = collection.find(Filters.and(
                Filters.eq("type", file.getType()),
                Filters.eq("name", file.getName())
        )).first();
        return existingFile != null;
    }
}
