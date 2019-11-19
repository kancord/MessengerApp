package MessengerApp.controller;

import MessengerApp.model.DAO.AccountDAO;
import MessengerApp.model.DAO.FavoritesDAO;
import MessengerApp.model.DAO.MessageDAO;
import MessengerApp.model.DAO.SubscribeDAO;
import MessengerApp.model.Message;
import MessengerApp.service.MessengerService;
import MessengerApp.service.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class MessengerController {

    MessengerService messengerService;

    @Autowired
    public void setMessengerService(MessengerService messengerService) {
        this.messengerService = messengerService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainPage(@AuthenticationPrincipal MyUser customUser) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("feedList", messengerService.feedListToAccountId(customUser.getId()));
        return modelAndView;
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ModelAndView accounts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account_list");
        modelAndView.addObject("accountList", messengerService.getAccountList());
        return modelAndView;
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public ModelAndView accountDetail(@PathVariable("id") int id, @AuthenticationPrincipal MyUser customUser) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account_detail");
        modelAndView.addObject("account", messengerService.getAccountByID(id));
        modelAndView.addObject("messageList", messengerService.getMessagesByAccountId(id));
        boolean isFollowed = (messengerService.hasSubscribeByAccSubaccId(customUser.getId(), id) || customUser.getId() == id);
        modelAndView.addObject("isFollowed", isFollowed);
        return modelAndView;
    }


    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public ModelAndView messages(@AuthenticationPrincipal MyUser customUser) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("message_list");
        modelAndView.addObject("messageList", messengerService.getMessagesByAccountId(customUser.getId()));
        return modelAndView;
    }

    @RequestMapping(value = "/message/add", method = RequestMethod.GET)
    public ModelAndView messagesAddGet() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("message_add");
        return modelAndView;
    }

    @RequestMapping(value = "/message/add", method = RequestMethod.POST)
    public ModelAndView messagesAddPost(@ModelAttribute("message") Message msg, @AuthenticationPrincipal MyUser customUser) {
        ModelAndView modelAndView = new ModelAndView();
        messengerService.createMessage(msg.getText(), customUser.getId());
        modelAndView.setViewName("redirect:/message");
        return modelAndView;
    }

    @RequestMapping(value = "/sub", method = RequestMethod.GET)
    public ModelAndView subs(@AuthenticationPrincipal MyUser customUser) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("subscribe_list");
        modelAndView.addObject("subscriberAccountList", messengerService.getSubscriberListByAccountId(customUser.getId()));
        modelAndView.addObject("followAccountList", messengerService.getFollowListByAccountId(customUser.getId()));
        return modelAndView;
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    public ModelAndView favorites(@AuthenticationPrincipal MyUser customUser) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("favorite_list");
        modelAndView.addObject("favoriteList", messengerService.favoritesListToAccountId(customUser.getId()));
        return modelAndView;
    }

    @RequestMapping(value = "/addFav/{mesId}", method = RequestMethod.PUT)
    @ResponseBody
    public String ajaxAddFavorite(@PathVariable("mesId") int mesId, @AuthenticationPrincipal MyUser customUser) {
        messengerService.createFavoriteById(customUser.getId(), mesId);
        return "OK";
    }

    @RequestMapping(value = "/delFav/{mesId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String ajaxDeleteFavorite(@PathVariable("mesId") int mesId, @AuthenticationPrincipal MyUser customUser) {
        messengerService.deleteFavoriteById(customUser.getId(), mesId);
        return "OK";
    }

    @RequestMapping(value = "/addSub/{subId}", method = RequestMethod.PUT)
    @ResponseBody
    public String ajaxAddSubscribe( @PathVariable("subId") int subId, @AuthenticationPrincipal MyUser customUser) {
        messengerService.createSubscribeById(customUser.getId(), subId);
        return "OK";
    }

    @RequestMapping(value = "/delSub/{subId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String ajaxDelSubscribe(@PathVariable("subId") int subId, @AuthenticationPrincipal MyUser customUser) {
        messengerService.deleteSubscribeById(customUser.getId(), subId);
        return "OK";
    }
}