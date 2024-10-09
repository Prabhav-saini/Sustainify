package sustainifyapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import sustainifyapp.model.Tip;

import java.util.List;

@Repository
public class TipDao {

    @Autowired
    HibernateTemplate hibernateTemplate;

    public List<Tip> viewAllTips() {
        return this.hibernateTemplate.loadAll(Tip.class);
    }
}
