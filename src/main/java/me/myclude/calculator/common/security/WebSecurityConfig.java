package me.myclude.calculator.common.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(DataSource dataSource, PasswordEncoder passwordEncoder) {
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
    }

    private static final String REMEMBER_ME = "sampleRememberMeKey";

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .antMatchers("/resources/**").anyRequest();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/webjars/**").permitAll()
                    .antMatchers("/account/register").permitAll()
                    .antMatchers("/member/**", "/favicon.ico").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/account/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery("select a.username, a.password, a.enabled from (select employee_number as username, password, enabled "
                        + "from promlusr ) a "
                        + "where username = ?")
                .authoritiesByUsernameQuery(
                		  "SELECT	LUSR.employee_number, "
                		+ "			R.name "
                		+ "FROM		member_role MR "
                		+ "INNER JOIN promlusr LUSR ON LUSR.member_id = MR.member_id "
                		+ "INNER JOIN role R ON R.role_id = MR.role_id "
                		+ "WHERE	LUSR.employee_number = ?"
                );
    }
}
