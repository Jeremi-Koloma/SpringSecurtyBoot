package com.GrafDigital.Jeremy.BootSecurity.Modele;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity // Identifier comme une entité JPA
@Getter // Generer les Getters
@Setter // Generer les Setters
@NoArgsConstructor // Un constructerus sans paramètre
public class AppUser {
    @Id // Idendifier le ID
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String userName;

    @Column(nullable = false)
    private String password;

    private int active;

    @Column(length = 20)
    private String roles = "";

    @Column(length = 50)
    private String permissions = "";

    public AppUser(String userName, String password, String roles, String permissions){
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        this.active = 1;
    }

    // Roles
    public List<String> getRoleList(){
        // Vérifier si le role n'est pas vide
        if (this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    // Roles
    public List<String> getPermissionList(){
        // Vérifier si les permissions ne sont pas vide
        if (this.permissions.length() > 0){
            return Arrays.asList(this.permissions .split(","));
        }
        return new ArrayList<>();
    }

}
