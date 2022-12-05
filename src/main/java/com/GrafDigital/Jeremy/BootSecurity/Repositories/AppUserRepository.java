package com.GrafDigital.Jeremy.BootSecurity.Repositories;
// Cette classe va étendre de l'interface JPA Repository avec <Entity, Long>

import com.GrafDigital.Jeremy.BootSecurity.Modele.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Pour la persitance des donnée dans la base de donnée;
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    // Retourner un utilisateur par son NOM
    AppUser findByUserName(String userName);
}
