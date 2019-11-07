package MessengerApp.model;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGE")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "TEXT", length = 1024, nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account accountId;

    public int getId() {
        return Id;
    }

    public String getText() {
        return text;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }
}