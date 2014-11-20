package pl.hibernate.dao;

import pl.hibernate.model.User;

public interface UserDao {

    public void save(User user);
    public void delete(User user);
    public void delete(Long id);
    public User getUser(Long id);

}
