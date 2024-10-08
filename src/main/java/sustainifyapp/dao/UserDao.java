package sustainifyapp.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sustainifyapp.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public void createUser(User user) {
        this.hibernateTemplate.save(user);
    }

    @Transactional
    public void updateUser(User user) {
        this.hibernateTemplate.update(user);
    }

    public void deleteUser(User user) {
        this.hibernateTemplate.delete(user);
    }

    @Transactional
    public User getUserByEmailPassword(User user) {
        Session session = Objects.requireNonNull(hibernateTemplate.getSessionFactory()).getCurrentSession();
        CriteriaBuilder criteria = session.getCriteriaBuilder(); // Criteria Builder

        CriteriaQuery<User> criteriaQuery = criteria.createQuery(User.class); // Criteria query
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteria.equal(root.get("email"), user.getEmail()),
                criteria.equal(root.get("password"), user.getPassword())); // Build query with where clause

        return session.createQuery(criteriaQuery).uniqueResult();
    }

    public User getUserByEmail(String email) {
        Session session = Objects.requireNonNull(hibernateTemplate.getSessionFactory()).getCurrentSession();
        CriteriaBuilder criteria = session.getCriteriaBuilder(); // Criteria Builder

        CriteriaQuery<User> criteriaQuery = criteria.createQuery(User.class); // Criteria query
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteria.equal(root.get("email"), email)); // Build query with where clause

        return session.createQuery(criteriaQuery).uniqueResult();
    }

    public Long getUserId(String email) {
        String hql = "SELECT u.id FROM User u WHERE u.email = :email";
        List<Long> userIds = (List<Long>) hibernateTemplate.findByNamedParam(hql, "email", email);

        return userIds.stream().findFirst().orElse(null);
    }
}
