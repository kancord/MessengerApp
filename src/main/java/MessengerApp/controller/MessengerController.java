package MessengerApp.controller;

import MessengerApp.model.DAO.AccountDAO;
import MessengerApp.model.DAO.MessageDAO;
import MessengerApp.model.DAO.SubscribeDAO;
import MessengerApp.model.Message;
import MessengerApp.service.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class MessengerController {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private SubscribeDAO subscribeDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public ModelAndView accounts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account_list");
        modelAndView.addObject("accountList", accountDAO.getAccountList());
        return modelAndView;
    }

    @RequestMapping(value = "/addSub", method = RequestMethod.GET)
    @ResponseBody
    public String ajaxAddSubscribe(@RequestParam("id") int id, @RequestParam("subId") int subId) {
        subscribeDAO.createSubscribeById(id, subId);
        return "OK";
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ModelAndView messages(@AuthenticationPrincipal MyUser customUser) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("message_list");
        modelAndView.addObject("messageList", messageDAO.getMessagesByAccount(accountDAO.getAccountByID(customUser.getId())));
        return modelAndView;
    }

    @RequestMapping(value = "/messages/add", method = RequestMethod.GET)
    public ModelAndView messagesAddGet() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("message_add");
        return modelAndView;
    }

    @RequestMapping(value = "/messages/add", method = RequestMethod.POST)
    public ModelAndView messagesAddPost(@ModelAttribute("message") Message msg, @AuthenticationPrincipal MyUser customUser) {
        ModelAndView modelAndView = new ModelAndView();
        messageDAO.createMessage(msg.getText(), accountDAO.getAccountByID(customUser.getId()));
        modelAndView.setViewName("redirect:/messages");
        return modelAndView;
    }
}