package model;

public class Movie extends File {
    public Movie(String type, String name, boolean read) {
        super(type, name, read);
    }


    @Override
    public String getType() {
        return "Movie";
    }
}
