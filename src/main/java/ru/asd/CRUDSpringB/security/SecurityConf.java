package ru.asd.CRUDSpringB.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import ru.asd.CRUDSpringB.service.UserDetailsServImpl;

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //     PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.userDetailsService(getUserDetailsService());
      /*  auth.inMemoryAuthentication()
                .withUser("asd")
                .password(encoder.encode("password"))
                .roles("USER");*/
/*
        auth
                .inMemoryAuthentication()
                .withUser("asd").password("password").roles("USER");
*/

    }
/*
auth
        .userDetailsService(userDetailsService())
        .passwordEncoder(dsdf);
*/


    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/edit").access("hasRole('ADMIN')")
                .antMatchers("/userlist").access("hasRole('ADMIN')")
                .antMatchers("/new").access("hasRole('ADMIN')")
                .antMatchers("/**").access("hasAnyRole('USER','ADMIN')")


                .anyRequest().authenticated()
                .and();
        http.logout()
//                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and();
        http.formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureUrl("/login-error")
                .defaultSuccessUrl("/userlist")
               // .successForwardUrl("/userlist")
                .permitAll();
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsServImpl();
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

//    PasswordEncoder passwordEncoder = new P;

}
