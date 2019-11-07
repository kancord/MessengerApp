package MessengerApp.model.DAO;

import MessengerApp.model.Account;
import MessengerApp.model.Subscribe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubscribeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Subscribe getSubscribeByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Subscribe) session.get(Subscribe.class, id);
    }

    public List<Subscribe> getSubscribesByAccount(Account account){
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT e FROM " + Subscribe.class.getName() + " e " //
                + " Where e.accountId = :account_id ";
        Query query = session.createQuery(sql);
        query.setParameter("account_id", account.getId());
        return  (List<Subscribe>) query.getResultList();
    }

    public void createSubscribe(Account account, Account subAccount) {
        Session session = sessionFactory.getCurrentSession();
        Subscribe subscribe = new Subscribe();
        subscribe.setAccountId(account);
        subscribe.setSubAccountId(subAccount);
        session.persist(subscribe);
    }

    public void deleteSubscribe(int id) {
        Session session = sessionFactory.getCurrentSession();
        Subscribe subscribe = getSubscribeByID(id);
        session.delete(subscribe);
    }

}
