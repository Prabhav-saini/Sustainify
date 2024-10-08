package sustainifyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import sustainifyapp.model.Activity;
import sustainifyapp.model.Goal;
import sustainifyapp.model.Tip;
import sustainifyapp.model.User;
import sustainifyapp.service.ActivityService;
import sustainifyapp.service.GoalService;
import sustainifyapp.service.TipService;
import sustainifyapp.service.UserService;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/home")
    public String home(Model m) {
        Activity recentActivity = null;
        Goal recentGoal = null;
        List<Tip> recentTips = null;

        m.addAttribute("recentActivity", recentActivity != null ? recentActivity.getType() : "No recent activity");
        m.addAttribute("recentGoal", recentGoal != null ? recentGoal.getDescription() : "No recent goal");
        m.addAttribute("recentTips", recentTips);
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

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public RedirectView createUser(@ModelAttribute User user, HttpServletRequest request) {
        userService.createUser(user);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(request.getContextPath() + "/api/home");
        return redirectView;
    }

    @RequestMapping(value = "/check-user", method = RequestMethod.POST)
    public  RedirectView checkUser(@ModelAttribute User user, HttpServletRequest request) {
        RedirectView redirectView = new RedirectView();
        if(userService.isUserExist(user)) {
            redirectView.setUrl(request.getContextPath() + "/api/home");
        } else {
            redirectView.setUrl(request.getContextPath() + "/api/sign-in?error=Invalid email or password");
        }
        return redirectView;
    }
}
