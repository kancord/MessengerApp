package MessengerApp.model.DAO;

import MessengerApp.model.Account;
import MessengerApp.model.Subscribe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class AccountDAO  {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
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
                + " Where e.nickname = :nickname ";
        Query query = session.createQuery(sql);
        query.setParameter("nickname", nickname);
        return (Account) query.getSingleResult();
    }

    public List<Account> getAccountList(){
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT e FROM " + Account.class.getName() + " e " ;
        Query query = session.createQuery(sql);
        return  (List<Account>) query.getResultList();
    }

    public void createAccount(String nickname, String firstName, String lastName, String about, String password) {
        Session session = sessionFactory.getCurrentSession();
        Account account = new Account();
        account.setAbout(about);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setNickname(nickname);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        account.setEncPassword(bCryptPasswordEncoder.encode(password));
        session.persist(account);
    }

    public void createAccountByObject(Account acc) {
        Session session = sessionFactory.getCurrentSession();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        acc.setEncPassword(bCryptPasswordEncoder.encode(acc.getEncPassword()));
        session.persist(acc);
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
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        account.setEncPassword(bCryptPasswordEncoder.encode(password));
        session.update(account);
    }


    public List<Account> getSubscriberList(int accountId) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "select ac " +
                "from " + Subscribe.class.getName() + " sub " +
                "INNER JOIN sub.account ac "  +
                " WHERE sub.subAccount=:account ";
        Query query = session.createQuery(sql);
        query.setParameter("account", getAccountByID(accountId));
        return  (List<Account>) query.getResultList();
    }

    public List<Account> getFollowList(int accountId) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "select ac " +
                "from " + Subscribe.class.getName() + " sub " +
                "INNER JOIN sub.subAccount ac "  +
                " WHERE sub.account=:account ";
        Query query = session.createQuery(sql);
        query.setParameter("account", getAccountByID(accountId));
        return  (List<Account>) query.getResultList();
    }
}
