package MessengerApp.controller;

import MessengerApp.model.Account;
import MessengerApp.model.DAO.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    private AccountDAO accountDAO;

    @Autowired
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
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
        accountDAO.createAccountByObject(account);
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }
}