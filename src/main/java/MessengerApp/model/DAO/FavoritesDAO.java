package MessengerApp.model.DAO;

import MessengerApp.model.Account;
import MessengerApp.model.Favorites;
import MessengerApp.model.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class FavoritesDAO {

    private SessionFactory sessionFactory;

    private AccountDAO accountDAO;

    private MessageDAO messageDAO;

    @Autowired
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Autowired
    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Favorites getFavoriteByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Favorites) session.get(Favorites.class, id);
    }

    public Favorites getFavoritesByAccIdMesId(int accountId, int messageId) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "select e " + "from " + Favorites.class.getName() + " e " +
                " WHERE e.account=:account and e.message=:message";
        Query query = session.createQuery(sql);
        query.setParameter("account", accountDAO.getAccountByID(accountId));
        query.setParameter("message", messageDAO.getMessageByID(messageId));
        return (Favorites) query.uniqueResult();
    }

    public List<Favorites> getFavoritesByAccount(Account account) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT e FROM " + Favorites.class.getName() + " e " //
                + " Where e.account = :account ";
        Query query = session.createQuery(sql);
        query.setParameter("account", account);
        return (List<Favorites>) query.getResultList();
    }

    public void createFavorite(Account account, Message message) {
        Session session = sessionFactory.getCurrentSession();
        Favorites fav = new Favorites();
        fav.setAccount(account);
        fav.setMessage(message);
        session.persist(fav);
    }

    public void deleteFavorite(int id) {
        Session session = sessionFactory.getCurrentSession();
        Favorites fav = getFavoriteByID(id);
        session.delete(fav);
    }

    public List<Object[]> getFavoritesToYou(Account account) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "select ac.firstName, ac.lastName, m.createDate, m.text , m.id " +
                "from " + Favorites.class.getName() + " fav " +
                " INNER JOIN fav.message m  " +
                " INNER JOIN m.account ac " +
                " WHERE fav.account=:account " +
                "ORDER BY m.createDate DESC";
        Query query = session.createQuery(sql);
        query.setParameter("account", account);
        return (List<Object[]>) query.getResultList();
    }

    public boolean hasFavByID(int accountId, int messageId) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT count(1) FROM " + Favorites.class.getName() + " e " //
                + " Where e.account = :account and e.message = :message";
        Query query = session.createQuery(sql);
        query.setParameter("account", accountDAO.getAccountByID(accountId));
        query.setParameter("message", messageDAO.getMessageByID(messageId));
        return (Long) query.uniqueResult() > 0;
    }

    public void createFavoriteById(int accountId, int messageId) {
        if (!hasFavByID(accountId, messageId)) {
            Session session = sessionFactory.getCurrentSession();
            Favorites fav = new Favorites();
            fav.setAccount(accountDAO.getAccountByID(accountId));
            fav.setMessage(messageDAO.getMessageByID(messageId));
            session.persist(fav);
        }
    }

    public void deleteFavoriteById(int accountId, int messageId) {
        if (hasFavByID(accountId, messageId)) {
            Session session = sessionFactory.getCurrentSession();
            Favorites fav = getFavoritesByAccIdMesId(accountId, messageId);
            session.delete(fav);
        }
    }

}
