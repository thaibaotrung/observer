package subcriber;

import model.File;

public class MovieSubscriber implements Subscriber {
    private String name;

    public MovieSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(File file) {
        if (file.getType().equals("Movie") && !file.getRead()) {
            System.out.println("MovieSubscriber " + name + " notified: New Movie - " + file.getName());
        }
    }

    @Override
    public String getSubscriptionType() {
        return "Movie";
    }
}
