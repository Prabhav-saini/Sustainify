package sustainifyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import sustainifyapp.model.User;
import sustainifyapp.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home() {
        return "index";
    }

    @RequestMapping("/sign-up")
    public String signUpForm(Model m) {
        m.addAttribute("title", "Register To Sustainify");
        return "register_user_form";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public RedirectView createUser(@ModelAttribute User user, HttpServletRequest request) {
        userService.createUser(user);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(request.getContextPath() + "/api/home");
        return redirectView;
    }
}
