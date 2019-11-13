package MessengerApp.model;


import javax.persistence.*;

@Entity
@Table(name = "FAVORITES")
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MESSAGE_ID", nullable = false)
    private Message message;

    public int getId() {
        return Id;
    }

    public Account getAccount() {
        return account;
    }

    public Message getMessage() {
        return message;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}