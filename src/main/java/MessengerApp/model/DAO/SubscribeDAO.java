package MessengerApp.model.DAO;

import MessengerApp.model.Account;
import MessengerApp.model.Subscribe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SubscribeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private AccountDAO accountDAO;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Subscribe getSubscribeByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Subscribe) session.get(Subscribe.class, id);
    }

    public List<Subscribe> getSubscribesByAccount(Account account) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT e FROM " + Subscribe.class.getName() + " e " //
                + " Where e.account = :account ";
        Query query = session.createQuery(sql);
        query.setParameter("account", account);
        return (List<Subscribe>) query.getResultList();
    }

    public boolean hasSubscribeByID(int accountId, int subAccountId) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT count(1) FROM " + Subscribe.class.getName() + " e " //
                + " Where e.account = :account and e.subAccount = :sub_account";
        Query query = session.createQuery(sql);
        query.setParameter("account", accountDAO.getAccountByID(accountId));
        query.setParameter("sub_account", accountDAO.getAccountByID(subAccountId));
        return (Long) query.uniqueResult() > 0;
    }

    public void createSubscribe(Account account, Account subAccount) {
        Session session = sessionFactory.getCurrentSession();
        Subscribe subscribe = new Subscribe();
        subscribe.setAccount(account);
        subscribe.setSubAccount(subAccount);
        session.persist(subscribe);
    }

    public void createSubscribeById(int accountId, int subAccountId) {
        if (!hasSubscribeByID(accountId, subAccountId)) {
            Session session = sessionFactory.getCurrentSession();
            Subscribe subscribe = new Subscribe();
            subscribe.setAccount(accountDAO.getAccountByID(accountId));
            subscribe.setSubAccount(accountDAO.getAccountByID(subAccountId));
            session.persist(subscribe);
        }
    }

    public void deleteSubscribe(int id) {
        Session session = sessionFactory.getCurrentSession();
        Subscribe subscribe = getSubscribeByID(id);
        session.delete(subscribe);
    }

}
