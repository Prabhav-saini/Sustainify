package sustainifyapp.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sustainifyapp.model.Activity;
import sustainifyapp.model.Goal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Repository
public class GoalDao {

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Transactional
    public void createGoal(Goal goal) {
        this.hibernateTemplate.save(goal);
    }

    public List<Goal> getAllGoalsByUserId(Long userId) {
        Session session = Objects.requireNonNull(hibernateTemplate.getSessionFactory()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder(); // Criteria Builder

        CriteriaQuery<Goal> criteriaQuery = criteriaBuilder.createQuery(Goal.class); // Criteria query
        Root<Goal> root = criteriaQuery.from(Goal.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("user").get("id"), userId)); // Build query with where clause

        return session.createQuery(criteriaQuery).getResultList();
    }
}
