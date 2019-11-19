package MessengerApp.controller;

import MessengerApp.model.Account;
import MessengerApp.model.DAO.AccountDAO;
import MessengerApp.service.MessengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    MessengerService messengerService;

    @Autowired
    public void setMessengerService(MessengerService messengerService) {
        this.messengerService = messengerService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public ModelAndView logoutSuccessfulPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("logout_page_successful");
        return modelAndView;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDeniedPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("403_page");
        return modelAndView;
    }

    @RequestMapping(value = "/new_account", method = RequestMethod.GET)
    public ModelAndView newAccountPageGet() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new_account");
        return modelAndView;
    }

    @RequestMapping(value = "/new_account", method = RequestMethod.POST)
    public ModelAndView newAccountPagePost(@ModelAttribute("account") Account account) {
        ModelAndView modelAndView = new ModelAndView();
        for (int i = 0; i < account.getNickname().length(); i++) {
            char chr = account.getNickname().charAt(i);
            if (!((chr >= 'A' && chr <= 'z') || (chr >= '0' && chr <= '9'))) {
                modelAndView.setViewName("redirect:/new_account?error=login");
                return modelAndView;
            }
        }
        if (account.getEncPassword().length() < 5) {
            modelAndView.setViewName("redirect:/new_account?error=password");
            return modelAndView;
        }
        for (int i = 0; i < account.getEncPassword().length(); i++) {
            char chr = account.getEncPassword().charAt(i);
            if (!((chr >= 'A' && chr <= 'z') || (chr >= '0' && chr <= '9'))) {
                modelAndView.setViewName("redirect:/new_account?error=password");
                return modelAndView;
            }
        }
        try {
            messengerService.createAccount(account);
            modelAndView.setViewName("redirect:/login");
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/new_account?error=unique");
        }
        return modelAndView;
    }
}
