package pl.hibernate.dao;

import pl.hibernate.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory session;

    @Override
    public void save(User user) {
        this.session.getCurrentSession().save(user);
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void delete(Long id) {

    }
}
