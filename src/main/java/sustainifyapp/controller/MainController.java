package sustainifyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import sustainifyapp.model.Activity;
import sustainifyapp.model.Goal;
import sustainifyapp.model.Tip;
import sustainifyapp.model.User;
import sustainifyapp.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private TipService tipService;
    @Autowired
    private GoalService goalService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("/home")
    public String home(Model m, HttpSession session) {
        String username = (String) session.getAttribute("userEmail");
        if(StringUtils.isEmpty(username)) {
            return "login_user_form";
        }
        m.addAttribute("username",username);
        return "home";
    }

    @RequestMapping("/sign-up")
    public String signUpForm(Model m) {
        m.addAttribute("title", "Register To Sustainify");
        return "register_user_form";
    }

    @RequestMapping("/sign-in")
    public String signInFrom() {
        return "login_user_form";
    }

    @RequestMapping("/update-profile")
    public String updateProfileForm(Model m, HttpSession session) {
        String username = (String) session.getAttribute("userEmail");
        m.addAttribute("username", username);
        return "updateProfile";
    }

    @RequestMapping("/view-profile")
    public String viewProfile(Model m, HttpSession session) {
        String username = (String) session.getAttribute("userEmail");
        User user = userService.viewUser(username);
        m.addAttribute("user", user);
        return "userProfile";
    }
    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public RedirectView createUser(@ModelAttribute User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userEmail", user.getEmail());
        userService.createUser(user);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(request.getContextPath() + "/api/home");
        return redirectView;
    }

    @RequestMapping(value = "/update-user", method = RequestMethod.POST)
    public RedirectView updateUser(@ModelAttribute User user, HttpServletRequest request) {
        userService.updateUser(user);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(request.getContextPath() + "/api/home");
        return redirectView;
    }

    @RequestMapping(value = "/check-user", method = RequestMethod.POST)
    public RedirectView checkUser(@ModelAttribute User user, HttpServletRequest request) {
        RedirectView redirectView = new RedirectView();
        if(userService.isUserValid(user)) {
            HttpSession session = request.getSession();
            session.setAttribute("userEmail", user.getEmail());
            redirectView.setUrl(request.getContextPath() + "/api/home");
        } else {
            redirectView.setUrl(request.getContextPath() + "/api/sign-in?error=Invalid email or password");
        }
        return redirectView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public RedirectView logout(HttpServletRequest request) {
        RedirectView redirectView = new RedirectView();
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
       redirectView.setUrl(request.getContextPath() + "/api/sign-in");
        return redirectView;
    }

    @RequestMapping(value = "view/activities", method = RequestMethod.GET)
    public String viewActivities(Model m, HttpSession session) {
        String username = (String) session.getAttribute("userEmail");
        Long userId = userService.getUserIdByEmail(username);
        List<Activity> activities = activityService.viewAllActivitiesByUserId(userId);
        m.addAttribute("activities", activities);
        return "viewActivities";
    }

    @RequestMapping(value = "view/goals", method = RequestMethod.GET)
    public String viewAllGoals(Model m, HttpSession session) {
        String username = (String) session.getAttribute("userEmail");
        Long userId = userService.getUserIdByEmail(username);
        List<Goal> goals = goalService.viewAllGoalByUserId(userId);
        m.addAttribute("goals", goals);
        return "viewGoals";
    }

    @RequestMapping(value = "view/tips", method = RequestMethod.GET)
    public String viewAllTips(Model m, HttpSession session) {
        String username = (String) session.getAttribute("userEmail");
        List<Tip> tips = tipService.viewAllTips();
        m.addAttribute("tips", tips);
        return "viewTips";
    }

    @RequestMapping(value = "mark/goal/achieved", method = RequestMethod.POST)
    public String markGoalAchieved(@RequestParam("goalId") Long goalId, Model m, HttpSession session) {
        String username = (String) session.getAttribute("userEmail");
        Long userId = userService.getUserIdByEmail(username);
        goalService.markGoalAchieved(goalId);
        List<Goal> goals = goalService.viewAllGoalByUserId(userId);
        m.addAttribute("goals", goals);
        return "viewGoals";
    }

    @PostMapping("/submit/comment")
    public ResponseEntity<String> submitComment(@RequestParam Long tipId, @RequestParam String content, HttpSession session) {
        String username = (String) session.getAttribute("userEmail");
        Long userId = userService.getUserIdByEmail(username);
        commentService.addCommentToTip(tipId, userId, content);
        return ResponseEntity.ok("Comment submitted successfully.");
    }

    @RequestMapping(value = "/log-activity", method = RequestMethod.POST)
    public RedirectView logActivity(@ModelAttribute Activity activity, HttpServletRequest request, HttpSession session) {
        RedirectView redirectView = new RedirectView();
        String username = (String) session.getAttribute("userEmail");
        User user = userService.viewUser(username);
        activity.setUser(user);
        activity.setDateLogged(LocalDateTime.now());
        activityService.createActivity(activity);
        redirectView.setUrl(request.getContextPath() + "/api/home");
        return redirectView;
    }

    @RequestMapping(value = "/log-goal", method = RequestMethod.POST)
    public RedirectView logGoal(@ModelAttribute Goal goal, HttpServletRequest request, HttpSession session) {
        RedirectView redirectView = new RedirectView();
        String username = (String) session.getAttribute("userEmail");
        User user = userService.viewUser(username);
        goal.setUser(user);
        goal.setStartDate(LocalDateTime.now());
        goal.setEndDate(LocalDateTime.now().plusDays(Long.parseLong(goal.getTargetDays())));
        goalService.createGoal(goal);
        redirectView.setUrl(request.getContextPath() + "/api/home");
        return redirectView;
    }

    @RequestMapping(value = "/submit-tip", method = RequestMethod.POST)
    public RedirectView submitTip(@ModelAttribute Tip tip, HttpServletRequest request, HttpSession session) {
        RedirectView redirectView = new RedirectView();
        String username = (String) session.getAttribute("userEmail");
        User user = userService.viewUser(username);
        tip.setUsers(new ArrayList<>());
        tip.getUsers().add(user);
        tipService.createTip(tip);
        redirectView.setUrl(request.getContextPath() + "/api/home");
        return redirectView;
    }
}
