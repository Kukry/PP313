package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImpl() {
    }

    @Override
    public List<User> allUsers() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        List<User> users = entityManager.createQuery(criteria).getResultList();
        return users;
    }

    @Override
    public User showUser(int id) {
        Query query = entityManager.createQuery("from User where id=:id");
        User user = (User) query.setParameter("id", id).getSingleResult();
        return user;
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User editedUser) {
        entityManager.merge(editedUser);
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
