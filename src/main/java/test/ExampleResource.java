package test;

import model.File;
import model.Movie;
import model.Newspaper;
import publisher.Publisher;
import subcriber.MovieSubscriber;
import subcriber.NewspaperSubscriber;
import subcriber.Subscriber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExampleResource {

    public static void main(String args[]){
        Publisher publisher = new Publisher();

        Subscriber movieSubscriber1 = new MovieSubscriber("Alice");
        Subscriber movieSubscriber2 = new MovieSubscriber("Bob");
        Subscriber newspaperSubscriber1 = new NewspaperSubscriber("Charlie");
        Subscriber newspaperSubscriber2 = new NewspaperSubscriber("David");

        publisher.addSubscriber(movieSubscriber1);
        publisher.addSubscriber(movieSubscriber2);
        publisher.addSubscriber(newspaperSubscriber1);
        publisher.addSubscriber(newspaperSubscriber2);
        
        String filePath = "D:\\observer\\publisher.txt";

        List<Movie> movies = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 3) {
                    String type = parts[0].trim();
                    String name = parts[1].trim();
                    boolean read = Boolean.parseBoolean(parts[2].trim());

                    if (type.equals("Movie")) {
                        Movie movie = new Movie(type, name, read);
                        publisher.addFile(movie);
                    }
                    if (type.equals("Newspaper")) {
                        Newspaper newspaper = new Newspaper(type, name, read);
                        publisher.addFile(newspaper);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
