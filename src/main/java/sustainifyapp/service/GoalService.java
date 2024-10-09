package sustainifyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sustainifyapp.dao.GoalDao;
import sustainifyapp.model.Goal;

import java.util.List;

@Service
public class GoalService {

    @Autowired
    GoalDao goalDao;

    @Transactional
    public void createGoal(Goal goal) {
        goalDao.createGoal(goal);
    }

    @Transactional
    public List<Goal> viewAllGoalByUserId(Long userId) {
        return goalDao.getAllGoalsByUserId(userId);
    }

    @Transactional
    public Goal viewGoal(Long goalId) {
        return goalDao.viewGoalById(goalId);
    }

    @Transactional
    public void markGoalAchieved(Long goalId) {
        Goal goal = viewGoal(goalId);
        goal.setAchieved(true);
         goalDao.markGoalAchievedById(goal);
    }
}
