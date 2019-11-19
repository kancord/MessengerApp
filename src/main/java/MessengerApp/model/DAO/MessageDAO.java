package MessengerApp.model.DAO;

import MessengerApp.model.Account;
import MessengerApp.model.Favorites;
import MessengerApp.model.Message;
import MessengerApp.model.Subscribe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class MessageDAO {


    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Message getMessageByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Message) session.get(Message.class, id);
    }

    public List<Message> getMessagesByAccount(Account account) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT e FROM " + Message.class.getName() + " e " //
                + " WHERE e.account = :account ORDER BY e.createDate DESC";
        Query query = session.createQuery(sql);
        query.setParameter("account", account);
        return (List<Message>) query.getResultList();
    }

    public List<Object[]> getMessagesToYou(Account account) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "select ac.firstName, ac.lastName, m.createDate, m.text , m.id, " +
                "CASE WHEN (fav.id IS NOT NULL) THEN 'TRUE' ELSE 'FALSE' END  " +
                "from " + Favorites.class.getName() + " fav " +
                "RIGHT JOIN fav.message  m " +
                "INNER JOIN m.account ac " +
                "WHERE ac IN " +
                "(SELECT sub.subAccount " +
                " FROM " + Subscribe.class.getName() + " sub " +
                " WHERE sub.account=:account) " +
                "ORDER BY m.createDate DESC";
        Query query = session.createQuery(sql);
        query.setParameter("account", account);
        return (List<Object[]>) query.getResultList();
    }

    public void createMessage(String text, Account account) {
        Session session = sessionFactory.getCurrentSession();
        Message msg = new Message();
        msg.setAccount(account);
        msg.setText(text);
        msg.setCreateDate(new Date());
        session.persist(msg);
    }

    public void deleteMessage(int id) {
        Session session = sessionFactory.getCurrentSession();
        Message msg = getMessageByID(id);
        session.delete(msg);
    }

    public void updateMessage(String text, int id) {
        Session session = sessionFactory.getCurrentSession();
        Message msg = getMessageByID(id);
        msg.setText(text);
        session.update(msg);
    }
}
