package MessengerApp.model;

import javax.persistence.*;
import java.util.Date;

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
    private Account account;

    @Column(name="CREATE_DATE")
    private Date createDate;



    public int getId() {
        return Id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getText() {
        return text;
    }

    public Account getAccount() {
        return account;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}