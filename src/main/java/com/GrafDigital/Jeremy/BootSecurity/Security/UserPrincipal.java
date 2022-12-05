package com.GrafDigital.Jeremy.BootSecurity.Security;

import com.GrafDigital.Jeremy.BootSecurity.Modele.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Cette Classe va implementé de UserDetails se Spring Security
@AllArgsConstructor // pour le constructeur de UserPincipal
public class UserPrincipal implements UserDetails {
    // Lier cette classe à notre Entity AppUser
    private AppUser appUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Créons une liste de permissions
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Extraire la liste de permission (Les Noms)
        appUser.getPermissionList().forEach(p->{
             GrantedAuthority authority = new SimpleGrantedAuthority(p);
             authorities.add(authority);
        });

        // Extraire la liste des Roles (Nom des Roles)
        appUser.getRoleList().forEach(r->{
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" +r);
            authorities.add(authority);
        });
        // Maintenant on retourne la liste des permissions
        return authorities;
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true ;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return appUser.getActive() == 1;
    }
}
