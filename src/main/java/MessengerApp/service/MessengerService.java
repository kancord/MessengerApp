package MessengerApp.service;

import MessengerApp.model.Account;
import MessengerApp.model.DAO.AccountDAO;
import MessengerApp.model.DAO.FavoritesDAO;
import MessengerApp.model.DAO.MessageDAO;
import MessengerApp.model.DAO.SubscribeDAO;
import MessengerApp.model.Favorites;
import MessengerApp.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessengerService {
    private AccountDAO accountDAO;

    private MessageDAO messageDAO;

    private SubscribeDAO subscribeDAO;

    private FavoritesDAO favoritesDAO;

    @Autowired
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Autowired
    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Autowired
    public void setSubscribeDAO(SubscribeDAO subscribeDAO) {
        this.subscribeDAO = subscribeDAO;
    }

    @Autowired
    public void setFavoritesDAO(FavoritesDAO favoritesDAO) {
        this.favoritesDAO = favoritesDAO;
    }

    public void createAccount(Account account) {
        accountDAO.createAccount(account);
    }

    public List<Object[]> feedListToAccountId(int id) {
        return messageDAO.getMessagesToAccount(accountDAO.getAccountByID(id));
    }

    public List<Account> getAccountList() {
        return accountDAO.getAccountList();
    }

    public Account getAccountByID(int id) {
        return accountDAO.getAccountByID(id);
    }

    public List<Message> getMessagesByAccountId(int id) {
        return messageDAO.getMessagesByAccount(accountDAO.getAccountByID(id));
    }

    public boolean hasSubscribeByAccSubaccId(int accountId, int subAccountId) {
        return subscribeDAO.hasSubscribeByAcc(accountDAO.getAccountByID(accountId), accountDAO.getAccountByID(subAccountId));
    }

    public void createMessage(String message, int accountId) {
        messageDAO.createMessage(message, accountDAO.getAccountByID(accountId));
    }

    public List<Account> getSubscriberListByAccountId(int id) {
        return accountDAO.getSubscriberList(getAccountByID(id));
    }

    public List<Account> getFollowListByAccountId(int id) {
        return accountDAO.getFollowList(getAccountByID(id));
    }

    public List<Object[]> favoritesListToAccountId(int id) {
        return favoritesDAO.getFavoriteMessagesByAccount(getAccountByID(id));
    }

    public void createSubscribeById(int id, int subId) {
        Account account = getAccountByID(id);
        Account subAccount = getAccountByID(subId);
        if (!subscribeDAO.hasSubscribeByAcc(account, subAccount)) {
            subscribeDAO.createSubscribeByAccs(account, subAccount);
        }
    }

    public void deleteSubscribeById(int id, int subId) {
        Account account = getAccountByID(id);
        Account subAccount = getAccountByID(subId);
        if (subscribeDAO.hasSubscribeByAcc(account, subAccount)) {
            subscribeDAO.deleteSubscribeByAccs(account, subAccount);
        }
    }

    public boolean hasFavoriteByAccIdAndMesId(int accountId, int messageId) {
        return favoritesDAO.hasFavavoriteByAccAndMes(accountDAO.getAccountByID(accountId), messageDAO.getMessageByID(messageId));
    }

    public void createFavoriteById(int accountId, int messageId) {
        Account account = accountDAO.getAccountByID(accountId);
        Message message = messageDAO.getMessageByID(messageId);
        if (!favoritesDAO.hasFavavoriteByAccAndMes(account, message)) {
            favoritesDAO.createFavoriteByAccAndMes(account, message);
        }
    }

    public void deleteFavoriteById(int accountId, int messageId) {
        Account account = accountDAO.getAccountByID(accountId);
        Message message = messageDAO.getMessageByID(messageId);
        if (favoritesDAO.hasFavavoriteByAccAndMes(account, message)) {
            favoritesDAO.deleteFavoriteByAccAndMes(account, message);
        }
    }

}