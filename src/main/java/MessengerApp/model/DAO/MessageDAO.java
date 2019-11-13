package MessengerApp.model.DAO;

import MessengerApp.model.Account;
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
public class MessageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Message getMessageByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Message) session.get(Message.class, id);
    }

    public List<Message> getMessagesByAccount(Account account){
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT e FROM " + Message.class.getName() + " e " //
                + " Where e.account = :account ";
        Query query = session.createQuery(sql);
        query.setParameter("account", account);
        return  (List<Message>) query.getResultList();
    }

    public void createMessage(String text, Account account) {
        Session session = sessionFactory.getCurrentSession();
        Message msg = new Message();
        msg.setAccount(account);
        msg.setText(text);
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
