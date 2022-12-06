package com.GrafDigital.Jeremy.BootSecurity.Security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


// Cette classe va etendre de webSecurityAdapter
@Configuration // Dire qu'il s'agit d'une classe de configuration
@EnableWebSecurity // Activer la Sécurité et dire à Spring ou se trouve la classe de configuration
@AllArgsConstructor // pour injecter userPrincipalDetailsService
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    // Injection userPrincipalDetailsService
    private UserPrincipalDetailsService userPrincipalDetailsService;
    // Overider deux (02) méthodes,

    // La première méthode permetra d'utiliser les identifiants des utilisateurs vennant de la base de donnée;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("123"))
                .roles("ADMIN").authorities("ACCESS_TEST1", "ACCESS_TEST2")
                .and()
                .withUser("Jeremi").password(passwordEncoder().encode("123")).roles("USER")
                .and()
                .withUser("manager").password(passwordEncoder().encode("123"))
                .roles("MANAGER").authorities("ACCESS_TEST1");
        auth.authenticationProvider(authenticationProvider());
    }

    // La deuxième méthode méthode configure va prendre en entré les requêtes HTTP avec (HttpSecurity en param qui correspond aux http)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/profile/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")// Toutes les requettes doivent etre identifier;
                .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
                .antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
                .antMatchers("/api/public/users").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login").permitAll() // Tous le monde doit avoir accès à notre page login
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login"); // Deconnexion
    }


    // Par ce que nous utiliser maintenant une base de donnée;
    @Bean
    DaoAuthenticationProvider  authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder()); // Pour le Cryptage
        daoAuthenticationProvider.setUserDetailsService(userPrincipalDetailsService); // Le Service

        return daoAuthenticationProvider;
    }

    // Crpter les mots de passe des utilisateurs
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
