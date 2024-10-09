package sustainifyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
import javax.servlet.http.HttpSession;
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
    public  RedirectView checkUser(@ModelAttribute User user, HttpServletRequest request) {
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
}
