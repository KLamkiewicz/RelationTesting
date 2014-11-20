package pl.dao.test;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.hibernate.dao.BookDao;
import pl.hibernate.dao.UserDao;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.hibernate.model.Book;
import pl.hibernate.model.User;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-cfg.xml"})
@DatabaseSetup("classpath:/dataset.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@EnableTransactionManagement
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class AllTests {

    @Autowired
    public UserDao userDao;

    @Autowired
    public BookDao bookDao;

    @Test
    @Ignore
    public void saveUser(){
        User u = new User();
        u.setName("Cimoche");
        userDao.save(u);
    }

    @Test
    @Ignore
    /*
        Przypadek 1 delete book without cascade
        Powoduje wystapienie bledu, posiadamy zaleznosci book w booksWant "a foreign key constraint fails "
        Przypadek 2 delete book with cascade
        Cascade.All co prawda eliminuje ten błąd, ale "przy okazji" usuwa wszystkich userów
     */
    public void deleteBook(){

        Book b = bookDao.getBook(new Long(1));
        bookDao.deleteBook(b);

    }

    @Test
    //@Ignore
    /*
        Przypadek 1 delete user without cascade
        Jako, ze User jest "ownerem" to zaleznosci z tabeli posredniczacej booksWant sa usuniete, ksiazki nienaruszone
        Przypadek 2 delete user with cascade, tutaj mamy dwa przypadki
        2a) Jesli usuwamy usera, ktory posiada jakies ksiazki, ktroych nikt inny nie posiada, usuwamy usera, wystapienia
         w booksWant i Book, ktore posiadal user
        2b) Jesli jednak jakis inny user posiadal te ksiazki to otrzymujemy "a foreign key constraint fails", poniewaz
        probujemy usunac Book, ktora posiada jakis inny uzytkownik
     */
    public void deleteUser(){

        User user = userDao.getUser(new Long(1));
        userDao.delete(user);

    }

}
