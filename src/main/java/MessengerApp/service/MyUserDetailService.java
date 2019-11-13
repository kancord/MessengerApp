package MessengerApp.service;

import MessengerApp.model.Account;
import MessengerApp.model.DAO.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private AccountDAO accountDAO;

    public MyUser loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account acc = accountDAO.getAccountByNickname(userName);
        if (acc == null) {
            System.out.println("Account not found! " + userName);
            throw new UsernameNotFoundException("Account " + userName + " was not found in the database");
        }
        System.out.println("user:" + acc.getNickname() + " found");
        return new MyUser(acc.getId(), acc.getNickname(), acc.getEncPassword(), new ArrayList<GrantedAuthority>());
    }
}
