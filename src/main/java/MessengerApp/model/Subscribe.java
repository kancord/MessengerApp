package MessengerApp.model;

import javax.persistence.*;

@Entity
@Table(name = "SUBSCRIBE")
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUB_ACCOUNT_ID", nullable = false)
    private Account subAccount;

    public int getId() {
        return Id;
    }

    public Account getAccount() {
        return account;
    }

    public Account getSubAccount() {
        return subAccount;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setSubAccount(Account subAccount) {
        this.subAccount = subAccount;
    }
}