package subcriber;

import model.File;

public class NewspaperSubscriber implements Subscriber {
    private String name;

    public NewspaperSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(File file) {
        if (file.getType().equals("Newspaper") && !file.getRead()) {
            System.out.println("NewspaperSubscriber " + name + " notified: New Newspaper - " + file.getName());
        }
    }

    @Override
    public String getSubscriptionType() {
        return "Newspaper";
    }
}
