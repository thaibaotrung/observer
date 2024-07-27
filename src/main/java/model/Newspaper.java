package model;

public class Newspaper extends File {
    public Newspaper(String type, String name, boolean read) {
        super(type, name, read);
    }

    @Override
    public String getType() {
        return "Newspaper";
    }
}
