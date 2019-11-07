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
    private Account accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUB_ACCOUNT_ID", nullable = false)
    private Account subAccountId;

    public int getId() {
        return Id;
    }

    public Account getAccountId() {
        return accountId;
    }

    public Account getSubAccountId() {
        return subAccountId;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    public void setSubAccountId(Account subAccountId) {
        this.subAccountId = subAccountId;
    }
}