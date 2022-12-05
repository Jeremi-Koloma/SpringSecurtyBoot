package com.GrafDigital.Jeremy.BootSecurity.Repositories;

import com.GrafDigital.Jeremy.BootSecurity.Modele.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

// Cette classe va implementé CommandLineRunner pour Enregister des données dans la base;
@Service
@AllArgsConstructor // Pour l'injection de notre Interface AppUserRepository
public class DbInit implements CommandLineRunner {
    // Injectons le Repository
    private AppUserRepository appUserRepository;
    // Cryper les mots de passes
    private PasswordEncoder passwordEncoder;
    // On implemente les méthodes
    @Override
    public void run(String... args) throws Exception{
        // Supprimer les users qui exist déjà dans la base de donnnée;
        appUserRepository.deleteAll();

        // Maintenant Créons des Users
        AppUser Jere = new AppUser("Jeremi", passwordEncoder.encode("123"), "USER","");
        AppUser admin = new AppUser("admin", passwordEncoder.encode( "123"), "ADMIN","ACCESS_TEST1, ACCESS_TEST2");
        AppUser manager = new AppUser("manager", passwordEncoder.encode( "123") , "MANAGER","ACCESS_TEST1");

        List<AppUser> users = Arrays.asList(Jere,admin,manager);

        appUserRepository.saveAll(users);
    }
}
