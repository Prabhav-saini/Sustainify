package sustainifyapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sustainifyapp.model.Tip;

import java.util.List;

@Repository
public class TipDao {

    @Autowired
    HibernateTemplate hibernateTemplate;

    public List<Tip> viewAllTips() {
        return this.hibernateTemplate.loadAll(Tip.class);
    }

    @Transactional
    public void createTip(Tip tip) {
        this.hibernateTemplate.save(tip);
    }
}
