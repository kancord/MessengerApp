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
    private Account accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MESSAGE_ID", nullable = false)
    private Message messageId;
}