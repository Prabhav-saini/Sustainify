package sustainifyapp.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import sustainifyapp.model.Activity;
import sustainifyapp.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Repository
public class ActivityDao {

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Transactional
    public void createActivity(Activity activity) {
        this.hibernateTemplate.save(activity);
    }

    public List<Activity> getAllActivitiesByUserId(Long userId) {
        Session session = Objects.requireNonNull(hibernateTemplate.getSessionFactory()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder(); // Criteria Builder

        CriteriaQuery<Activity> criteriaQuery = criteriaBuilder.createQuery(Activity.class); // Criteria query
        Root<Activity> root = criteriaQuery.from(Activity.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("user").get("id"), userId)); // Build query with where clause

        return session.createQuery(criteriaQuery).getResultList();
    }
}
