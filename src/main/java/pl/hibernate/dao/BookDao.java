package pl.hibernate.dao;

import pl.hibernate.model.Book;

public interface BookDao {

    public void deleteBook(Book book);
    public void deleteBook(Long id);
    public Book getBook(Long id);
}
