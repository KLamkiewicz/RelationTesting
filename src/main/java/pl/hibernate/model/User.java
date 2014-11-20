package pl.hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    //Przypadek 1
    //@ManyToMany
    //Przypadek 2
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "booksWant",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "bookId")
    )
    private List<Book> booksWant = new ArrayList<Book>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooksWant() {
        return booksWant;
    }

    public void setBooksWant(List<Book> booksWant) {
        this.booksWant = booksWant;
    }
}
