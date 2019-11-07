package MessengerApp.model.DAO;

import MessengerApp.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO  {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Account getAccountByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Account) session.get(Account.class, id);
    }

    public Account getAccountByNickname(String nickname){
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT e FROM " + Account.class.getName() + " e " //
                + " Where e.NICKNAME = :nickname ";
        Query query = session.createQuery(sql);
        query.setParameter("nickname", nickname);
        return (Account) query.getSingleResult();
    }


    public void createAccount(String nickname, String firstName, String lastName, String about, String password) {
        Session session = sessionFactory.getCurrentSession();
        Account account = new Account();
        account.setAbout(about);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setNickname(nickname);
        account.setEncPassword(password);
        session.persist(account);
    }

    public void deleteAccount(String nickname) {
        Session session = sessionFactory.getCurrentSession();
        Account account = getAccountByNickname(nickname);
        session.delete(account);
    }

    public void updateAccount(String nickname, String firstName, String lastName, String about, String password) {
        Session session = sessionFactory.getCurrentSession();
        Account account = getAccountByNickname(nickname);
        account.setAbout(about);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setNickname(nickname);
        account.setEncPassword(password);
        session.update(account);
    }


}
