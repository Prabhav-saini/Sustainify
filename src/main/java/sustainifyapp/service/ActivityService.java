package sustainifyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sustainifyapp.dao.ActivityDao;
import sustainifyapp.model.Activity;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    ActivityDao activityDao;

    @Transactional
    public void createActivity(Activity activity) {
        activityDao.createActivity(activity);
    }

    @Transactional
    public List<Activity> viewAllActivitiesByUserId(Long userId) {
        return activityDao.getAllActivitiesByUserId(userId);
    }
}
