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

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Subscribe getSubscribeByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Subscribe) session.get(Subscribe.class, id);
    }

    public Subscribe getSubscribeByAccs(Account account, Account subAccount) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT e FROM " + Subscribe.class.getName() + " e " //
                + " Where e.account = :account  and e.subAccount = :subAccount";
        Query query = session.createQuery(sql);
        query.setParameter("account", account);
        query.setParameter("subAccount", subAccount);
        return (Subscribe) query.uniqueResult();
    }

    public List<Subscribe> getSubscribesByAccount(Account account) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT e FROM " + Subscribe.class.getName() + " e " //
                + " Where e.account = :account ";
        Query query = session.createQuery(sql);
        query.setParameter("account", account);
        return (List<Subscribe>) query.getResultList();
    }

    public boolean hasSubscribeByAcc(Account account, Account subAccount) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT count(1) FROM " + Subscribe.class.getName() + " e " //
                + " Where e.account = :account and e.subAccount = :sub_account";
        Query query = session.createQuery(sql);
        query.setParameter("account", account);
        query.setParameter("sub_account", subAccount);
        return (Long) query.uniqueResult() > 0;
    }

    public void createSubscribeByAccs(Account account, Account subAccount) {
        Session session = sessionFactory.getCurrentSession();
        Subscribe subscribe = new Subscribe();
        subscribe.setAccount(account);
        subscribe.setSubAccount(subAccount);
        session.persist(subscribe);
    }

    public void deleteSubscribeByAccs(Account account, Account subAccount) {
        Session session = sessionFactory.getCurrentSession();
        Subscribe subscribe = getSubscribeByAccs(account, subAccount);
        session.delete(subscribe);
    }
}
