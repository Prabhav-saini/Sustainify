package sustainifyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sustainifyapp.dao.TipDao;
import sustainifyapp.model.Tip;

import java.util.List;

@Service
public class TipService {

    @Autowired
    TipDao tipDao;

    @Transactional
    public List<Tip> viewAllTips() {
        return tipDao.viewAllTips();
    }
}
