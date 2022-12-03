package com.GrafDigital.Jeremy.BootSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


// Cette classe va etendre de webSecurityAdapter
@Configuration // Dire qu'il s'agit d'une classe de configuration
@EnableWebSecurity // Activer la Sécurité et dire à Spring ou se trouve la classe de configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    // Overider deux (02) méthodes,

    // La première méthode permetra d'utiliser les identifiants des utilisateurs vennant de la base de donnée;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("123"))
                .roles("ADMIN")
                .and()
                .withUser("Jeremi")
                .password(passwordEncoder().encode("1234"))
                .roles("USER");
    }

    // La deuxième méthode méthode configure va prendre en entré les requêtes HTTP avec (HttpSecurity en param qui correspond aux http)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated() // Toutes les requettes doivent etre identifier;
                .and()
                .httpBasic();
    }

    // Crpter les mots de passe des utilisateurs
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
