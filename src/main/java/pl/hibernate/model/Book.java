package pl.hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    //Przypadek 1
    @ManyToMany(mappedBy = "booksWant")
    //Przypadek 2
    //@ManyToMany(mappedBy = "booksWant", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<User>();

}
