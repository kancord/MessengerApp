package MessengerApp.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MyUser extends User {

    private final int id;

    public int getId() {
        return id;
    }


    public MyUser(int id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }
}
