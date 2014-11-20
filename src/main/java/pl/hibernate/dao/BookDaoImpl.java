package pl.hibernate.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.hibernate.model.Book;

import javax.annotation.Resource;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private SessionFactory session;

    @Override
    public void deleteBook(Long id) {

    }

    @Override
    public void deleteBook(Book book) {
        this.session.getCurrentSession().delete(book);
    }

    @Override
    public Book getBook(Long id) {
        Book book = (Book) this.session.getCurrentSession().get(Book.class, id);
        return book;
    }
}
