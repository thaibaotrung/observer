package publisher;

import model.File;
import subcriber.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private List<File> files = new ArrayList<>();
    private List<Subscriber> subscribers = new ArrayList<>();

    public void addFile(File file) {
        files.add(file);
        notifySubscribers(file);
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
}
