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

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Favorites getFavoriteByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Favorites) session.get(Favorites.class, id);
    }

    public List<Favorites> getFavoritesByAccount(Account account){
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT e FROM " + Favorites.class.getName() + " e " //
                + " Where e.account = :account ";
        Query query = session.createQuery(sql);
        query.setParameter("account", account);
        return  (List<Favorites>) query.getResultList();
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

}
