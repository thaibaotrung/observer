package subcriber;

import model.File;

public interface Subscriber {
    void update(File file);
    String getSubscriptionType();
}
