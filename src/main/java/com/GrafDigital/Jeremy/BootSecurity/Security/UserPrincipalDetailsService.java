package com.GrafDigital.Jeremy.BootSecurity.Security;

import com.GrafDigital.Jeremy.BootSecurity.Modele.AppUser;
import com.GrafDigital.Jeremy.BootSecurity.Repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Cette Classe va implementé de UserDetailsService de Spring Security
@Service
@AllArgsConstructor // Pour l'injection de notre Repository
public class UserPrincipalDetailsService implements UserDetailsService {
    // Lier cette classe à notre Repository pour pouvoir charger l'utilisateur par son userName
    private AppUserRepository appUserRepository;
    // on implement la méthode
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Extraire l'utilisateur dans la base de donnée;
        AppUser appUser = appUserRepository.findByUserName(username);
        // Convertir ce User comme le user Principal
        UserPrincipal userPrincipal = new UserPrincipal(appUser); // User Extract in database;
        // Maintenant on retourne le userPrincipal;
        return userPrincipal;
    }
}
