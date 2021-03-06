package MessengerApp.config;


import MessengerApp.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyUserDetailService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests().antMatchers("/login", "/logout", "/new_account", "/logoutSuccessful").permitAll();
        http.authorizeRequests().antMatchers("/static/main.css").permitAll();
        http.authorizeRequests().antMatchers("/**").authenticated();
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        http.authorizeRequests().and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout()
                .logoutSuccessUrl("/logoutSuccessful");

        http.authorizeRequests().and()
                .rememberMe().tokenRepository(this.persistentTokenRepository())
                .tokenValiditySeconds(24 * 60 * 60); // 24h

    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

}
