package spring.security.dao;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import spring.security.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void removeUser(Long id) {
        Query query = entityManager.createQuery("delete from User U where U.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> userList() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public User findUserByUsername(String username) {
        TypedQuery<User> user = entityManager.createQuery("from User U where U.username = :username", User.class);
        user.setParameter("username", username);
        if (user.getSingleResult()==null){
            throw new UsernameNotFoundException("User not found");
        }
        return user.getSingleResult();
    }
}
